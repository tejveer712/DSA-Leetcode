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
    public boolean hasCycle(ListNode head) {

        // Initialize both pointers at the head of the linked list
        ListNode slow = head;
        ListNode fast = head;

        // Traverse the list until the fast pointer reaches the end
        while (fast != null && fast.next != null) {

            // Move slow pointer one step
            slow = slow.next;

            // Move fast pointer two steps
            fast = fast.next.next;

            // If both pointers meet, a cycle exists
            if (slow == fast) {
                return true;
            }
        }

        // Fast pointer reached the end, so there is no cycle
        return false;
    }
}

/*
==========================
      REVISION NOTES
==========================

Pattern:
- Fast & Slow Pointer (Floyd's Cycle Detection Algorithm)

Intuition:
- Slow pointer moves one node at a time.
- Fast pointer moves two nodes at a time.
- If a cycle exists, the fast pointer will eventually catch up to the slow pointer.
- If there is no cycle, the fast pointer will reach null.

Algorithm:
1. Initialize both slow and fast pointers at the head.
2. Move slow by one step.
3. Move fast by two steps.
4. If slow == fast, return true.
5. If fast reaches null, return false.

Why does this work?
- Inside a cycle, the fast pointer gains one node on the slow pointer every iteration.
- Eventually, the distance between them becomes zero, so they meet.

Important Condition:
while (fast != null && fast.next != null)

This prevents a NullPointerException when executing:
fast = fast.next.next;

Why use == instead of .equals()?
- We are checking whether both pointers point to the same node in memory.
- We are NOT comparing the values stored inside the nodes.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Recognition Clues:
- Detect a cycle in a linked list.
- Find the middle of a linked list.
- Find the starting node of a cycle.
- Problems where two pointers move at different speeds.

Common Mistakes:
1. Using Node instead of ListNode.
2. Forgetting to check:
      fast != null && fast.next != null
3. Comparing node values:
      slow.val == fast.val
   instead of comparing references:
      slow == fast

Interview One-Liner:
"Use Floyd's Tortoise and Hare algorithm. Move one pointer by one step and another by two steps. If they ever meet, a cycle exists; otherwise, if the fast pointer reaches null, the list has no cycle. This achieves O(n) time and O(1) space."
*/