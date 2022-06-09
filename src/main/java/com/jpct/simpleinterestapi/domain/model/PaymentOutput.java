package com.jpct.simpleinterestapi.domain.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
public class PaymentOutput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Integer paymentNumber;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SimpleInterestInput simpleInterestInput;

    private PaymentOutput(Builder builder) {
        id = builder.id;
        paymentNumber = builder.paymentNumber;
        amount = builder.amount;
        paymentDate = builder.paymentDate;
        createdAt = builder.createdAt;
        simpleInterestInput = builder.simpleInterestInput;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

    public PaymentOutput() {
    }

    public Long getId() {
        return id;
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public SimpleInterestInput getSimpleInterestInput() {
        return simpleInterestInput;
    }

    public static final class Builder {
        private Long id;
        private Integer paymentNumber;
        private Double amount;
        private LocalDate paymentDate;
        private LocalDateTime createdAt;
        private SimpleInterestInput simpleInterestInput;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
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

        public Builder createdAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder simpleInterestInput(SimpleInterestInput val) {
            simpleInterestInput = val;
            return this;
        }

        public PaymentOutput build() {
            return new PaymentOutput(this);
        }
    }
}
