class Solution {

    // Returns the sum of the squares of all digits of the given number.
    // Example: 19 -> 1² + 9² = 82
    int square(int n) {
        int sum = 0;

        while (n > 0) {
            int d = n % 10;      // Extract the last digit.
            n = n / 10;          // Remove the last digit.
            sum = sum + d * d;   // Add the square of the digit.
        }

        return sum;
    }

    public boolean isHappy(int n) {

        // Initialize both pointers at the starting number.
        int slow = n;
        int fast = n;

        // Continue until the fast pointer reaches 1.
        // If it reaches 1, the number is happy.
        while (fast != 1) {

            // Move slow pointer by one step.
            slow = square(slow);

            // Move fast pointer by two steps.
            fast = square(square(fast));

            // If both pointers meet at a value other than 1,
            // a cycle exists, so the number is not happy.
            if (slow == fast && slow != 1) {
                return false;
            }
        }

        // Fast pointer reached 1, so the number is happy.
        return true;
    }
}

/*
=========================
Revision Notes
=========================

Pattern:
- Floyd's Cycle Detection (Tortoise and Hare)

Key Observation:
- Each number generates the next number by replacing it with the
  sum of the squares of its digits.
- This creates a sequence similar to a linked list.
- If the sequence reaches 1 -> Happy Number.
- Otherwise, it eventually enters a cycle.

Why Floyd's Algorithm Works:
- Treat square(n) as the "next" pointer.
- slow moves one step:
      slow = square(slow)
- fast moves two steps:
      fast = square(square(fast))
- If a cycle exists, slow and fast will eventually meet.
- If fast reaches 1 first, the sequence terminates successfully.

Common Mistake:
❌ slow = square(n);
❌ fast = square(square(n));

These repeatedly calculate from the original input n,
so the pointers never move through the sequence,
resulting in an infinite loop (Time Limit Exceeded).

Correct Update:
✔ slow = square(slow);
✔ fast = square(square(fast));

Time Complexity:
- O(log n) per square() call.
- The sequence reaches either 1 or a small cycle quickly.
- Overall: O(log n)

Space Complexity:
- O(1)

Interview Tip:
Whenever you see:
1. A sequence generated from the previous value.
2. Possibility of entering a cycle.

Think:
→ Floyd's Cycle Detection.
*/