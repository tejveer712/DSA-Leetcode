class Solution {
    public void moveZeroes(int[] nums) {

        // Position where the next non-zero element should be placed
        int insertPos = 0;

        // Traverse the array
        for (int j = 0; j < nums.length; j++) {

            // If current element is non-zero
            if (nums[j] != 0) {

                // Swap nums[insertPos] and nums[j]
                int temp = nums[insertPos];
                nums[insertPos] = nums[j];
                nums[j] = temp;

                // Move to the next insertion position
                insertPos++;
            }
        }
        
    }
}