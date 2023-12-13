package com.microservice.membership.service;

import com.microservice.membership.dto.ResponseTemplate;
import com.microservice.membership.exception.DataNotFoundException;
import com.microservice.membership.model.FitnessPackage;
import com.microservice.membership.repository.FitnessPackagesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FitnessPackageService {
    private final FitnessPackagesRepository fitnessPackagesRepository;

    @Autowired
    public FitnessPackageService(FitnessPackagesRepository fitnessPackagesRepository) {
        this.fitnessPackagesRepository = fitnessPackagesRepository;
    }

    public ResponseTemplate getAllFitnessPackage() {
        log.info("Start getAllFitnessPackage");

        try {
            List<FitnessPackage> fitnessPackages = fitnessPackagesRepository.findAll();
            log.info("fitness packages: {}", fitnessPackages);

            return ResponseTemplate.builder()
                    .responseCode(9000)
                    .isSuccess(true)
                    .message("Berhasil mengambil data")
                    .payload(fitnessPackages)
                    .build();

        } catch (Exception e) {
            return ResponseTemplate.builder()
                    .responseCode(4040)
                    .isSuccess(false)
                    .message(e.getMessage())
                    .payload("")
                    .build();
        }
    }

    public ResponseTemplate getFitnessPackage(Long id) throws DataNotFoundException {
        log.info("Start getFitnessPackage");
        log.info("getFitnessPackage requestPath value: {}", id);

        try {
            Optional<FitnessPackage> fitnessPackages = fitnessPackagesRepository.findById(id);

            if (fitnessPackages.isEmpty()) {
                throw new DataNotFoundException("Fitness package tidak di temukan.");
            }

            return ResponseTemplate.builder()
                    .responseCode(9000)
                    .isSuccess(true)
                    .message("Berhasil mengambil data")
                    .payload(fitnessPackages.get())
                    .build();

        } catch (DataNotFoundException e) {
            return ResponseTemplate.builder()
                    .responseCode(4040)
                    .isSuccess(false)
                    .message(e.getMessage())
                    .payload("")
                    .build();
        } catch (Exception e) {
            return ResponseTemplate.builder()
                    .responseCode(6666)
                    .isSuccess(false)
                    .message(e.getMessage())
                    .payload("")
                    .build();
        }
    }
}
