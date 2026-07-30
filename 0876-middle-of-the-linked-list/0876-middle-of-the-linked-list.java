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
    public ListNode middleNode(ListNode head) {

        // Both pointers start from the head.
        ListNode slow = head;
        ListNode fast = head;

        // Move:
        // - slow by 1 step
        // - fast by 2 steps
        // When fast reaches the end, slow will be at the middle.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // For odd-length lists:
        // slow points to the exact middle.
        //
        // For even-length lists:
        // slow points to the second middle,
        // which is what the problem asks for.
        return slow;
    }
}

/*
========================
Revision Notes
========================

Pattern:
- Fast & Slow Pointer (Tortoise and Hare)

Key Idea:
- Slow moves 1 node at a time.
- Fast moves 2 nodes at a time.
- When fast reaches the end, slow has travelled only half the distance,
  so it is at the middle.

Why does this return the SECOND middle?
- Example: 1 -> 2 -> 3 -> 4 -> 5 -> 6

Iteration 0:
slow = 1
fast = 1

Iteration 1:
slow = 2
fast = 3

Iteration 2:
slow = 3
fast = 5

Iteration 3:
slow = 4
fast = null

Loop stops because fast == null.
Answer = 4 (the second middle).

Loop Condition:
while (fast != null && fast.next != null)

Reason:
- We move fast by two nodes.
- Need both fast and fast.next to exist before doing fast.next.next.
- Prevents NullPointerException.

Time Complexity:
O(n)

Space Complexity:
O(1)

Recognition Clues:
Use Fast & Slow Pointers when the problem asks for:
- Middle of a linked list
- Detect a cycle
- Find the start of a cycle
- Happy Number
- Split a linked list into two halves
*/