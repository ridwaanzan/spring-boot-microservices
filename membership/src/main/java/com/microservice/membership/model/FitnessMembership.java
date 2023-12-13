package com.microservice.membership.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fitness_memberships")
public class FitnessMembership implements Serializable {
    private static final long serialVersionUID = -3210148467812121875L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "fitness_package_id", nullable = false)
    private FitnessPackage fitnessPackage;

    @Column(name = "remaining_duration")
    private Integer remainingDuration;

    @Column(name = "status")
    private Integer status;
}
