package com.jpct.simpleinterestapi.domain.service.impl;

import com.jpct.simpleinterestapi.app.rest.request.SimpleInterestRequest;
import com.jpct.simpleinterestapi.app.rest.response.SimpleInterestResponse;
import com.jpct.simpleinterestapi.domain.mapper.SimpleInterestMapper;
import com.jpct.simpleinterestapi.domain.model.PaymentOutput;
import com.jpct.simpleinterestapi.domain.model.SimpleInterestInput;
import com.jpct.simpleinterestapi.domain.repository.PaymentOutputRepository;
import com.jpct.simpleinterestapi.domain.repository.SimpleInterestInputRepository;
import com.jpct.simpleinterestapi.domain.service.SimpleInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SimpleInterestServiceImpl implements SimpleInterestService {

    private final SimpleInterestInputRepository simpleInterestInputRepository;
    private final PaymentOutputRepository paymentOutputRepository;
    private final SimpleInterestMapper simpleInterestMapper;

    @Autowired
    public SimpleInterestServiceImpl(SimpleInterestInputRepository simpleInterestInputRepository,
                                     PaymentOutputRepository paymentOutputRepository,
                                     SimpleInterestMapper simpleInterestMapper) {
        this.simpleInterestInputRepository = simpleInterestInputRepository;
        this.paymentOutputRepository = paymentOutputRepository;
        this.simpleInterestMapper = simpleInterestMapper;
    }

    @Override
    public List<SimpleInterestResponse> calculateSimpleInterest(SimpleInterestRequest simpleInterestRequest) {
        SimpleInterestInput simpleInterestInput = simpleInterestMapper.mapToSimpleInterestInput(simpleInterestRequest);
        simpleInterestInput = simpleInterestInputRepository.save(simpleInterestInput);

        List<PaymentOutput> paymentOutputList = getPaymentsList(simpleInterestInput);
        paymentOutputRepository.saveAll(paymentOutputList);

        return paymentOutputList.stream()
                .map(simpleInterestMapper::mapToSimpleInterestResponse)
                .collect(Collectors.toList());
    }

    private List<PaymentOutput> getPaymentsList(SimpleInterestInput simpleInterestInput){
        return IntStream.range(1, simpleInterestInput.getTerms() + 1)
                .mapToObj(i -> createPayment(i, simpleInterestInput))
                .collect(Collectors.toList());
    }

    private PaymentOutput createPayment(Integer iterator, SimpleInterestInput simpleInterestInput){
        return PaymentOutput.newBuilder()
                .simpleInterestInput(simpleInterestInput)
                .paymentNumber(iterator)
                .paymentDate(LocalDate.now().plusWeeks(iterator))
                .amount(calculateAmountPeriod(simpleInterestInput.getAmount(), simpleInterestInput.getRate(), iterator))
                .build();
    }

    private Double calculateAmountPeriod(Double principalAmount, Double rate, Integer month){
        return (principalAmount * rate * month) / 100;
    }
}
