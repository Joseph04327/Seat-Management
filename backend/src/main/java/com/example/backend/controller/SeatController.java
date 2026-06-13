package com.example.backend.controller;

import com.example.backend.dto.SeatResponse;
import com.example.backend.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<SeatResponse> getAllSeats() {
        return seatService.getAllSeats();
    }

    @PutMapping("/{seatId}/clear")
    public void clearSeat(@PathVariable Long seatId) {
        seatService.clearSeat(seatId);
    }
}
