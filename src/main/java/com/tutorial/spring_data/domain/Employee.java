package com.tutorial.spring_data.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate employDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getEmployDate() {
        return employDate;
    }

    public void setEmployDate(LocalDate employDate) {
        this.employDate = employDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", employDate=" + employDate +
                '}';
    }

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate employDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.employDate = employDate;
    }

    public Employee() {
        super();
    }
}
