
class Solution {
    public int[] twoSum(int[] numbers, int target) {

        // Initialize two pointers:
        // i starts at the beginning of the sorted array.
        // j starts at the end of the sorted array.
        int i = 0;
        int j = numbers.length - 1;

        // Continue searching while the pointers have not crossed.
        // We need two distinct elements, so i must be less than j.
        while (i < j) {

            // Calculate the sum of the elements at both pointers.
            int sum = numbers[i] + numbers[j];

            // Case 1: We found the required pair.
            // Return 1-based indices as required by LeetCode 167.
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            }

            // Case 2: The sum is smaller than the target.
            // Since the array is sorted, move i right to get a
            // larger value and potentially increase the sum.
            else if (sum < target) {
                i++;
            }

            // Case 3: The sum is greater than the target.
            // Move j left to get a smaller value and potentially
            // decrease the sum.
            else {
                j--;
            }
        }

        // No valid pair was found.
        // LeetCode guarantees exactly one solution for valid inputs,
        // so this is only a fallback return.
        return new int[]{-1, -1};
    }
}

/*
REVISION NOTES — LEETCODE 167

1. Pattern: Two Pointers

2. Key Idea:
   The array is already sorted, so we can adjust the sum
   by moving either pointer instead of checking every pair.

3. Algorithm:
   - Start i at index 0 and j at the last index.
   - Calculate numbers[i] + numbers[j].
   - If sum == target, return the 1-based indices.
   - If sum < target, increment i.
   - If sum > target, decrement j.
   - Continue until the pair is found.

4. Invariant:
   At every step, the pointers define the current candidate pair.
   Because the array is sorted, each pointer movement eliminates
   candidates that cannot produce the target with the current pair.

5. Complexity:
   Time: O(n) — each pointer moves in only one direction.
   Space: O(1) — only a constant number of variables are used.

6. Pattern Recognition:
   Look for a sorted array and a pair whose sum must equal
   a target. Consider two pointers at opposite ends.

7. Common Mistakes:
   - Returning the values instead of their indices.
   - Forgetting that LeetCode 167 requires 1-based indices.
   - Sorting the array unnecessarily.
   - Using i <= j instead of i < j, which could reuse an element.

8. One-Line Takeaway:
   In a sorted array, move the left pointer to increase the sum
   and the right pointer to decrease it.
*/
