
class Solution {
    public int threeSumClosest(int[] nums, int target) {

        // Stores the closest sum found so far.
        int result = 0;

        // Tracks the minimum absolute difference from the target.
        int maxDiff = Integer.MAX_VALUE;

        // Sort the array to enable the two-pointer approach.
        Arrays.sort(nums);

        int n = nums.length;

        // Fix the first element of the triplet.
        for (int i = 0; i < n - 2; i++) {

            // Skip duplicate fixed elements.
            // Not really required in this
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Initialize two pointers for the remaining elements.
            int left = i + 1;
            int right = n - 1;

            // Search for the pair that produces the closest sum.
            while (left < right) {

                // Calculate the sum of the current triplet.
                int sum = nums[i] + nums[left] + nums[right];

                // Calculate the absolute difference from the target.
                int diff = Math.abs(target - sum);

                // Update the closest sum if we found a smaller difference.
                if (diff < maxDiff) {
                    maxDiff = diff;
                    result = sum;
                }

                // Case 1: Exact target found.
                // This is the best possible answer, so return immediately.
                if (sum == target) {
                    return sum;
                }

                // Case 2: Current sum is smaller than target.
                // Move left forward to increase the sum.
                else if (sum < target) {
                    left++;
                }

                // Case 3: Current sum is greater than target.
                // Move right backward to decrease the sum.
                else {
                    right--;
                }
            }
        }

        // Return the triplet sum closest to the target.
        return result;
    }
}

/*
REVISION NOTES — LEETCODE 16: 3SUM CLOSEST

1. Pattern: Sorting + Two Pointers

2. Key Idea:
   Sort the array, fix one element and use two pointers
   to find the remaining two elements.

   Unlike 3Sum, we don't need an exact sum of zero.

   We need to minimize:

   diff = Math.abs(target - sum)

   Maintain the minimum difference found so far and
   update the result whenever a closer sum is discovered.

3. Algorithm:
   - Sort the input array.
   - Iterate through the array using pointer i.
   - Skip duplicate fixed elements.
   - Initialize left = i + 1 and right = n - 1.
   - Calculate the sum of the three elements.
   - Calculate the absolute difference from the target.
   - If diff < maxDiff:
       Update maxDiff.
       Update result.
   - If sum == target:
       Return immediately because the exact target is optimal.
   - If sum < target:
       Increment left to increase the sum.
   - If sum > target:
       Decrement right to decrease the sum.
   - Return the closest sum after completing the traversal.

4. Invariant:
   Since the array is sorted:
   - Moving left forward increases the sum.
   - Moving right backward decreases the sum.

   maxDiff always stores the smallest absolute difference
   encountered so far.

   result always stores the sum corresponding to maxDiff.

5. Complexity:
   Time: O(n log n) for sorting.
         O(n^2) for the outer loop and two-pointer traversal.
         Overall: O(n^2).

   Space: O(1) auxiliary space, excluding sorting implementation.

6. Pattern Recognition:
   - Find three elements whose sum is closest to a target.
   - Exact equality is not mandatory.
   - Need to minimize the absolute difference.
   - Brute force would require three nested loops.

   Think: Fix one element + Two Pointers + Track Minimum Difference.

7. Common Mistakes:
   - Forgetting to sort the array.
   - Initializing left = 0 instead of i + 1.
   - Moving the wrong pointer.
   - Comparing sum directly with target instead of comparing
     the absolute difference when updating the result.
   - Forgetting to update the closest sum.
   - Not returning immediately when an exact target is found.
   - Using duplicate skipping unnecessarily as a requirement.
     Unlike 3Sum, duplicate skipping is optional here because
     duplicate triplets do not need to be returned.

8. One-Line Takeaway:
   Sort the array, fix one element, use two pointers to
   find the closest remaining pair, and maintain the
   minimum absolute difference from the target in O(n^2).
*/
