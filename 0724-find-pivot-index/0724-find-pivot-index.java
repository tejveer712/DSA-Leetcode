class Solution {
    public int pivotIndex(int[] nums) {

        int leftSum = 0;   // Sum of elements to the left of the current index
        int totalSum = 0;  // Sum of all elements in the array

        // First pass: Calculate the total sum of the array.
        for (int num : nums) {
            totalSum += num;
        }

        // Second pass: Check each index as a potential pivot.
        for (int i = 0; i < nums.length; i++) {

            // Right sum = Total sum - Current element - Left sum
            int rightSum = totalSum - nums[i] - leftSum;

            // If left and right sums are equal, we've found the pivot.
            if (leftSum == rightSum) {
                return i;
            }

            // Include the current element in the left sum
            // before moving to the next index.
            leftSum += nums[i];
        }

        // No pivot index exists.
        return -1;
    }
}

/*
========================
Revision Notes
========================

Pattern:
- Prefix Sum

Key Observation:
- Instead of recalculating the left and right sums for every index (O(n²)),
  maintain the left sum while deriving the right sum using the total sum.

Formula:
- leftSum  = Sum of elements before index i
- rightSum = totalSum - nums[i] - leftSum

Algorithm:
1. Calculate the total sum of the array.
2. Initialize leftSum = 0.
3. Traverse each index:
   - Compute rightSum.
   - If leftSum == rightSum, return the current index.
   - Otherwise, add nums[i] to leftSum.
4. If no pivot is found, return -1.

Why update leftSum after checking?
- While evaluating index i, leftSum must contain only the elements
  before i. If we update it first, nums[i] would incorrectly become
  part of the left side.

Time Complexity:
- O(n)
  - One pass to compute totalSum.
  - One pass to find the pivot.

Space Complexity:
- O(1)

Edge Cases:
- Pivot at index 0.
- Pivot at the last index.
- Single-element array (answer is 0).
- Arrays containing negative numbers.
*/