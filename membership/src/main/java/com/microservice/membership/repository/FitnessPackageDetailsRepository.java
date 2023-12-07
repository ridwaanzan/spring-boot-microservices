package com.microservice.membership.repository;

import com.microservice.membership.model.FitnessPackageDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessPackageDetailsRepository extends JpaRepository<FitnessPackageDetail, Long> {
}
