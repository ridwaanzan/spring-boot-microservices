package com.microservice.membership.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fitness_packages")
public class FitnessPackage implements Serializable {
    private static final long serialVersionUID = 6323087094928975583L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "fitnessPackage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FitnessPackageDetail> fitnessPackageDetails;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "duration")
    private Integer duration;

    @Override
    public String toString() {
        return "FitnessPackage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }

}
