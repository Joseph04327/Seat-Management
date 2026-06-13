package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatResponse {
    private String seatId;
    private String floorNo;
    private String seatNo;
    private boolean occupied;
    private String employeeId;
}