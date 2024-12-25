package com.paresh.practice.data.structure;

// LinkedList class

/**
 * Making the Node class static is appropriate here because the Node class does
 * not need any instance-specific members from the LinkedList class, and it
 * reduces memory overhead. Therefore, the Node class is self-contained and
 * independent, and the linked list can efficiently manage its nodes.
 *
 * ALso keeping Node class as inner class of LinkedList class provides better
 * encapsulation logically.
 */
public class LinkedListReferenceCode {

    // Node class representing each element in the linked list
    static class Node {
        int data;
        Node next;

        // Constructor to create a new node
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Head of the list
    private Node head;

    // Constructor for LinkedList
    public LinkedListReferenceCode() {
        head = null;
    }

    // Method to add a node at the end
    public void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Method to add a node at the beginning
    public void prepend(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // Method to delete a node by value
    public void delete(int key) {
        // If the list is empty
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        // If the head node is the one to be deleted
        if (head.data == key) {
            head = head.next;
            return;
        }

        Node current = head;
        Node previous = null;

        // Search for the node to be deleted
        while (current != null && current.data != key) {
            previous = current;
            current = current.next;
        }

        // If the key wasn't found
        if (current == null) {
            System.out.println("Node with value " + key + " not found.");
            return;
        }

        // Remove the node
        previous.next = current.next;
    }

    // Method to print the linked list
    public void printList() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Main method to test the LinkedList class
    public static void main(String[] args) {
        LinkedListReferenceCode list = new LinkedListReferenceCode();

        // Adding elements to the list
        list.append(10);
        list.append(20);
        list.append(30);
        list.append(40);
        list.prepend(5);

        // Print the linked list
        System.out.println("Linked List:");
        list.printList();

        // Delete an element
        System.out.println("Deleting node with value 20:");
        list.delete(20);
        list.printList();

        // Try to delete a non-existent node
        System.out.println("Trying to delete a non-existent node (50):");
        list.delete(50);
    }
}
