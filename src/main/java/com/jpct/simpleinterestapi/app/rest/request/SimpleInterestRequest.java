package com.jpct.simpleinterestapi.app.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@JsonRootName(value = "simple_interest_request")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@ApiModel(description = "Simple interest request.")
public class SimpleInterestRequest {

    @JsonProperty(value = "amount", required = true)
    @ApiModelProperty(notes = "Amount to request.", required = true, dataType = "java.lang.Double")
    @Min(1)
    @Max(999999)
    private Double amount;

    @JsonProperty(value = "terms", required = true)
    @ApiModelProperty(notes = "Weeks to be paid on.", required = true, dataType = "java.lang.Integer")
    @Min(4)
    @Max(52)
    private Integer terms;

    @JsonProperty(value = "rate", required = true)
    @ApiModelProperty(notes = "Interest rate to be lend.", required = true, dataType = "java.lang.Double")
    @Min(1)
    @Max(100)
    private Double rate;

    public SimpleInterestRequest() {
    }

    private SimpleInterestRequest(Builder builder) {
        setAmount(builder.amount);
        setTerms(builder.terms);
        setRate(builder.rate);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTerms() {
        return terms;
    }

    public void setTerms(Integer terms) {
        this.terms = terms;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public static final class Builder {
        private Double amount;
        private Integer terms;
        private Double rate;

        private Builder() {
        }

        public Builder amount(Double val) {
            amount = val;
            return this;
        }

        public Builder terms(Integer val) {
            terms = val;
            return this;
        }

        public Builder rate(Double val) {
            rate = val;
            return this;
        }

        public SimpleInterestRequest build() {
            return new SimpleInterestRequest(this);
        }
    }
}
