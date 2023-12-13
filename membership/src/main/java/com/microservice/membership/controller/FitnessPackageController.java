package com.microservice.membership.controller;

import com.microservice.membership.dto.ResponseTemplate;
import com.microservice.membership.service.FitnessPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FitnessPackageController {
    @Autowired
    private FitnessPackageService fitnessPackageService;

    @GetMapping("/getallfitnesspackage")
    public ResponseTemplate getAllFitnessPackage() {
        return fitnessPackageService.getAllFitnessPackage();
    }

    @PostMapping("/getfitnesspackage/{id}")
    public ResponseTemplate getFitnessPackage(@PathVariable Long id) {
        return fitnessPackageService.getFitnessPackage(id);
    }
}
