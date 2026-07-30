class Solution {

    // Counts how many elements in the matrix are <= mid.
    // Uses the "staircase" traversal starting from the bottom-left corner.
    int checkSmall(int[][] matrix, int n, int m, int mid){

        int row = n - 1;
        int col = 0;
        int count = 0;

        // Traverse until we go out of bounds.
        while(row >= 0 && col < m){

            if(matrix[row][col] <= mid){

                // Since the current element is <= mid,
                // every element above it in the same column
                // will also be <= mid (because columns are sorted).
                count += row + 1;

                // Move to the next column.
                col++;

            }else{

                // Current element is greater than mid,
                // so move upward to smaller values.
                row--;
            }
        }

        return count;
    }

    public int kthSmallest(int[][] matrix, int k) {

        int n = matrix.length;
        int m = matrix[0].length;

        // Smallest possible answer.
        int low = matrix[0][0];

        // Largest possible answer.
        int high = matrix[n - 1][m - 1];

        int res = -1;

        // Binary Search on the answer (value range).
        while(low <= high){

            int mid = low + (high - low) / 2;

            // Count numbers <= mid.
            int count = checkSmall(matrix, n, m, mid);

            // Not enough numbers <= mid.
            // Need a larger value.
            if(count < k){
                low = mid + 1;
            }
            else{

                // mid can be the answer.
                // Try to find a smaller valid value.
                res = mid;
                high = mid - 1;
            }
        }

        return res;
    }
}


/*
==========================
Revision Notes
==========================

Pattern:
- Binary Search on Answer

How to recognize this problem:
- Matrix rows and columns are sorted.
- Asked to find the kth smallest element.
- Not asked to actually sort the matrix.
- The answer lies within a range of values.
- We can efficiently count how many elements are <= any chosen value.

----------------------------------------------------

Binary Search Space

low  = smallest element = matrix[0][0]

high = largest element = matrix[n-1][m-1]

Search over the VALUE RANGE, not over matrix indices.

----------------------------------------------------

Helper Function Idea

For any guessed value (mid),
count how many elements are <= mid.

If count < k
    -> answer must be larger.

Else
    -> mid is a possible answer.
       Try finding a smaller valid value.

----------------------------------------------------

Why does the counting work?

Start from the bottom-left corner.

If matrix[row][col] <= mid

then every element above it in the same column
is also <= mid because columns are sorted.

So we can directly add:

row + 1

elements at once.

Then move right.

Otherwise,

matrix[row][col] > mid

Move upward because values decrease.

This visits at most:

n rows + n columns

=> O(n)

----------------------------------------------------

Monotonic Property

As mid increases,

count(elements <= mid)

can only increase or remain the same.

Example

mid = 10 -> count = 5

mid = 12 -> count = 7

mid = 15 -> count = 9

Since the count is monotonic,
Binary Search is applicable.

----------------------------------------------------

Why return the first value whose count >= k?

Suppose

count(12) = 7

count(13) = 8

k = 8

13 is the first value that has at least k numbers
less than or equal to it.

That is exactly the kth smallest element.

----------------------------------------------------

Time Complexity

Binary Search:
O(log(MaxValue - MinValue))

Each helper call:
O(n)

Overall:

O(n * log(MaxValue - MinValue))

----------------------------------------------------

Space Complexity

O(1)

No extra data structures are used.

----------------------------------------------------

Key Takeaway

Whenever you can:

1. Guess an answer.
2. Efficiently count how many elements satisfy it.
3. That count is monotonic.

Think:

Binary Search on Answer.
*/