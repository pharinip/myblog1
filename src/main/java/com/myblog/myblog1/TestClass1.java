package com.myblog.myblog1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestClass1 {

    public static void main(String[] args) {

        List<Login1> logins= Arrays.asList(
                new Login1("mike","mike@1"),
                new Login1("sam","sam@1")
        );

        List<LoginDto1> dtos=logins.stream().map(login->mapTo(login)).collect(Collectors.toList());
        System.out.println(dtos);


    }
    static LoginDto1 mapTo(Login1 login){
        LoginDto1 loginDto=new LoginDto1();
        loginDto.setUserName(login.getUserName());
        loginDto.setPassword(login.getPassword());
        return loginDto;
    }
}
