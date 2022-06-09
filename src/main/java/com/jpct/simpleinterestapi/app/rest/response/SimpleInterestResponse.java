package com.jpct.simpleinterestapi.app.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Objects;

@JsonRootName(value = "simple_interest_response")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@ApiModel(description = "Simple interest response.")
public class SimpleInterestResponse {

    @JsonProperty(value = "payment_number")
    @ApiModelProperty(notes = "Payment number.")
    private Integer paymentNumber;

    @JsonProperty(value = "amount")
    @ApiModelProperty(notes = "Amount.")
    private Double amount;

    @JsonProperty(value = "payment_date")
    @ApiModelProperty(notes = "Payment date")
    private LocalDate paymentDate;

    public SimpleInterestResponse() {
    }

    private SimpleInterestResponse(Builder builder) {
        setPaymentNumber(builder.paymentNumber);
        setAmount(builder.amount);
        setPaymentDate(builder.paymentDate);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(Integer paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public static final class Builder {
        private Integer paymentNumber;
        private Double amount;
        private LocalDate paymentDate;

        private Builder() {
        }

        public Builder paymentNumber(Integer val) {
            paymentNumber = val;
            return this;
        }

        public Builder amount(Double val) {
            amount = val;
            return this;
        }

        public Builder paymentDate(LocalDate val) {
            paymentDate = val;
            return this;
        }

        public SimpleInterestResponse build() {
            return new SimpleInterestResponse(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleInterestResponse that = (SimpleInterestResponse) o;
        return Objects.equals(paymentNumber, that.paymentNumber) && Objects.equals(amount, that.amount) && Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentNumber, amount, paymentDate);
    }
}
