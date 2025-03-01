package com.paresh.practice.data.structure;

public class LinkedListSelfPractice<D> {

    public void rearrangeList() {
        //input 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
        //output 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null
        // leetcode problem 143
        /**
         * ### Problem Recap:
         * Given a singly linked list, reorder it in the following way:
         *
         * 1. The first node is followed by the last node.
         * 2. The second node is followed by the second-last node.
         * 3. The third node is followed by the third-last node, and so on.
         *
         * For example, if the input is:
         *
         * ```
         * 1 -> 2 -> 3 -> 4 -> 5
         * ```
         *
         * The output should be:
         *
         * ```
         * 1 -> 5 -> 2 -> 4 -> 3
         * ```
         *
         * ### Step-by-Step Logic:
         *
         * #### **Step 1: Find the middle of the list**
         *
         * To reorder the list, the first thing we need to do is **split the list into two halves**. To do that, we need to find the "middle" of the list.
         *
         * - We use the **slow and fast pointer technique**:
         *   - The `slow` pointer moves one step at a time.
         *   - The `fast` pointer moves two steps at a time.
         * - When the `fast` pointer reaches the end of the list, the `slow` pointer will be at the middle.
         *
         * For example, in the list:
         *
         * ```
         * 1 -> 2 -> 3 -> 4 -> 5
         * ```
         *
         * After the **slow and fast pointers** run through the list:
         * - `slow` will be at **3**, which is the middle of the list.
         * - `fast` will reach the end, i.e., **5**.
         *
         * #### **Step 2: Split the list into two halves**
         *
         * Once we've found the middle of the list, we split the list into two parts:
         *
         * - **First half**: From the head of the list to the middle.
         * - **Second half**: From the node after the middle to the end.
         *
         * For example:
         *
         * - First half: `1 -> 2 -> 3`
         * - Second half: `4 -> 5`
         *
         * #### **Step 3: Reverse the second half**
         *
         * Now, we need to reverse the second half of the list because we want to interleave nodes from the first half and the second half in a specific order.
         *
         * The second half is `4 -> 5`, and after reversing it, it becomes `5 -> 4`.
         *
         * #### **Step 4: Merge the two halves**
         *
         * Now that we have:
         * - The first half: `1 -> 2 -> 3`
         * - The reversed second half: `5 -> 4`
         *
         * We can start merging the two halves. We alternate between the nodes from the first half and the reversed second half.
         *
         * - Start with the first node from the first half (`1`).
         * - Then add the first node from the reversed second half (`5`).
         * - Next, add the second node from the first half (`2`).
         * - Then add the second node from the reversed second half (`4`).
         * - Finally, add the last node from the first half (`3`).
         *
         * The result will be:
         *
         * ```
         * 1 -> 5 -> 2 -> 4 -> 3
         * ```
         *
         * ### Summary of Steps:
         * 1. **Find the middle** of the linked list using the slow and fast pointers.
         * 2. **Split the list** into two halves.
         * 3. **Reverse the second half** of the list.
         * 4. **Merge the two halves** by alternating nodes from the first half and the reversed second half.
         *
         * This approach ensures that we reorder the list in the desired pattern.
         *
         * ### Time Complexity:
         * - Finding the middle of the list: **O(n)** (since we traverse the list once).
         * - Reversing the second half: **O(n/2)** = **O(n)**.
         * - Merging the two halves: **O(n)**.
         *
         * So, the overall time complexity is **O(n)**.
         *
         * ### Space Complexity:
         * - We don't use any extra space except for a few pointers, so the space complexity is **O(1)**.
         *
         * This solution efficiently solves the problem in linear time with constant space.
         */

        if (head == null || head.next == null) {
            return; // Nothing to reorder
        }

        // Step 1: Find the middle of the linked list
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Split the list into two halves
        Node secondHalf = slow.next;
        slow.next = null; // Break the list into two parts

        // Step 3: Reverse the second half
        secondHalf = reverseList(secondHalf);

        // Step 4: Merge the two halves
        Node firstHalf = head;
        while (secondHalf != null) {
            Node tmp1 = firstHalf.next;
            Node tmp2 = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = tmp1;

            firstHalf = tmp1;
            secondHalf = tmp2;
        }

    }

    private Node reverseList(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
// 1->2->3->4->5
// 2
// 1->null
// prev = 1->null
// current = 2->3->4->5
// next = 3->4->5
// current.next = prev = 1->null
// prev = 2->1->null
// current = next = 3->4->5
// 2->1->null
// 3->4->5


        return prev;
    }

    static class Node<D> {
        D data;
        Node next;

        // Node constructor with only data as argument
        public Node(D data) {
            this.data = data;
            // By default, the next node is null when a new node is created
            this.next = null;
        }
    }

    // Head of the linked list
    private Node head;

    //constructor name same as classname
    //no returntype in constructor
    //
    public LinkedListSelfPractice() {
        this.head = null;
    }

    //add at end
    public void append(D data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node last = head;
            while (null != last.next) {
                last = last.next;
            }
            last.next = newNode;
        }
    }

    //add at  beginning
    //no need to check head is null just create new node and newnode.next = head
    public void prepend(D data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    //delete node
    public void delete(D data) {
        if (null == head) {
            System.out.println("List is empty, cannot delete");
        }

        // If the head node is the one to be deleted
        else if (head.data == data) {
            head = head.next;
        } else {
            Node current = head;
            Node previous = null;
            /**
             * Always check current == null or current != null
             * current.next == null is problamatic
             */
            while (current != null && current.data != data) {
                previous = current;
                current = current.next;
            }
            /**
             * Always check current == null or current != null
             * current.next == null is problamatic
             */
            if (current == null) {
                System.out.println("Item not found");
            } else {
                previous.next = current.next;
            }
        }
    }

    public void addAtPos(int pos, D data) {
        if (null == head) {
            System.out.println("List is empty, cannot add at pos " + pos);
        } else {
            Node current = head;
            Node previous = null;
            int i = 1;
            while (i < pos) {
                previous = current;
                current = current.next;
                i++;
            }
            Node newNode = new Node(data);
            if (previous == null) {
                newNode.next = current;
                head = newNode;
            } else {
                previous.next = newNode;
                newNode.next = current;
            }

        }
    }

    //print linked list

    /**
     * Always check current == null or current != null
     * current.next == null is problamatic
     */
    public void print() {
        if (null == head) {
            System.out.println("List is Empty");
        } else {
            Node current = head;
            /**
             * Always check current == null or current != null
             * current.next == null is problamatic
             */
            while (null != current) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.print("null \n");
        }
    }

    public static void main(String... args) {
        LinkedListSelfPractice<Integer> linkList = new LinkedListSelfPractice<>();
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
        linkList.addAtPos(5, 10);
        linkList.append(10);
        linkList.print();
        linkList.addAtPos(1, 20);
        linkList.print();
        linkList.addAtPos(2, 30);
        linkList.print();
    }
}