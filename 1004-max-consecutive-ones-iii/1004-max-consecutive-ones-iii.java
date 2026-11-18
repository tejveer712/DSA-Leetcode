class Solution {
    public int longestOnes(int[] nums, int k) {

        int low = 0;
        int zeros = 0;
        int n = nums.length;
        int res = Integer.MIN_VALUE;

        for(int high = 0; high < n; high++){
            
            if(nums[high] == 0){
                zeros++;
            }
            
            while(zeros > k){
                if(nums[low] == 0){
                    zeros--;
                }
                low++;
            }

            res = Math.max(res, high-low+1);
        }

        return res;
        
    }
}