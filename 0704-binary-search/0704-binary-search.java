class Solution {
    public int search(int[] nums, int target) {
        int low = 0; 
        int high = nums.length -1;
        

        while(low<=high){
            int mid = low + (high - low) /2;

            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target){
                low = mid+1;   
            }
            else{
                high = mid - 1;
            }
        }
        return -1;
    }
}
// Notes 
/**
The array is sorted, so after checking the middle element, we can eliminate half of the remaining search space:

If nums[mid] < target, search the right half.
If nums[mid] > target, search the left half.
Otherwise, we found the target.

Common Pitfall #1: Overflow in Mid Calculation
// Risky
int mid = (left + right) / 2;

// Safe
int mid = left + (right - left) / 2;
Why?

If:

left = 1_000_000_000
right = 1_500_000_000
then:

left + right = 2_500_000_000
which exceeds Integer.MAX_VALUE.

The overflow produces an incorrect midpoint.

Using:

left + (right - left) / 2
avoids this issue entirely.
*/
