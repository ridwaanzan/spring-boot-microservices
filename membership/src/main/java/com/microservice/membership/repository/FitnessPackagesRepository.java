package com.microservice.membership.repository;

import com.microservice.membership.model.FitnessPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessPackagesRepository extends JpaRepository<FitnessPackage, Long> {
}
