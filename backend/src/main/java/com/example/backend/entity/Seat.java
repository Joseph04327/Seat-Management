package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long floor_seat_seq;

    private String floor_no;
    private String seat_no;
}
