class Solution {
    public int pivotIndex(int[] nums) {

        int left = 0;   // Sum of all elements to the left of the current index
        int sum = 0;    // Total sum of the array
        int right = 0;  // Sum of all elements to the right of the current index

        // Calculate the total sum of the array
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        // Check if index 0 is the pivot.
        // Left sum is 0, right sum is total sum excluding nums[0].
        right = sum - nums[0];
        if (left == right)
            return 0;

        // Traverse the remaining indices.
        for (int i = 1; i < nums.length; i++) {

            // Add the previous element to the left sum.
            // This makes 'left' equal to the sum of elements before index i.
            left += nums[i - 1];

            // Right sum = Total sum - Current element - Left sum
            right = sum - nums[i] - left;

            // If both sums are equal, current index is the pivot.
            if (left == right)
                return i;
        }

        // No pivot index found.
        return -1;
    }
}

/*
Revision Notes:

1. Pivot Index:
   - An index where:
       leftSum == rightSum

2. Formula:
   - leftSum = Sum of elements before index i
   - rightSum = TotalSum - nums[i] - leftSum

3. Algorithm:
   - Calculate the total sum.
   - Initially, leftSum = 0.
   - Check index 0 separately because there are no elements on its left.
   - For every other index:
       a) Update leftSum by adding the previous element.
       b) Compute rightSum.
       c) Compare leftSum and rightSum.

4. Time Complexity:
   - O(n)
     - One pass to calculate total sum.
     - One pass to find the pivot.

5. Space Complexity:
   - O(1)
     - Uses only a few integer variables.

6. Edge Cases:
   - Pivot at index 0.
   - Pivot at the last index.
   - Single-element array (answer is 0).
   - Arrays containing negative numbers are handled correctly because sums are computed directly.
*/