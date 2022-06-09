package com.jpct.simpleinterestapi.domain.repository;

import com.jpct.simpleinterestapi.domain.model.SimpleInterestInput;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SimpleInterestInputRepository extends JpaRepository<SimpleInterestInput, Long> {

    @Override
    Optional<SimpleInterestInput> findById(Long id);

    @Override
    List<SimpleInterestInput> findAll();
}
