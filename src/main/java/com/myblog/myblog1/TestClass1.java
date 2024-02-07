package com.myblog.myblog1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestClass1 {

    public static void main(String[] args) {

        List<Login> logins= Arrays.asList(
                new Login("mike","mike@1"),
                new Login("sam","sam@1")
        );

        List<LoginDto> dtos=logins.stream().map(login->mapTo(login)).collect(Collectors.toList());
        System.out.println(dtos);


    }
    static LoginDto mapTo(Login login){
        LoginDto loginDto=new LoginDto();
        loginDto.setUserName(login.getUserName());
        loginDto.setPassword(login.getPassword());
        return loginDto;
    }
}
