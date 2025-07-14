package com.alasdeplata.models;

import com.alasdeplata.enums.Continent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String country;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Continent continent;

    @Column(name = "airport_code", unique = true)
    private String airportCode;
    @Column(nullable = true)
    private Double latitude;

    @Column(nullable = true)
    private Double longitude;

}
