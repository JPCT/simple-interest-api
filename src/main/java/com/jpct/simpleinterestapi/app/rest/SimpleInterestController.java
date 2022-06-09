package com.jpct.simpleinterestapi.app.rest;

import com.jpct.simpleinterestapi.app.rest.request.SimpleInterestRequest;
import com.jpct.simpleinterestapi.app.rest.response.SimpleInterestResponse;
import com.jpct.simpleinterestapi.domain.service.SimpleInterestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", maxAge = 86400)
@RestController
@RequestMapping("/api/interest")
@Tag(name = "Simple interest controller", description = "Simple interest controlle")
public class SimpleInterestController {

    private final SimpleInterestService simpleInterestService;
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Autowired
    public SimpleInterestController(SimpleInterestService simpleInterestService) {
        this.simpleInterestService = simpleInterestService;
    }

    @ApiOperation(value = "Calculate simple interest.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Simple interest calculated."),
            @ApiResponse(code = 400, message = "Error in input data."),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SimpleInterestResponse>> createUser(@Valid @RequestBody SimpleInterestRequest simpleInterestRequest){
        Set<ConstraintViolation<SimpleInterestRequest>> violationSet = validator.validate(simpleInterestRequest);
        if (!violationSet.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<SimpleInterestResponse> simpleInterestResponseList = simpleInterestService.calculateSimpleInterest(simpleInterestRequest);

        return new ResponseEntity<>(simpleInterestResponseList, HttpStatus.OK);
    }
}
