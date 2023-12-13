package com.microservice.membership.repository;

import com.microservice.membership.model.FitnessPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FitnessPackagesRepository extends JpaRepository<FitnessPackage, Long> {
    List<FitnessPackage> findAll();

    Optional<FitnessPackage> findById(Long id);
}
