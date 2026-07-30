class Solution {
    public int findKthPositive(int[] arr, int k) {

        int low = 0;
        int high = arr.length - 1;

        // Binary search for the first index where
        // missing numbers till that index >= k
        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Missing numbers before arr[mid]
            // Formula:
            // Expected numbers till arr[mid] = arr[mid] - 1
            // Actual numbers present before arr[mid] = mid
            // Missing = (arr[mid] - 1) - mid
            int missing = arr[mid] - mid - 1;

            if (missing < k) {
                // We haven't missed k numbers yet.
                // Answer lies further to the right.
                low = mid + 1;
            } else {
                // We have already missed k (or more) numbers.
                // Try finding an earlier index.
                high = mid - 1;
            }
        }

        /*
         * After the loop:
         * low = first index where missing >= k
         * high = low - 1
         *
         * Why is the answer = low + k?
         *
         * Before the kth missing number:
         * - There are exactly 'low' array elements.
         * - There are exactly 'k' missing numbers.
         *
         * Therefore,
         * kth missing number = low + k
         *
         * Example:
         * arr = [2,3,4,7,11], k = 5
         *
         * Binary search ends with:
         * low = 4
         *
         * Before the answer:
         * Array elements = 4 (2,3,4,7)
         * Missing numbers = 5 (1,5,6,8,9)
         *
         * Answer = 4 + 5 = 9
         */
        return low + k;
    }
}


/*
Pattern:
1. Derive a monotonic function.
   missing(i) = arr[i] - i - 1

2. Binary search for the first index where:
   missing(i) >= k

3. Answer = low + k

Key intuition:
Before the kth missing number,
- low numbers come from the array.
- k numbers are missing.
Hence the kth missing number is low + k.
*/