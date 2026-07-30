class Solution {

    // Returns the next valid index after making one jump.
    int nextIndex(int currentIndex, int[] nums) {

        int n = nums.length;

        // Handles both positive and negative jumps correctly.
        return ((currentIndex + nums[currentIndex]) % n + n) % n;
    }

    public boolean circularArrayLoop(int[] nums) {

        int n = nums.length;

        // Try every index as a possible starting point.
        for (int start = 0; start < n; start++) {

            // Remember the direction of movement for this traversal.
            boolean forward = nums[start] > 0;

            int slow = start;
            int fast = start;

            while (true) {

                // ---------------- Move Slow Pointer ----------------

                // Stop if slow changes direction.
                if ((nums[slow] > 0) != forward)
                    break;

                slow = nextIndex(slow, nums);

                // ---------------- Move Fast Pointer (1st Jump) ----------------

                // Stop if fast changes direction.
                if ((nums[fast] > 0) != forward)
                    break;

                fast = nextIndex(fast, nums);

                // After the first jump, fast lands on a new index.
                // Check again before making the second jump.
                if ((nums[fast] > 0) != forward)
                    break;

                // ---------------- Move Fast Pointer (2nd Jump) ----------------

                fast = nextIndex(fast, nums);

                // ---------------- Cycle Found ----------------

                if (slow == fast) {

                    // Self-loop (cycle length = 1) is not valid.
                    if (nextIndex(slow, nums) == slow)
                        break;

                    return true;
                }
            }
        }

        return false;
    }
}

/*
======================== REVISION NOTES ========================

Pattern:
- Floyd's Cycle Detection (Slow & Fast Pointer)

Key Observation:
- Every index has exactly one outgoing edge (next index).
- Therefore, the array behaves like a directed graph where each node has one outgoing connection.

---------------------------------------------------------------

1. nextIndex() Helper
---------------------
Instead of writing movement logic everywhere, create a helper.

Formula:
((currentIndex + nums[currentIndex]) % n + n) % n

Why?
- currentIndex + nums[currentIndex] -> raw destination
- % n -> wraps around the circular array
- + n -> fixes negative modulo
- Final % n -> keeps index between 0 and n-1

---------------------------------------------------------------

2. Why start from every index?
------------------------------
A valid cycle can begin from any index.

Example:
0 -> 1

2 -> 4 -> 3 -> 2

If we only start Floyd from index 0,
we would completely miss the cycle starting from index 2.

---------------------------------------------------------------

3. Remember the direction
-------------------------
boolean forward = nums[start] > 0;

The problem requires:
✔ Entire cycle should move only forward
OR
✔ Entire cycle should move only backward

The direction must never change while traversing.

---------------------------------------------------------------

4. Why check direction before every jump?
-----------------------------------------
Suppose:

0 -> 2 -> 3

If index 2 changes direction,
we must stop immediately.

Fast pointer actually performs:

fast = nextIndex(fast)
fast = nextIndex(fast)

So direction must be checked:
- Before first jump
- Before second jump

---------------------------------------------------------------

5. Why reject self-loop?
------------------------
Example:

nums = [3,1,2]

0 -> 0

Although Floyd detects a cycle,
cycle length = 1

Problem requires:
k > 1

Hence:

if(nextIndex(slow, nums) == slow)

Reject it.

---------------------------------------------------------------

6. Why break instead of return false?
-------------------------------------
break:
Current starting index failed.
Try another starting index.

return false:
Only after every starting index has been checked.

---------------------------------------------------------------

Time Complexity:
O(n²)

Space Complexity:
O(1)

---------------------------------------------------------------

Interview Follow-up:
The optimal solution is O(n).

Idea:
After finishing one traversal,
mark every visited node as processed (typically by setting it to 0).

Future traversals skip these processed nodes,
preventing repeated work.

===============================================================
*/