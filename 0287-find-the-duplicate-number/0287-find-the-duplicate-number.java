class Solution {
    public int findDuplicate(int[] nums) {

        // Initialize both pointers at index 0.
        // We treat the array as a linked list where:
        // Index = Node
        // nums[index] = Next Node
        int slow = 0;
        int fast = 0;

        // Phase 1: Find the intersection point inside the cycle.
        while (true) {

            // Slow pointer moves one step.
            slow = nums[slow];

            // Fast pointer moves two steps.
            fast = nums[nums[fast]];

            // If both pointers meet, a cycle exists.
            if (slow == fast) {

                // Phase 2:
                // Move one pointer back to the beginning.
                // Keep the other at the meeting point.
                slow = 0;

                // Move both one step at a time.
                // They will meet at the entrance of the cycle,
                // which is the duplicate number.
                while (slow != fast) {
                    slow = nums[slow];
                    fast = nums[fast];
                }

                // Duplicate number found.
                return slow;
            }
        }
    }
}

/*
========================
Revision Notes
========================

Pattern:
- Floyd's Tortoise and Hare (Cycle Detection)

Key Observation:
- Since nums contains n + 1 numbers in the range [1, n],
  at least one number must be repeated.
- Think of the array as a linked list:
      Node = Index
      Next Node = nums[index]
- A duplicate value means two indices point to the same node,
  creating a cycle.

Algorithm:
1. Initialize both slow and fast at index 0.
2. Move:
      slow = nums[slow]
      fast = nums[nums[fast]]
3. Continue until they meet inside the cycle.
4. Reset slow to 0.
5. Move both pointers one step at a time.
6. The node where they meet again is the duplicate number.

Why does resetting slow work?
- Let:
      x = Distance from start to cycle entrance
      y = Distance from cycle entrance to meeting point
      c = Length of the cycle
- When slow and fast meet:
      2(x + y) = x + y + k * c
      => x = k * c - y
- This means:
      • One pointer starting from the beginning.
      • One pointer starting from the meeting point.
  After moving one step at a time, both reach the cycle entrance
  simultaneously.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Things to Remember:
- Never modify the array.
- No extra data structures are required.
- The duplicate number is the entrance of the cycle.
- This pattern can also be used in Linked List Cycle II.
*/