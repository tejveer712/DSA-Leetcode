class Solution {
    public int findMin(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            // Mid is in the left sorted portion,
            // so the minimum must be to the right.
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            }
            // Mid is in the right sorted portion (or is the minimum),
            // so keep mid in the search space.
            else {
                high = mid;
            }
        }

        // low == high, pointing to the minimum.
        return nums[low];
    }
}

/*
Whenever you see a rotated sorted array:

Compare nums[mid] with nums[high].
If nums[mid] > nums[high]:
mid is in the left sorted part.
Minimum is to the right.
low = mid + 1.
Otherwise:
mid is in the right sorted part or is the minimum.
Keep mid in the search space.
high = mid.
When the loop ends, low == high, and that index is the minimum.

*/