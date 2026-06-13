package com.example.backend.controller;

import com.example.backend.dto.EmployeeResponse;
import com.example.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PutMapping("/{employeeId}/seat/{seatId}")
    public void assignSeat(@PathVariable String employeeId, @PathVariable Long seatId) {

        employeeService.assignSeat(employeeId, seatId);
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
