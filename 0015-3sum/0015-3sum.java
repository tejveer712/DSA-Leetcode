
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        // Stores all unique triplets whose sum equals zero.
        List<List<Integer>> result = new ArrayList<>();

        // Sort the array to enable the two-pointer approach.
        Arrays.sort(nums);

        int n = nums.length;

        // Fix the first element of the triplet.
        for (int i = 0; i < n - 2; i++) {

            // Skip duplicate first elements to avoid duplicate triplets.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Initialize two pointers for the remaining elements.
            int left = i + 1;
            int right = n - 1;

            // Required sum of the remaining two elements.
            int sum = -nums[i];

            // Search for a pair whose sum equals the required sum.
            while (left < right) {

                int s = nums[left] + nums[right];

                // Case 1: Found a valid triplet.
                if (s == sum) {

                    // Store the triplet in the result.
                    result.add(Arrays.asList(
                        nums[i], nums[left], nums[right]
                    ));

                    // Move both pointers to search for another pair.
                    left++;
                    right--;

                    // Skip duplicate left elements.
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate right elements.
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }

                // Case 2: Current sum is smaller than the target.
                // Move left forward to increase the sum.
                else if (s < sum) {
                    left++;
                }

                // Case 3: Current sum is greater than the target.
                // Move right backward to decrease the sum.
                else {
                    right--;
                }
            }
        }

        // Return all unique triplets.
        return result;
    }
}

/*
REVISION NOTES — LEETCODE 15: 3SUM

1. Pattern: Sorting + Two Pointers

2. Key Idea:
   Instead of using three nested loops (O(n^3)),
   sort the array, fix one element and use two pointers
   to find the remaining two elements.

   We need:
   nums[i] + nums[left] + nums[right] == 0

   Rearranging:
   nums[left] + nums[right] == -nums[i]

3. Algorithm:
   - Sort the input array.
   - Iterate through the array using pointer i.
   - Skip duplicate fixed elements.
   - Initialize left = i + 1 and right = n - 1.
   - Calculate the sum of nums[left] + nums[right].
   - If sum == -nums[i]:
       Store the triplet.
       Move both pointers.
       Skip duplicate left and right elements.
   - If sum < -nums[i]:
       Increment left to increase the sum.
   - If sum > -nums[i]:
       Decrement right to decrease the sum.
   - Return all unique triplets.

4. Invariant:
   Since the array is sorted:
   - Moving left forward increases the sum.
   - Moving right backward decreases the sum.

   The fixed element nums[i] is never reused because
   left always starts at i + 1.

5. Complexity:
   Time: O(n log n) for sorting.
         O(n^2) for the outer loop and two-pointer traversal.
         Overall: O(n^2).

   Space: O(1) auxiliary space, excluding sorting implementation.
          O(k) for storing the result, where k is the number
          of unique triplets.

6. Pattern Recognition:
   - Find three elements satisfying a target sum.
   - Need unique triplets or combinations.
   - Brute force would require three nested loops.
   - Sorting is allowed and the array has no ordering constraints.

   Think: Fix one element + Two Pointers.

7. Common Mistakes:
   - Forgetting to sort the array.
   - Initializing left = 0 instead of i + 1.
   - Initializing left and right outside the outer loop.
   - Not skipping duplicate fixed elements.
   - Not skipping duplicate left and right elements.
   - Forgetting to move both pointers after finding a triplet.
   - Using left < n instead of left < right in duplicate checks.
   - Returning only the target sum instead of all three elements.

8. One-Line Takeaway:
   Sort the array, fix one element, use two pointers to
   find the remaining pair, and skip duplicates to
   generate unique triplets in O(n^2) time.
*/
