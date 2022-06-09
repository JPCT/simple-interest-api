package com.jpct.simpleinterestapi.domain.mapper.impl;

import com.jpct.simpleinterestapi.app.rest.request.SimpleInterestRequest;
import com.jpct.simpleinterestapi.app.rest.response.SimpleInterestResponse;
import com.jpct.simpleinterestapi.domain.mapper.SimpleInterestMapper;
import com.jpct.simpleinterestapi.domain.model.PaymentOutput;
import com.jpct.simpleinterestapi.domain.model.SimpleInterestInput;
import org.springframework.stereotype.Component;

@Component
public class SimpleInterestMapperImpl implements SimpleInterestMapper {

    @Override
    public SimpleInterestInput mapToSimpleInterestInput(SimpleInterestRequest simpleInterestRequest) {
        return SimpleInterestInput.newBuilder()
                .amount(simpleInterestRequest.getAmount())
                .terms(simpleInterestRequest.getTerms())
                .rate(simpleInterestRequest.getRate())
                .build();
    }

    @Override
    public SimpleInterestResponse mapToSimpleInterestResponse(PaymentOutput paymentOutput) {
        return SimpleInterestResponse.newBuilder()
                .paymentNumber(paymentOutput.getPaymentNumber())
                .amount(paymentOutput.getAmount())
                .paymentDate(paymentOutput.getPaymentDate())
                .build();
    }
}
