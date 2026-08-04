class Solution {
    public int subarraysDivByK(int[] nums, int k) {

        int n = nums.length;
        int ans = 0;      // Stores the total number of valid subarrays
        int sum = 0;      // Running prefix sum

        // HashMap to store:
        // Key   -> remainder of prefixSum % k
        // Value -> frequency of that remainder
        HashMap<Integer, Integer> f = new HashMap<>();

        // A remainder of 0 has already occurred once (empty prefix).
        // This helps count subarrays starting from index 0.
        f.put(0, 1);

        for (int i = 0; i < n; i++) {

            // Update the running prefix sum
            sum += nums[i];

            // Compute the remainder of the current prefix sum
            int remainder = sum % k;

            // Java can produce a negative remainder for negative numbers.
            // Convert it to the equivalent positive remainder.
            if (remainder < 0) {
                remainder += k;
            }

            // If this remainder has been seen before,
            // every previous occurrence forms a valid subarray.
            ans += f.getOrDefault(remainder, 0);

            // Record the current remainder for future subarrays.
            f.put(remainder, f.getOrDefault(remainder, 0) + 1);
        }

        return ans;
    }

    /*
    ========================= Revision Notes =========================

    Pattern:
    Prefix Sum + HashMap (Store Prefix Sum Remainders)

    Key Observation:
    If two prefix sums have the same remainder when divided by k,
    then the subarray between them has a sum divisible by k.

        prefixSum1 % k == prefixSum2 % k

    Therefore,

        (prefixSum2 - prefixSum1) % k == 0

    -----------------------------------------------------------------
    Algorithm:

    1. Maintain a running prefix sum.
    2. Compute:
           remainder = prefixSum % k
    3. If remainder is negative:
           remainder += k
       (Java's % operator can return negative values.)
    4. Add the frequency of this remainder to the answer because each
       previous occurrence forms one valid subarray.
    5. Increment the frequency of the current remainder.

    -----------------------------------------------------------------
    Why initialize map with (0 -> 1)?

    f.put(0, 1);

    This represents the empty prefix.

    It allows us to count subarrays that start from index 0.

    Example:
    nums = [5], k = 5

    Prefix Sum = 5
    Remainder = 0

    Since remainder 0 has already occurred once (empty prefix),
    we correctly count the subarray [5].

    -----------------------------------------------------------------
    Time Complexity:
    O(n)

    Space Complexity:
    O(min(n, k))
    (At most one entry for each possible remainder.)

    -----------------------------------------------------------------
    Common Mistakes:

    1. Forgetting:
           f.put(0, 1);

    2. Not handling negative remainders:
           if (remainder < 0)
               remainder += k;

    3. Updating the HashMap before adding its frequency to the answer.
       Correct order:
           ans += frequency
           frequency++

    4. Using map.get() instead of getOrDefault(), which may cause a
       NullPointerException if the key is absent.

    -----------------------------------------------------------------
    Pattern Recognition:

    If a problem asks:
    - Count subarrays...
    - Sum divisible by K
    - Modulo property
    - Prefix sums with equal remainders

    Think:
        Prefix Sum + HashMap (Remainder Frequency)

    ================================================================
    */
}