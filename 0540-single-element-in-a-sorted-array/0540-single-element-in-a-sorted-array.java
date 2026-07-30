class Solution {
    public int singleNonDuplicate(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        // Keep searching until only one element is left.
        // That element will be the single non-duplicate.
        while (low < high) {

            int mid = low + (high - low) / 2;

            // ------------------- IMPORTANT -------------------
            // We always want mid to point to the START of a pair.
            //
            // Before the single element:
            // Pairs start at EVEN indices.
            //
            // Example:
            // Index : 0 1 2 3 4 5
            // Value : 1 1 2 2 3 3
            //
            // If mid becomes odd, move one step back so it
            // points to the even index (start of the pair).
            //
            // This lets us ALWAYS compare:
            // nums[mid] with nums[mid + 1]
            //
            // Without this adjustment we'd sometimes compare
            // the wrong pair.
            if (mid % 2 == 1) {
                mid--;
            }

            // Pair is correct.
            // Therefore the single element must be AFTER this pair.
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            }
            // Pair is broken.
            // Therefore the single element is AT mid or BEFORE it.
            else {
                high = mid;
            }
        }

        // low == high
        // Only one candidate remains.
        return nums[low];
    }
}