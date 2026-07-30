class Solution {
    public boolean check(int[] nums) {

        if(nums.length==0 || nums.length==1){
            return true;
        }

        int voilation = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] > nums[(i + 1) % nums.length]){
                voilation++;
            }
        }
        if(voilation <= 1){
            return true;
        }else return false;
        
    }
}