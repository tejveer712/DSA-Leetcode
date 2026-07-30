class Solution {

    // Counts how many numbers in the multiplication table
    // are less than or equal to 'mid'.
    int checkSmall(int m, int n, int mid) {

        int count = 0;

        // Iterate through every row of the multiplication table.
        for (int row = 1; row <= m; row++) {

            /*
             * Row 'row' contains:
             * row*1, row*2, row*3, ... , row*n
             *
             * We need:
             * row * col <= mid
             *
             * => col <= mid / row
             *
             * Therefore, the number of valid elements in this row is:
             * min(n, mid / row)
             *
             * We take the minimum because the table only has 'n' columns.
             */
            count += Math.min(n, mid / row);
        }

        return count;
    }

    public int findKthNumber(int m, int n, int k) {

        // Smallest possible value in the multiplication table.
        int low = 1;

        // Largest possible value in the multiplication table.
        int high = m * n;

        int res = -1;

        // Binary Search on the answer (value), NOT on indices.
        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Count how many numbers in the table are <= mid.
            int count = checkSmall(m, n, mid);

            if (count < k) {

                // Fewer than k numbers are <= mid.
                // The kth smallest must be larger.
                low = mid + 1;

            } else {

                // At least k numbers are <= mid.
                // mid is a possible answer.
                // Try to find an even smaller valid answer.
                res = mid;
                high = mid - 1;
            }
        }

        return res;
    }
}

/*
======================================
Revision Notes
======================================

1. Binary Search on Answer
--------------------------
- We are NOT searching an index.
- We are searching the value of the kth smallest number.
- Search space:
      low = 1
      high = m * n

2. Key Observation
------------------
The multiplication table is sorted both row-wise and column-wise.

For any guessed value 'mid',
we can efficiently count how many numbers are <= mid.

3. Helper Function Logic
------------------------
For row 'i', the values are:

i, 2i, 3i, ... , ni

We need:

i * col <= mid

=> col <= mid / i

Hence,

count in this row = min(n, mid / i)

Add this for every row.

4. Monotonic Property
---------------------
If mid increases,
the number of elements <= mid can only increase (or stay the same).

Example:

mid = 5  -> count = 8
mid = 6  -> count = 10
mid = 7  -> count = 11

Since the count is monotonic,
Binary Search on the answer works.

5. Binary Search Decision
-------------------------
count < k
    -> Not enough numbers <= mid
    -> Search larger values.

count >= k
    -> mid could be the answer
    -> Try to minimize the answer.

6. Pattern Recognition
----------------------
Whenever a problem asks:

- kth smallest value
- kth largest value
- minimum possible answer
- maximum possible answer

and you can write a helper that answers:

"How many values satisfy this guess?"

or

"Is this guess feasible?"

then think:

>>> Binary Search on Answer <<<

Time Complexity:
----------------
Helper Function : O(m)

Binary Search   : O(log(m * n))

Overall:
O(m * log(m * n))

Space Complexity:
-----------------
O(1)
*/