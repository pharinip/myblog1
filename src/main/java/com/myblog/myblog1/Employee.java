package com.myblog.myblog1;

public class Employee {

   private String userName;
   private int age;
   private String city;

    public Employee(String userName, int age, String city) {
        this.userName = userName;
        this.age = age;
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}
