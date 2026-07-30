class Solution {
    public int maxProduct(int[] nums) {

        int bestMin = nums[0];
        int bestMax = nums[0];
        int ans = nums[0];
        
        
        for(int i=1; i<nums.length; i++){
            
            int oldMax = bestMax;
            int oldMin = bestMin;

            int candidate1 = nums[i];
            int candidate2 = oldMax * nums[i];
            int candidate3 = oldMin * nums[i];

            bestMax = Math.max(candidate1, Math.max(candidate2, candidate3));
            bestMin = Math.min(candidate1, Math.min(candidate2, candidate3));
                
            ans = Math.max(ans, bestMax);
        }
        return ans;
        
    }
}