package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 5)
    private String employeeId;

    private String name;
    private String email;

    @OneToOne
    @JoinColumn(name = "floor_seat_seq", unique = true)
    private Seat seat;
}
