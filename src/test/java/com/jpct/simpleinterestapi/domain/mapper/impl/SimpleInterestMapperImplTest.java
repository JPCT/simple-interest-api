package com.jpct.simpleinterestapi.domain.mapper.impl;

import com.jpct.simpleinterestapi.app.rest.request.SimpleInterestRequest;
import com.jpct.simpleinterestapi.app.rest.response.SimpleInterestResponse;
import com.jpct.simpleinterestapi.domain.model.PaymentOutput;
import com.jpct.simpleinterestapi.domain.model.SimpleInterestInput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleInterestMapperImplTest {

    @InjectMocks
    private SimpleInterestMapperImpl subject;
    @Before
    public void setUp() {
    }

    @Test
    public void shouldMapToSimpleInterestInputCorrectly(){
        SimpleInterestRequest simpleInterestRequest = SimpleInterestRequest.newBuilder()
                .amount(100.0)
                .terms(10)
                .rate(3.0)
                .build();

        SimpleInterestInput expectedSimpleInterestInput = SimpleInterestInput.newBuilder()
                .amount(100.0)
                .terms(10)
                .rate(3.0)
                .build();

        assertTrue(expectedSimpleInterestInput.equals(subject.mapToSimpleInterestInput(simpleInterestRequest)));
    }

    @Test
    public void shouldMapToSimpleInterestResponseCorrectly(){
        PaymentOutput paymentOutput = PaymentOutput.newBuilder()
                .paymentNumber(1)
                .amount(100.0)
                .paymentDate(LocalDate.now())
                .build();

        SimpleInterestResponse expectedSimpleInterestResponse = SimpleInterestResponse.newBuilder()
                .paymentNumber(1)
                .amount(100.0)
                .paymentDate(LocalDate.now())
                .build();

        assertTrue(expectedSimpleInterestResponse.equals(subject.mapToSimpleInterestResponse(paymentOutput)));
    }
}