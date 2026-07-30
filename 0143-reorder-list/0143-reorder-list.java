/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    // Reverse a linked list and return the new head
    private ListNode reverse(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            // Store the next node before changing the link
            ListNode next = curr.next;

            // Reverse the pointer
            curr.next = prev;

            // Move both pointers one step ahead
            prev = curr;
            curr = next;
        }

        // 'prev' becomes the new head after reversal
        return prev;
    }

    public void reorderList(ListNode head) {

        // No need to reorder if the list has 0, 1 or 2 nodes
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        // Find the middle of the linked list
        // 'prev' will always point to the node before 'slow'
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second;

        if (fast == null) {
            // Even number of nodes
            // Example: 1 -> 2 -> 3 -> 4
            // slow = 3
            second = slow;
        } else {
            // Odd number of nodes
            // Example: 1 -> 2 -> 3 -> 4 -> 5
            // slow = 3
            second = slow.next;
        }

        // Split the linked list into two halves
        if (fast == null) {
            prev.next = null;
        } else {
            slow.next = null;
        }

        // Reverse the second half
        second = reverse(second);

        // Merge the two halves alternately
        ListNode first = head;

        while (second != null) {

            // Save the next nodes before modifying pointers
            ListNode nextFirst = first.next;
            ListNode nextSecond = second.next;

            // Insert one node from the second half
            first.next = second;

            // Connect it back to the first half
            second.next = nextFirst;

            // Move both pointers forward
            first = nextFirst;
            second = nextSecond;
        }
    }
}

/*
==========================
Revision Notes
==========================

Pattern:
---------
This problem combines three linked list patterns:
1. Find Middle (Slow & Fast Pointers)
2. Reverse Linked List
3. Merge Two Linked Lists Alternately

Algorithm:
----------
1. Find the middle of the linked list.
2. Split the list into two independent halves.
3. Reverse the second half.
4. Merge nodes alternately from the first and second halves.

Example:
--------
Input:
1 -> 2 -> 3 -> 4 -> 5

Step 1:
First  = 1 -> 2 -> 3
Second = 4 -> 5

Step 2:
Reverse second half

5 -> 4

Step 3:
Merge alternately

1 -> 5 -> 2 -> 4 -> 3

Time Complexity:
----------------
O(n)
- Find middle      : O(n)
- Reverse second   : O(n)
- Merge            : O(n)

Overall: O(n)

Space Complexity:
-----------------
O(1)

Important Interview Points:
---------------------------
1. Always split the list before reversing.
   Otherwise the original links remain connected.

2. Save next pointers before changing links.

   nextFirst = first.next;
   nextSecond = second.next;

3. During merge:
   first -> second -> nextFirst

4. For odd length:
   Middle node stays at the end.

5. For even length:
   Second half starts at 'slow'.

Common Mistakes:
----------------
✗ Forgetting to split the list.
✗ Losing the remaining list by not storing next pointers.
✗ Returning a new head (the problem modifies the list in place).
✗ Forgetting the base case for very small lists.

Follow-up (Interview):
----------------------
Q. Why do we save nextFirst and nextSecond before changing pointers?

A.
Changing first.next or second.next overwrites the original links.
Without saving them first, we lose access to the remaining nodes of
the two halves and cannot continue merging correctly.
*/