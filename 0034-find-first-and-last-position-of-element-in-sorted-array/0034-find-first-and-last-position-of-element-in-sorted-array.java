class Solution {

    // Helper function to find either the first or last occurrence
    // first = true  -> find first occurrence
    // first = false -> find last occurrence
    private int findOccurrence(int[] nums, int target, boolean first) {

        int low = 0;
        int high = nums.length - 1;
        int ans = -1;   // Stores the latest valid index found

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] < target) {
                // Target lies on the right
                low = mid + 1;
            }
            else if (nums[mid] > target) {
                // Target lies on the left
                high = mid - 1;
            }
            else {
                // Target found
                ans = mid;

                // We already have one occurrence.
                // Keep searching to see if a better answer exists.

                if (first) {
                    // Looking for FIRST occurrence.
                    // Search on the LEFT to find an earlier index.
                    high = mid - 1;
                }
                else {
                    // Looking for LAST occurrence.
                    // Search on the RIGHT to find a later index.
                    low = mid + 1;
                }
            }
        }

        // Returns -1 if target doesn't exist.
        return ans;
    }

    public int[] searchRange(int[] nums, int target) {

        // Find the leftmost occurrence
        int first = findOccurrence(nums, target, true);

        // Find the rightmost occurrence
        int last = findOccurrence(nums, target, false);

        return new int[]{first, last};
    }
}

/**
1. Perform a normal Binary Search.

2. When target is found:
      ✔ Save the index.
      ✔ Don't stop searching.

3. Ask yourself:
      "Can a better answer still exist?"

   First Occurrence?
      -> Yes, on the LEFT
      -> high = mid - 1

   Last Occurrence?
      -> Yes, on the RIGHT
      -> low = mid + 1

4. Keep updating 'ans'.
   The final value of 'ans' is the correct answer.
*/