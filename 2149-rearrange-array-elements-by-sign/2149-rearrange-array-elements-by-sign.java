class Solution {
    public int[] rearrangeArray(int[] nums) {

        int []  arrP = new int[nums.length/2];
        int []  arrN = new int[nums.length/2];

        int posIndex = 0; int negIndex = 0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                arrP[posIndex++] = nums[i];
            }
            else{
                arrN[negIndex++] = nums[i];
            }
        }

        int[] result = new int[nums.length];
        posIndex = 0; negIndex=0;

        for(int i=0; i<nums.length;i+=2){
            result[i]=arrP[posIndex++];
            result[i+1]=arrN[negIndex++];
        }

        return result;


        
    }
}