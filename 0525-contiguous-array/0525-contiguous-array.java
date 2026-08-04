class Solution {
    public int findMaxLength(int[] nums) {

        int n = nums.length;

        // Count of zeros and ones seen so far
        int zero = 0;
        int one = 0;

        // Maps (zero - one) difference to its first occurrence index
        HashMap<Integer, Integer> map = new HashMap<>();

        // Stores the maximum length of a valid subarray
        int maxLength = 0;

        for (int i = 0; i < n; i++) {

            // Update zero/one counts
            if (nums[i] == 0)
                zero++;
            else
                one++;

            // Current balance between zeros and ones
            int diff = zero - one;

            // If balance becomes 0, then from index 0 to i
            // we have equal number of zeros and ones
            if (diff == 0) {
                maxLength = Math.max(maxLength, i + 1);
                continue;
            }

            // Store only the first occurrence of each balance
            // because it gives the longest possible subarray
            if (!map.containsKey(diff)) {
                map.put(diff, i);
            } else {

                // Same balance seen before.
                // Elements between previous index and current index
                // contain equal number of zeros and ones.
                int idx = map.get(diff);
                int len = i - idx;

                maxLength = Math.max(maxLength, len);
            }
        }

        return maxLength;
    }
}

/*
==================== Revision Notes ====================

Pattern:
- Prefix Sum + HashMap

Key Observation:
- Instead of checking every subarray,
  keep track of:
      diff = (#zeros - #ones)

- If the same 'diff' appears again, then the subarray
  between those two indices has equal number of 0s and 1s.

Why?
Suppose:
Index      Diff
-----      ----
2            1
7            1

Between indices (2 + 1) to 7:
The increase in zeros equals the increase in ones,
so the difference remains unchanged.

Why store only the first occurrence?
- The first occurrence gives the maximum possible length.
- Later occurrences would produce shorter subarrays.

Special Case:
- If diff == 0,
  then the entire prefix (0...i) has equal zeros and ones.

Complexity:
Time  : O(n)
Space : O(n)

Recognition Clue:
Whenever the problem says:
- Equal number of X and Y
- Balanced counts
- Same frequency of two values

Think:
"Can I represent the balance using a prefix sum
and use a HashMap to find repeated balances?"
========================================================
*/