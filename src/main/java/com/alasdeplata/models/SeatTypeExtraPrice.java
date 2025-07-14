package com.alasdeplata.models;

import java.math.BigDecimal;

import com.alasdeplata.enums.SeatType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "seat_type_extra_price")
@Data
public class SeatTypeExtraPrice {
    @Id
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private BigDecimal extraPrice;
}
