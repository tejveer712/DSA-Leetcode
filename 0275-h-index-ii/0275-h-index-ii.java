class Solution {

    public int hIndex(int[] citations) {

        int n = citations.length;

        // Binary Search on INDEX, not on the answer.
        // We want to find the FIRST index where:
        // citations[mid] >= n - mid
        //
        // Why?
        // n - mid = number of papers from mid to the end.
        // citations[mid] = minimum citation count among those papers (because array is sorted).
        //
        // If citations[mid] >= n - mid,
        // then all remaining papers have at least (n - mid) citations,
        // so (n - mid) is a valid h-index.

        int low = 0;
        int high = n - 1;

        // Stores the first valid index.
        // If no valid index is found, ans remains n,
        // and h-index becomes 0.
        int ans = n;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Check if this index can form a valid h-index.
            if (citations[mid] >= n - mid) {

                // Current index is valid.
                // Save it and try to find an earlier valid index
                // because an earlier index means:
                // -> More papers remaining
                // -> Larger possible h-index.
                ans = mid;
                high = mid - 1;

            } else {

                // Not enough citations.
                // Need to move right where citation values are larger.
                low = mid + 1;
            }
        }

        // Number of papers from the first valid index to the end
        // is the maximum possible h-index.
        return n - ans;
    }
}

/*
==========================
Revision Notes
==========================

1. What was the answer being searched?
--------------------------------------
We were NOT binary searching the h-index.

We were binary searching the FIRST index where:

    citations[mid] >= n - mid

Once that index is found,

    h-index = n - firstValidIndex


------------------------------------------------------------

2. What made the condition work?
--------------------------------

For every index:

    n - mid      -> Number of papers remaining.
    citations[mid] -> Minimum citation count among those papers
                      (because the array is sorted).

If

    citations[mid] >= n - mid

then every remaining paper has at least (n - mid) citations.

Therefore,

    h = n - mid

is a valid h-index.


------------------------------------------------------------

3. Why was Binary Search correct?
---------------------------------

The condition is monotonic.

Moving right:

- citations[mid] increases (or stays the same).
- n - mid decreases.

Therefore, once

    citations[mid] >= n - mid

becomes true, it will remain true for every index after it.

Pattern:

False False False True True True

This is exactly the "First True" Binary Search pattern.


------------------------------------------------------------

4. What was the monotonic property?
-----------------------------------

Index:      0   1   2   3   4
Citation:   0   1   3   5   6
Papers:     5   4   3   2   1

Condition:

0 >= 5   -> False
1 >= 4   -> False
3 >= 3   -> True
5 >= 2   -> True
6 >= 1   -> True

Once the condition becomes TRUE,
it never becomes FALSE again.


------------------------------------------------------------

5. Could I recognize this pattern faster next time?
----------------------------------------------------

Clues:

✓ Array is already sorted.

✓ Time complexity required is O(log n).

✓ The question depends on the position (index),
  not directly on the answer.

✓ The condition changes only once
  (False -> True or True -> False).

These are strong indicators of
Binary Search on INDEX.


------------------------------------------------------------

Key Observation
---------------

At every index:

Remaining Papers = n - mid

Minimum Citation = citations[mid]

If

    citations[mid] >= n - mid

then all remaining papers satisfy the h-index condition.


------------------------------------------------------------

Binary Search Pattern Used
--------------------------

First True Binary Search

while(low <= high){

    if(condition(mid)){
        ans = mid;
        high = mid - 1;
    }
    else{
        low = mid + 1;
    }
}

Answer:

    n - ans


------------------------------------------------------------

Time Complexity  : O(log n)

Space Complexity : O(1)

------------------------------------------------------------
*/