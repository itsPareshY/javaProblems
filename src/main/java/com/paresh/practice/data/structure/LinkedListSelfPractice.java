package com.paresh.practice.data.structure;

public class LinkedListSelfPractice {

    static class Node {
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    //constructor name same as classname
    //no returntype in constructor
    public LinkedListSelfPractice() {
        this.head = null;
    }

    //add at end
    public void append(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
        }
        else{
            Node last = head;
            while (null != last.next ){
                last = last.next;
            }
            last.next = newNode;
        }
    }

    //add at  beginning
    //no need to check head is null just create new node and newnode.next = head
    public void prepend(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    //delete node
    public void delete (int data){
        if (null == head){
            System.out.println("List is empty, cannot delete");
        }

        // If the head node is the one to be deleted
        else if (head.data == data) {
            head = head.next;
        }
        else {
            Node current = head;
            Node previous = null;
            /**
             * Always check current == null or current != null
             * current.next == null is problamatic
             */
            while(current != null && current.data != data){
                previous = current;
                current = current.next;
            }
            /**
             * Always check current == null or current != null
             * current.next == null is problamatic
             */
            if(current == null){
                System.out.println("Item not found");
            }
            else{
                previous.next = current.next;
            }
        }
    }

    //print linked list

    /**
     * Always check current == null or current != null
     * current.next == null is problamatic
     */
    public void print(){
        if(null== head){
            System.out.println("List is Empty");
        }
        else{
            Node current = head;
            /**
             * Always check current == null or current != null
             * current.next == null is problamatic
             */
            while (null != current){
                System.out.print(current.data+" -> ");
                current = current.next;
            }
            System.out.print("null \n");
        }
    }
    public static void main(String ... args){
        LinkedListSelfPractice linkList = new LinkedListSelfPractice();
        linkList.append(3);
        linkList.prepend(2);
        linkList.append(4);
        linkList.prepend(1);
        linkList.append(5);
        linkList.print();
        linkList.delete(3);
        linkList.delete(5);
        linkList.delete(1);
        linkList.print();
        linkList.delete(50);
        linkList.delete(2);
        linkList.delete(4);
        linkList.print();
        linkList.delete(2);
    }
}