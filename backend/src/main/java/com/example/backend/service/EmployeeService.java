package com.example.backend.service;

import com.example.backend.dto.EmployeeResponse;
import com.example.backend.entity.Employee;
import com.example.backend.entity.Seat;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    public List<EmployeeResponse> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeResponse> result = new ArrayList<>();

        for (Employee employee : employees) {

            EmployeeResponse response = new EmployeeResponse();

            response.setEmployeeId(employee.getEmployeeId());

            response.setName(employee.getName());

            result.add(response);
        }

        return result;
    }
}
