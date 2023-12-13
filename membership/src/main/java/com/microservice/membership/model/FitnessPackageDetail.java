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
@Table(name = "fitness_package_details")
public class FitnessPackageDetail implements Serializable {
    private static final long serialVersionUID = -896349676469681829L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fitness_package_id", nullable = false)
    private FitnessPackage fitnessPackage;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private Integer exercise_time;

    @Override
    public String toString() {
        return "FitNessPackageDetail{" +
                "id='" + id + "'" +
                ", name='"+ name +"'" +
                ", description='"+ description +"'" +
                ", exercise_time='"+ exercise_time +"'" +
                "}";
    }
}
