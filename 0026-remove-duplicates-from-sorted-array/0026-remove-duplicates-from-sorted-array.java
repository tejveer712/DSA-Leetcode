class Solution {
    public int removeDuplicates(int[] nums) {



// Edge case: if array is empty, return 0
// First element is always unique
// Create a pointer (insertPos) to track where the next unique element should be placed
// Initialize insertPos = 1
// Traverse the array starting from index 1
    // Compare current element with previous element
    // If they are different:
        // We found a new unique element
        // Place the current element at insertPos
        // Move insertPos to the next position
// After traversal, insertPos represents the number of unique elements
// Return insertPos

    if(nums.length == 0){
        return 0;
    }

    int ip = 1;

    for(int i=1; i<nums.length; i++){
        if(nums[i] != nums[i-1]){
            nums[ip] = nums[i];
            ip++;
        }
    }

    return ip;


        
    }
}