package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"floor_no", "seat_no"})})
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long floorSeatSeq;

    @Column(name = "floor_no")
    private String floorNo;
    @Column(name = "seat_no")
    private String seatNo;
}
