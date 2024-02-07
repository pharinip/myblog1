package com.myblog.myblog1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class TestClass3 {

    public static void main(String[] args) {



        List<Integer> num=Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> even=num.stream().filter(n->n%2==0).collect(Collectors.toList());
        System.out.println(even);

       List<Integer> square= even.stream().map(i->i*i).collect(Collectors.toList());
        System.out.println(square);

        int sumOfSquaredValues = square.stream()
                .reduce(0, Integer::sum);


        System.out.println(sumOfSquaredValues);

        


        }

    }

