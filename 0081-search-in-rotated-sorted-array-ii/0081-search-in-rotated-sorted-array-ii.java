class Solution {
    public boolean search(int[] nums, int target) {

        // Search space
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            // Calculate mid safely to avoid integer overflow
            int mid = low + (high - low) / 2;

            // Target found
            if (nums[mid] == target) {
                return true;
            }

            /*
             * Special case: duplicates at both ends and the middle.
             *
             * Example:
             * [1, 1, 1, 0, 1]
             *
             * Here we cannot determine which half is sorted because
             * low, mid and high all contain the same value.
             *
             * The only safe option is to shrink the search space.
             */
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;

                // Search boundaries have changed.
                // Recompute mid in the next iteration.
                continue;
            }

            // Left half is sorted.
            if (nums[low] <= nums[mid]) {

                // Check whether the target lies inside the sorted left half.
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    // Otherwise search the right half.
                    low = mid + 1;
                }

            } else {

                // Right half is sorted.

                // Check whether the target lies inside the sorted right half.
                if (nums[mid] <= target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    // Otherwise search the left half.
                    high = mid - 1;
                }
            }
        }

        // Target not present.
        return false;
    }
}

/*
==========================
Revision Notes / Learnings
==========================

1. This problem is similar to "Search in Rotated Sorted Array",
   but duplicates make it harder to identify the sorted half.

2. When:
        nums[low] == nums[mid] == nums[high]
   we cannot determine which half is sorted.

   Example:
        [1, 1, 1, 0, 1]

   In this case, shrink the search space:
        low++;
        high--;

3. Use 'continue' after shrinking because:
   - low and high have changed.
   - The old mid belongs to the previous search space.
   - A fresh mid must be computed in the next iteration.

4. If duplicates are NOT blocking us:
   - Either the left half is sorted.
   - Or the right half is sorted.
   Then perform the normal rotated binary search.

5. Time Complexity:
   - Average Case: O(log n)
   - Worst Case: O(n)

   Worst case occurs when the array contains many duplicates,
   because we may only shrink one element from each side.

   Example:
        [1,1,1,1,1,1,1]

6. Always use:
        while (low <= high)

   because when low == high,
   one element is still left to check.

Important - 
   Binary search relies on being able to discard half of the search space every iteration. Duplicates can make it impossible to determine which half is sorted, forcing us to shrink the search space by only one element from each end. That's why the worst-case time complexity becomes O(n).
*/