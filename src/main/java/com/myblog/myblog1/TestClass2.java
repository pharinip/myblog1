package com.myblog.myblog1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestClass2 {

    public static void main(String[] args) {

        List<String> names= Arrays.asList("Aarthi","sudha","Ashok","Ashwin","Swathi");

        List<String> startsWithA=names.stream().filter(name->name.startsWith("A")).collect(Collectors.toList());
        System.out.println(startsWithA);

    }
}
