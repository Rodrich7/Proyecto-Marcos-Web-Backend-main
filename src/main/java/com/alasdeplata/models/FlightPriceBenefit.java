package com.alasdeplata.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "flight_price_benefits")
public class FlightPriceBenefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_price_id")
    private FlightPrice flightPrice;

    @ManyToOne
    @JoinColumn(name = "benefit_id")
    private Benefit benefit;

    private String value; // Ej: "Incluido", "No incluido", "Con cargo", "10kg", etc.
    private String extraInfo; // Opcional: detalles adicionales
}