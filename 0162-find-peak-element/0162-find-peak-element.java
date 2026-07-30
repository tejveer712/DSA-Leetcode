class Solution {
    public int findPeakElement(int[] nums) {


        int low = 0;
        int high = nums.length - 1;

        // Use low < high because we compare arr[mid] with arr[mid + 1].
        // This guarantees mid is never the last index,
        // so arr[mid + 1] is always a valid access.
        // if(nums.length == 1){
        //     return 0;
        // }

        while (low < high) {

            int mid = low + (high - low) / 2;

            // If current element is smaller than the next,
            // we are on the increasing slope.
            // Therefore, the peak must lie on the right.
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            }

            // Peak lies on the left including mid.
            else if (nums[mid] > nums[mid + 1]) {
                high = mid;      
            }
        }
        // low == high
        return high; //At the end of binary search,That index is guaranteed to be a peak.
    }
        
}


// Binary search shrinks the search space while always keeping
// at least one peak inside it.
//
// When low == high, only one index remains, and that index
// is guaranteed to be a peak.
//
// Therefore simply return low (or high).