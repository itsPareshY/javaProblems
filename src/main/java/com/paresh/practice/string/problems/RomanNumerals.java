package com.paresh.practice.string.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class RomanNumerals {


    static{

    }

    public int romanToInt(String s) {
        final Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        romanMap.put(null , 0);
        String romanRegex = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        int total = 0;
        if (null != s &&  !s.isEmpty() && s.length() <=15){
            boolean isValid = s.matches(romanRegex);
            if(isValid){
                for(int i = 0 ; i< s.length() ;i++) {
                   int current  = romanMap.get(s.charAt(i));

                    if(i + 1 < s.length()  && current < romanMap.get(s.charAt(i+1))){
                        total -= current;
                    }
                    else {
                        total += current;
                    }
                }
            }
            else {
                return 0;
            }
        }
        return total;
    }

    public static void main(String[] args){
        String s = "MCMXCIV";
        RomanNumerals rn = new RomanNumerals();
       System.out.println(rn.romanToInt(s));
    }
}
