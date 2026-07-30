// class Solution {

//     public boolean searchMatrix(int[][] matrix, int target) {

//         // Number of rows and columns.
//         int n = matrix.length;
//         int m = matrix[0].length;

//         // Binary search over the rows.
//         int low = 0;
//         int high = n - 1;

//         // Stores the row where the target may exist.
//         int row = -1;

//         // ---------------- Binary Search to Find the Candidate Row ----------------
//         while (low <= high) {

//             int mid = low + (high - low) / 2;

//             // If target is smaller than the first element of this row,
//             // search in the upper half.
//             if (target < matrix[mid][0]) {
//                 high = mid - 1;
//             }

//             // If target is greater than the last element of this row,
//             // search in the lower half.
//             else if (target > matrix[mid][m - 1]) {
//                 low = mid + 1;
//             }

//             // Target lies within this row's range.
//             else {
//                 row = mid;
//                 break;
//             }
//         }

//         // No valid row found.
//         if (row == -1) {
//             return false;
//         }

//         // ---------------- Binary Search Inside the Candidate Row ----------------
//         low = 0;
//         high = m - 1;

//         while (low <= high) {

//             int mid = low + (high - low) / 2;

//             if (matrix[row][mid] == target) {
//                 return true;
//             }

//             if (matrix[row][mid] < target) {
//                 low = mid + 1;
//             } else {
//                 high = mid - 1;
//             }
//         }

//         // Target not present.
//         return false;
//     }
// }

/*
======================== REVISION NOTES ========================

Pattern:
- Binary Search on Rows + Binary Search on Columns.

Observation:
- Every row is sorted.
- First element of every row > Last element of previous row.
- Therefore each row represents a non-overlapping sorted range.

How to think:
1. Which row can contain the target?
2. Once the row is found, perform a normal binary search inside that row.

Binary Search on Rows:
- If target < first element of row
      -> search above.
- If target > last element of row
      -> search below.
- Otherwise
      -> target can only exist in this row.

Time Complexity:
- Finding row      : O(log(rows))
- Finding element  : O(log(cols))
- Overall          : O(log(rows) + log(cols))

Space Complexity:
- O(1)

Interview Tip:
Whenever every row represents a unique sorted range,
first locate the correct row and then search within it.
*/




class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {

        // Number of rows and columns.
        int n = matrix.length;
        int m = matrix[0].length;

        // Treat the matrix as one sorted array.
        int low = 0;
        int high = n * m - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Convert 1D index into 2D coordinates.
            int row = mid / m;
            int col = mid % m;

            if (matrix[row][col] == target) {
                return true;
            }

            if (matrix[row][col] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }
}

/*
======================== REVISION NOTES ========================

Pattern:
- Binary Search on a Virtual 1D Array.

Observation:
- Entire matrix is globally sorted.
- We don't need to flatten the matrix physically.
- Just map every 1D index to its 2D position.

Index Mapping:
row = mid / numberOfColumns
col = mid % numberOfColumns

Example:

1  3  5  7
10 11 16 20
23 30 34 60

Virtual Array:

1 3 5 7 10 11 16 20 23 30 34 60

Binary search works exactly as it would on this array.

Time Complexity:
- O(log(rows × cols))

Space Complexity:
- O(1)

Interview Tip:
Whenever the matrix is globally sorted
(last element of previous row < first element of next row),
think of it as one sorted array and use index mapping instead
of creating an extra array.
*/