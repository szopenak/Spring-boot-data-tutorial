package com.tutorial.spring_data.repository;

import com.tutorial.spring_data.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public List<Employee> findByFirstName(String firstName);
    public List<Employee> findByFirstNameIgnoreCase(String firstName);
    public List<Employee> findByLastNameOrderByFirstNameAsc(String firstName);
    public int countByFirstName(String firstName);
    @Async
    public CompletableFuture<List<Employee>> findEmployeesByEmployDateAfter(LocalDate date);
    @Query(
            value = "select * from employee where salary > :value",
            nativeQuery = true
    )
    public List<Employee> getEmployeesWithSalaryGreaterThan(@Param("value") BigDecimal value);

}
