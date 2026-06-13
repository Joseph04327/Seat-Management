package com.example.backend.service;

import com.example.backend.entity.Employee;
import com.example.backend.entity.Seat;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Transactional
    public void assignSeat(String employeeId, Long seatId) {

        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow();

        Seat seat = seatRepository.findById(seatId).orElseThrow();

        Optional<Employee> seatOwner = employeeRepository.findBySeat(seat);

        if (seatOwner.isPresent() && !seatOwner.get().getId().equals(employee.getId())) {
            throw new IllegalArgumentException("Seat is already occupied");
        }

        employee.setSeat(seat);

        employeeRepository.save(employee);
    }
}
