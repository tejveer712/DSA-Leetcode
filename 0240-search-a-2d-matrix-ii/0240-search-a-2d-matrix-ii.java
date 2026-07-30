class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        // Number of rows and columns.
        int n = matrix.length;
        int m = matrix[0].length;

        // Start from the top-right corner.
        // Left values are smaller.
        // Down values are larger.
        int row = 0;
        int col = m - 1;

        while (row < n && col >= 0) {

            if (matrix[row][col] == target) {
                return true;
            }

            // Current value is too small.
            // Eliminate the current row and move down.
            if (matrix[row][col] < target) {
                row++;
            }
            // Current value is too large.
            // Eliminate the current column and move left.
            else {
                col--;
            }
        }

        return false;
    }
}


/*
REVISION NOTES

1. Pattern
   - Matrix Search II (rows and columns both sorted).

2. Why start from the top-right?
   - Left values are smaller.
   - Down values are larger.
   - One comparison tells us exactly which direction to move.

3. Movement
   - current == target -> return true
   - current < target  -> move down (row++)
   - current > target  -> move left (col--)

4. Why does it work?
   - If current < target:
       Every element to the left is even smaller.
       Entire row can be discarded.

   - If current > target:
       Every element below is even larger.
       Entire column can be discarded.

5. Time Complexity
   - O(m + n)
   - At most m downward moves and n leftward moves.

6. Space Complexity
   - O(1)

7. Recognition Clue
   - Rows sorted.
   - Columns sorted.
   - Need efficient search.
   - Think: "Start from a corner where one direction decreases and the other increases."
   - Usually choose Top-Right or Bottom-Left.
*/