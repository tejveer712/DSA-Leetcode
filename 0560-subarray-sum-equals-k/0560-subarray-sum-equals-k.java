class Solution {
    public int subarraySum(int[] nums, int k) {

        // Stores: prefixSum -> number of times it has occurred
        HashMap<Integer, Integer> prefixCount = new HashMap<>();

        // Prefix sum 0 has occurred once before starting
        // This handles subarrays that start from index 0
        prefixCount.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {

            // Update running prefix sum
            prefixSum += num;

            // If (prefixSum - k) has occurred before,
            // then there exists a subarray ending here with sum = k
            if (prefixCount.containsKey(prefixSum - k)) {
                count += prefixCount.get(prefixSum - k);
            }

            // Record current prefix sum
            prefixCount.put(prefixSum,
                    prefixCount.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}

/*
=========================
Revision Notes
=========================

Pattern:
- Prefix Sum + HashMap

Key Idea:
- Let current prefix sum be 'prefixSum'.
- We need a previous prefix sum such that:

      prefixSum - previousPrefix = k

  Rearranging:

      previousPrefix = prefixSum - k

- If we've already seen (prefixSum - k),
  every occurrence represents one valid subarray ending at the current index.

Why store frequencies instead of indices?
- The same prefix sum can occur multiple times.
- Every occurrence creates a different valid subarray.

Why initialize map with (0,1)?
- Represents an empty prefix before the array starts.
- Allows subarrays starting from index 0 to be counted.

Example:
nums = [1,2,3], k = 3

Initially:
map = {0=1}

prefix = 1
Need -2 -> not found
map = {0=1,1=1}

prefix = 3
Need 0 -> found once
count = 1
map = {0=1,1=1,3=1}

prefix = 6
Need 3 -> found once
count = 2

Answer = 2

Time Complexity:
O(n)

Space Complexity:
O(n)

Recognition Clues:
- Count subarrays
- Sum equals K
- Negative numbers are allowed
- Prefix Sum + HashMap is the standard approach
*/