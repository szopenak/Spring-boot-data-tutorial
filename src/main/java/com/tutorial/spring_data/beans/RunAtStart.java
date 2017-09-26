package com.tutorial.spring_data.beans;

import com.tutorial.spring_data.domain.Employee;
import com.tutorial.spring_data.repository.EmployeeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class RunAtStart {

    private final EmployeeRepository employeeRepository;
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    private EmployeeGenerator employeeGenerator;

    @PostConstruct
    public void initializeTest() {
        //generate employees
        for(int i =0; i<50; i++) {
            employeeRepository.save(employeeGenerator.generate());
        }
        //get all employees
        logger.info("UNSORTED EMPLOYEES:");
        final List<Employee> allEmployees = employeeRepository.findAll();
        logger.info("SIZE: "+allEmployees.size());
        allEmployees.forEach(logger::info);

        //get sorted employees
        List<Employee> sorted = employeeRepository.findAll(
                new Sort(
                        new Sort.Order(
                                Sort.Direction.ASC, "firstName"
                        ),
                        new Sort.Order(
                                Sort.Direction.ASC, "lastName"
                        )
                )
        );

        logger.info("SORTED:");
        sorted.forEach(logger::info);

        //use the pages
        Page<Employee> pages = employeeRepository.findAll(new PageRequest(2, 10));
        logger.info("PAGE "+pages.getNumber());
        pages.getContent().forEach(logger::info);

        //use the custom method in repo
        logger.info("FIND ONLY ALANS, IGNORE CASE: ");
        employeeRepository.findByFirstNameIgnoreCase("alaN").forEach(logger::info);

        //use another custom method in repo
        logger.info("FIND ONLY INSANE, ORDER ASC LASTNAME: ");
        employeeRepository.findByLastNameOrderByFirstNameAsc("Insane").forEach(logger::info);

        //count by name
        logger.info("HOW MANY KATE'S:");
        logger.info(employeeRepository.countByFirstName("Kate"));
        try {
            employeeRepository.findEmployeesByEmployDateAfter(LocalDate.of(1999, 5, 5))
                    .whenComplete(logger::info);
            logger.info("AFTER CALLING ASYNC METHOD");
        } catch (Exception e) {
            logger.error("ERROR");
        }

        //native, custom querys
        logger.info("EMPLOYEES WITH SALARY GT 8000:");
        employeeRepository.getEmployeesWithSalaryGreaterThan(new BigDecimal("8000")).forEach(logger::info);
    }

}
