package com.example.backend.service;

import com.example.backend.dto.SeatResponse;
import com.example.backend.entity.Employee;
import com.example.backend.entity.Seat;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<SeatResponse> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();

        List<Employee> employees = employeeRepository.findBySeatIsNotNull();

        Map<Long, Employee> employeeMap = new HashMap<>();

        for (Employee employee : employees) {

            employeeMap.put(
                    employee.getSeat().getFloorSeatSeq(),
                    employee
            );
        }

        List<SeatResponse> result = new ArrayList<>();

        for (Seat seat : seats) {

            SeatResponse response = new SeatResponse();

            response.setSeatId(seat.getFloorSeatSeq());
            response.setFloorNo(seat.getFloorNo());
            response.setSeatNo(seat.getSeatNo());

            Employee employee = employeeMap.get(seat.getFloorSeatSeq());

            if (employee != null) {
                response.setOccupied(true);
                response.setEmployeeId(employee.getEmployeeId());
            } else {
                response.setOccupied(false);

            }
            result.add(response);
        }
        return result;
    }

    @Transactional
    public void clearSeat(Long seatId) {

        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new RuntimeException("Seat not found"));

        Optional<Employee> employee = employeeRepository.findBySeat(seat);

        if (employee.isPresent()) {
            Employee e = employee.get();
            e.setSeat(null);
            employeeRepository.save(e);
        }
    }
}
