package com.alasdeplata.models;

import java.time.LocalDateTime;
import java.util.List;

import com.alasdeplata.enums.FlightStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "flight_number", unique = true)
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Destination origin;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @OneToMany(mappedBy = "flight")
    @JsonIgnore
    private List<FlightAdditionalService> flightAdditionalServiceList;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;
}
