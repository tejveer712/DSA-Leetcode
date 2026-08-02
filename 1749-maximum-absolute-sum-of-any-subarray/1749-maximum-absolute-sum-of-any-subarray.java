class Solution {
    public int maxAbsoluteSum(int[] nums) {

        // Maximum subarray sum ending at the current index
        int maxEnding = 0;

        // Stores the overall maximum subarray sum found so far
        int maxSum = Integer.MIN_VALUE;

        // Minimum subarray sum ending at the current index
        int minEnding = 0;

        // Stores the overall minimum subarray sum found so far
        int minSum = Integer.MAX_VALUE;

        for (int i : nums) {

            // Either start a new subarray from the current element
            // or extend the previous maximum subarray
            maxEnding = Math.max(i, maxEnding + i);

            // Update the global maximum subarray sum
            maxSum = Math.max(maxSum, maxEnding);

            // Either start a new minimum subarray from the current element
            // or extend the previous minimum subarray
            minEnding = Math.min(i, minEnding + i);

            // Update the global minimum subarray sum
            minSum = Math.min(minSum, minEnding);
        }

        // The answer is the larger absolute value between
        // the maximum positive subarray sum and the minimum (most negative) subarray sum
        return Math.max(Math.abs(maxSum), Math.abs(minSum));
    }
}

/*
========================================
Revision Notes
========================================

1. Pattern:
   - Kadane's Algorithm (Maximum Subarray Sum)
   - Reverse Kadane (Minimum Subarray Sum)

2. Key Observation:
   - Maximum absolute subarray sum can come from:
       a) The largest positive subarray sum
       b) The most negative subarray sum
   - Therefore, run Kadane twice in a single traversal.

3. Maximum Kadane:
   maxEnding = Math.max(curr, maxEnding + curr);
   maxSum = Math.max(maxSum, maxEnding);

4. Minimum Kadane:
   minEnding = Math.min(curr, minEnding + curr);
   minSum = Math.min(minSum, minEnding);

5. Final Answer:
   Math.max(Math.abs(maxSum), Math.abs(minSum));

6. Time Complexity:
   O(n)

7. Space Complexity:
   O(1)

8. Interview Tip:
   - Whenever a problem asks for the "largest positive" and
     "largest negative" subarray, think of running Kadane
     twice: once with Math.max() and once with Math.min().

9. Common Mistake:
   - Accidentally using Math.max() for the minimum Kadane.
     Remember:
       Maximum Kadane -> Math.max()
       Minimum Kadane -> Math.min()
*/