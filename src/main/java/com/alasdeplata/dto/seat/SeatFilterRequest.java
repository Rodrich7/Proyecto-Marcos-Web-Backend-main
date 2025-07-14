package com.alasdeplata.dto.seat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatFilterRequest {
    private Long flightId;
    private String seatStatus;
    private String seatType;
}
