class Solution {
    public int minSubArrayLen(int target, int[] nums) {

        int low = 0;
        int high = 0;
        int len = nums.length;
        int res = Integer.MAX_VALUE;
        int sum = 0;

        while(high < len){
            sum = sum + nums[high];

            while(sum >= target){
                int tempLen = high - low + 1;
                res = Math.min(res, tempLen);
                sum = sum - nums[low];
                low++;
            }
            high ++;
        }

        if(res == Integer.MAX_VALUE){
            return 0;
        }

        return res;
    }
}