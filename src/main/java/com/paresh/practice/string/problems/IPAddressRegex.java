package com.paresh.practice.string.problems;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

class IPAddressRegex{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String IP = in.next();
            if(IP.equalsIgnoreCase("exit")){
                break;
            }
            System.out.println(IP.matches(new MyRegex().pattern));
        }

    }
}

class MyRegex{
    protected String pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
}

//Write your code here
