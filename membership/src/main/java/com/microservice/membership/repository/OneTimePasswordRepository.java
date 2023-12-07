package com.microservice.membership.repository;

import com.microservice.membership.model.OneTimePasswords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OneTimePasswordRepository extends JpaRepository<OneTimePasswords, Long> {
    Optional<OneTimePasswords> findByUserIdAndOtp(Long userId, Integer Otp);
    Optional<OneTimePasswords> findByUserIdAndOtpAndType(Long userId, Integer otp, String type);
}
