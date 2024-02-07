package com.myblog.myblog1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeTest {
    public static void main(String[] args) {
        List<Employee> lists= Arrays.asList(
                new Employee("mike",30,"chennai"),
                new Employee("sam",45,"chennai"),
                new Employee("kat",55,"kolar"),
                new Employee("preethi",35,"mysore")
        );

        List<Employee> emps=lists.stream().filter(emp->emp.getAge()>30).collect(Collectors.toList());
        for(Employee e:emps){
            System.out.println( e.getUserName());
            System.out.println( e.getAge());
            System.out.println( e.getCity());

        }
    }
}
