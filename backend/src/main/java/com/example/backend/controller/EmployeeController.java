package com.example.backend.controller;

import com.example.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PutMapping("/{employeeId}/seat/{seatId}")
    public void assignSeat(@PathVariable String employeeId, @PathVariable Long seatId) {

        employeeService.assignSeat(employeeId, seatId);
    }
}
