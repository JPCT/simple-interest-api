package com.jpct.simpleinterestapi.domain.service;

import com.jpct.simpleinterestapi.app.rest.request.SimpleInterestRequest;
import com.jpct.simpleinterestapi.app.rest.response.SimpleInterestResponse;

import java.util.List;

public interface SimpleInterestService {
    List<SimpleInterestResponse> calculateSimpleInterest(SimpleInterestRequest simpleInterestRequest);
}
