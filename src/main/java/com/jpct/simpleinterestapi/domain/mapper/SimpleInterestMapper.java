package com.jpct.simpleinterestapi.domain.mapper;

import com.jpct.simpleinterestapi.app.rest.request.SimpleInterestRequest;
import com.jpct.simpleinterestapi.app.rest.response.SimpleInterestResponse;
import com.jpct.simpleinterestapi.domain.model.PaymentOutput;
import com.jpct.simpleinterestapi.domain.model.SimpleInterestInput;

public interface SimpleInterestMapper {
    SimpleInterestInput mapToSimpleInterestInput(SimpleInterestRequest simpleInterestRequest);
    SimpleInterestResponse mapToSimpleInterestResponse(PaymentOutput paymentOutput);
}
