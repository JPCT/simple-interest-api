package com.jpct.simpleinterestapi.domain.repository;

import com.jpct.simpleinterestapi.domain.model.PaymentOutput;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentOutputRepository extends JpaRepository<PaymentOutput, Long> {

    @Override
    Optional<PaymentOutput> findById(Long id);

    @Override
    List<PaymentOutput> findAll();

    List<PaymentOutput> findAllBySimpleInterestInputId(Long id);
}
