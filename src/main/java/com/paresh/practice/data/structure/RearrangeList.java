package com.paresh.practice.data.structure;

public class RearrangeList {

    public static void main (String args[]){
    LinkedListSelfPractice<Integer> list = new LinkedListSelfPractice<>();
    list.append(1);
    list.append(2);
    list.append(3);
    list.append(4);
    list.append(5);
    list.append(6);
    list.print();
    //input list 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
    //output rearrange list 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null
    list.rearrangeList();
    list.print();


    }
}
