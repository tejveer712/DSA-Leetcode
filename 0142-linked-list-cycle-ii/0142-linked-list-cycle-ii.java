/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        ListNode temp = head;

        // Phase 1: Detect whether a cycle exists
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            // Cycle detected
            if (slow == fast) {
                slow = head;
                // Phase 2: Find the starting node of the cycle
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        // No cycle exists
        return null;
    }
}

/*
========================
Revision Notes
========================

1. Floyd's Algorithm has two phases:
   - Detect a meeting point using slow and fast pointers.
   - Move one pointer to the head and keep the other at the meeting point.
     Move both one step at a time until they meet again.

2. The first meeting point is NOT the start of the cycle.

3. The return statement must be inside the `if (slow == fast)` block.
   Returning after the first iteration causes the algorithm to terminate
   before detecting a cycle.

4. Always use:
       while (fast != null && fast.next != null)
   to avoid NullPointerException.

5. Time Complexity: O(n)
6. Space Complexity: O(1)
*/