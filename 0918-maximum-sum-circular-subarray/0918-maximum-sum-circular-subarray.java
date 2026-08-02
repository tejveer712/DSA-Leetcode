class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        // Stores the maximum subarray sum found so far.
        int maxSum = nums[0];

        // Stores the minimum subarray sum found so far.
        int minSum = nums[0];

        // Maximum subarray sum ending at the current index (Kadane's Algorithm).
        int maxBestEnding = nums[0];

        // Minimum subarray sum ending at the current index (Inverse Kadane).
        int minBestEnding = nums[0];

        // Total sum of all elements in the array.
        int totalSum = nums[0];

        // Traverse the array starting from the second element.
        for (int i = 1; i < nums.length; i++) {

            // Either extend the previous maximum subarray or start a new one.
            maxBestEnding = Math.max(maxBestEnding + nums[i], nums[i]);

            // Either extend the previous minimum subarray or start a new one.
            minBestEnding = Math.min(minBestEnding + nums[i], nums[i]);

            // Update the overall maximum and minimum subarray sums.
            maxSum = Math.max(maxBestEnding, maxSum);
            minSum = Math.min(minBestEnding, minSum);

            // Add the current element to the total array sum.
            totalSum += nums[i];
        }

        // Special case:
        // If all numbers are negative, totalSum - minSum becomes 0,
        // which represents an empty subarray (not allowed).
        // Therefore, return the maximum (least negative) element.
        if (maxSum < 0)
            return maxSum;

        // Maximum sum without wrapping.
        int normalSum = maxSum;

        // Maximum sum with wrapping.
        // Remove the minimum subarray from the total sum.
        int circularSum = totalSum - minSum;

        // Return the better of the two possibilities.
        return Math.max(normalSum, circularSum);
    }
}

/*
======================== Revision Notes ========================

Pattern:
- Kadane's Algorithm + Circular Array

Key Observation:
There are only two possible answers:
1. Normal Maximum Subarray
   -> Found using standard Kadane's Algorithm.

2. Circular Maximum Subarray
   -> The maximum circular subarray is obtained by removing the
      minimum subarray from the total array sum.
      Formula:
          Circular Sum = Total Sum - Minimum Subarray Sum

Algorithm:
1. Run Kadane to find the maximum subarray sum.
2. Run inverse Kadane to find the minimum subarray sum.
3. Compute the total sum of the array.
4. If all elements are negative (maxSum < 0), return maxSum.
5. Otherwise, return:
       max(maxSum, totalSum - minSum)

Why the all-negative check?
Example:
nums = [-3, -2, -5]

totalSum = -10
minSum = -10

Circular Sum = totalSum - minSum = 0

0 represents an empty subarray, which is invalid.
The correct answer is -2.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Interview Clue:
Whenever a problem mentions a "circular array" and asks for a
maximum subarray, think:
    Normal Kadane
        vs
    Total Sum - Minimum Subarray (Inverse Kadane)
===============================================================
*/