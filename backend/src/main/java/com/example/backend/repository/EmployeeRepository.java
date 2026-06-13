package com.example.backend.repository;

import com.example.backend.entity.Employee;
import com.example.backend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findBySeatIsNotNull();
    Optional<Employee> findByEmployeeId(String employeeId);
    Optional<Employee> findBySeat(Seat seat);
}
