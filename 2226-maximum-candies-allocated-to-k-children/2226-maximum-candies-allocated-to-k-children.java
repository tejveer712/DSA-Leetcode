class Solution {

    // Helper function checks whether it is possible to give
    // 'mid' candies to each of the k children.
    boolean help(int[] candies, long k, int mid) {

        // Total number of piles of size 'mid' that can be created
        long availablePiles = 0;

        for (int i = 0; i < candies.length; i++) {

            // Integer division gives the number of complete sub-piles
            // of size 'mid' that can be formed from the current pile.
            // Example:
            // 8 candies, mid = 3
            // 8 / 3 = 2  -> Two complete piles of size 3
            // Remaining 2 candies cannot be used because
            // piles cannot be merged.
            availablePiles += candies[i] / mid;

            // Early exit optimization.
            // Once we already have enough piles for all children,
            // there is no need to process the remaining piles.
            if (availablePiles >= k) {
                return true;
            }
        }

        // Not enough piles of size 'mid'
        return false;
    }

    public int maximumCandies(int[] candies, long k) {

        // Minimum possible candies per child
        int low = 1;

        // Maximum possible candies per child
        // cannot exceed the largest pile.
        int high = 0;
        for (int candy : candies) {
            high = Math.max(high, candy);
        }

        // Stores the maximum feasible answer.
        int ans = 0;

        while (low <= high) {

            // Candidate answer
            int mid = low + (high - low) / 2;

            if (help(candies, k, mid)) {

                // mid is feasible.
                // Try to maximize the answer.
                ans = mid;
                low = mid + 1;

            } else {

                // mid is too large.
                // Search smaller values.
                high = mid - 1;
            }
        }

        return ans;
    }
}

/*
======================== REVISION NOTES ========================

PATTERN:
Binary Search on Answer

---------------------------------------------------------------
Answer Space
---------------------------------------------------------------
Search on:
Number of candies each child should receive.

Range:
low = 1
high = maximum pile size

---------------------------------------------------------------
Helper Function
---------------------------------------------------------------
Question:
If every child receives 'mid' candies,
can we satisfy at least k children?

For every pile:

candies[i] / mid

gives the number of complete sub-piles of size 'mid'.

Example:

Pile = 13
mid = 5

13 / 5 = 2

Possible piles:
5, 5, 3

Only two complete piles are useful.

---------------------------------------------------------------
Why do we add all quotients?
---------------------------------------------------------------
Each original pile is independent.

Children can receive candies from only ONE pile.

Leftover candies from different piles
CANNOT be merged.

Therefore,

Total available piles =
Σ (candies[i] / mid)

---------------------------------------------------------------
Monotonic Property
---------------------------------------------------------------
If 'mid' candies per child is possible,

then every smaller value is also possible.

Example:

5 works
↓

4 works
↓

3 works

Hence binary search.

---------------------------------------------------------------
Binary Search Rule
---------------------------------------------------------------
Possible(mid)
    ans = mid
    low = mid + 1

Not Possible(mid)
    high = mid - 1

---------------------------------------------------------------
Complexity
---------------------------------------------------------------
Helper  : O(n)

Binary Search : O(log(maxCandy))

Overall:

O(n log(maxCandy))

Space:

O(1)

---------------------------------------------------------------
Recognition Clues
---------------------------------------------------------------
✔ Maximize / Minimize answer

✔ Can guess an answer

✔ Can write a helper to check feasibility

✔ Feasibility is monotonic

=> Binary Search on Answer

===============================================================
*/