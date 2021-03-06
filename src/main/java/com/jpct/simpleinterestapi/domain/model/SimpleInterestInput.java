package com.jpct.simpleinterestapi.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
public class SimpleInterestInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Integer terms;

    @Column(nullable = false)
    private Double rate;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private SimpleInterestInput(Builder builder) {
        id = builder.id;
        amount = builder.amount;
        terms = builder.terms;
        rate = builder.rate;
        createdAt = builder.createdAt;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

    public SimpleInterestInput() {
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getTerms() {
        return terms;
    }

    public Double getRate() {
        return rate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static final class Builder {
        private Long id;
        private Double amount;
        private Integer terms;
        private Double rate;
        private LocalDateTime createdAt;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
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

        public Builder createdAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public SimpleInterestInput build() {
            return new SimpleInterestInput(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleInterestInput that = (SimpleInterestInput) o;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(terms, that.terms) && Objects.equals(rate, that.rate) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, terms, rate, createdAt);
    }
}
