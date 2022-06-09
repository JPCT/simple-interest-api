package com.jpct.simpleinterestapi.domain.service.impl;

import com.jpct.simpleinterestapi.app.rest.request.SimpleInterestRequest;
import com.jpct.simpleinterestapi.app.rest.response.SimpleInterestResponse;
import com.jpct.simpleinterestapi.domain.mapper.SimpleInterestMapper;
import com.jpct.simpleinterestapi.domain.model.PaymentOutput;
import com.jpct.simpleinterestapi.domain.model.SimpleInterestInput;
import com.jpct.simpleinterestapi.domain.repository.PaymentOutputRepository;
import com.jpct.simpleinterestapi.domain.repository.SimpleInterestInputRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleInterestServiceImplTest {

    @Mock
    private SimpleInterestInputRepository simpleInterestInputRepository;
    @Mock
    private PaymentOutputRepository paymentOutputRepository;
    @Mock
    private SimpleInterestMapper simpleInterestMapper;
    @InjectMocks
    private SimpleInterestServiceImpl subject;

    @Before
    public void setUp() {
    }

    @Test
    public void shouldCalculateSimpleInterestCorrectly(){
        SimpleInterestRequest simpleInterestRequest = SimpleInterestRequest.newBuilder()
                .amount(100.0)
                .rate(1.0)
                .terms(1)
                .build();

        SimpleInterestInput simpleInterestInput = SimpleInterestInput.newBuilder()
                .amount(100.0)
                .rate(1.0)
                .terms(1)
                .build();

        when(simpleInterestMapper.mapToSimpleInterestInput(simpleInterestRequest)).thenReturn(simpleInterestInput);
        when(simpleInterestInputRepository.save(simpleInterestInput)).thenReturn(simpleInterestInput);

        List<PaymentOutput> paymentOutputList = Arrays.asList(PaymentOutput.newBuilder()
                        .id(null)
                        .simpleInterestInput(any())
                        .paymentDate(LocalDate.now().plusWeeks(1))
                        .amount(100.0)
                        .paymentNumber(1)
                .build());

        when(simpleInterestMapper.mapToSimpleInterestResponse(paymentOutputList.get(0))).thenReturn(SimpleInterestResponse.newBuilder()
                        .amount(100.0)
                        .paymentDate(LocalDate.now().plusWeeks(1))
                        .paymentNumber(1)
                .build());

        SimpleInterestResponse expectedSimpleInterestResponse = SimpleInterestResponse.newBuilder()
                .paymentNumber(1)
                .amount(100.0)
                .paymentDate(LocalDate.now().plusWeeks(1))
                .build();
        List<SimpleInterestResponse> simpleInterestResponses = subject.calculateSimpleInterest(simpleInterestRequest);

        assertEquals(expectedSimpleInterestResponse, simpleInterestResponses.get(0));
        verify(paymentOutputRepository).saveAll(any());
    }
}