package com.tutorial.spring_data.beans;

import com.tutorial.spring_data.domain.Employee;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class EmployeeGenerator {
    private List<String> firstNames = Arrays.asList(new String[] {"Adam", "Alan", "Catherine", "Mark", "Kate"});
    private List<String> lastNames = Arrays.asList(new String[] {"Noone", "Hostile", "Desperado", "Insane", "Crazy"});
    private Random rand = new Random();


    public Employee generate() {
        return new Employee(
                firstNames.get(rand.nextInt(firstNames.size())),
                lastNames.get(rand.nextInt(lastNames.size())),
                new BigDecimal(String.valueOf(rand.nextInt(10000))),
                LocalDate.of(rand.nextInt(60)+1950, rand.nextInt(12)+1, rand.nextInt(28)+1)
                );
    }

}
