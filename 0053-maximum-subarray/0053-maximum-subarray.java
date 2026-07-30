class Solution {
    public int maxSubArray(int[] nums) {

        int bestEnding = nums[0];
        int ans = nums[0];
        
        for(int i=0; i<nums.length; i++){
            if(i==0){
                bestEnding = nums[i];
                ans = nums[i];
            }else{
                int option1 = bestEnding + nums[i];
                int option2 = nums[i];
                bestEnding = Math.max(option1, option2);
                //ans = Math.max(ans, bestEnding);
                if(bestEnding > ans){
                    ans = bestEnding;
                }
            }
        }
        return ans;
    }
}