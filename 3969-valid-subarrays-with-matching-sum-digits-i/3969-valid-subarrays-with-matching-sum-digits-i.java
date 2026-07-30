class Solution {
    public int countValidSubarrays(int[] nums, int x) {

        int s=0;
        int count=0;
        long tempSum=0;
        
        
        for(int i=0;i<nums.length;i++){
            long sum=0;
            for(int j=i; j<nums.length;j++){
                sum += nums[j]; 
                long lastDigit = sum%10;
                tempSum=sum;
                while(tempSum>=10){
                    tempSum=tempSum/10;
                }
                long firstDigit=tempSum;
                if(firstDigit==x && lastDigit==x){
                    count++;
                }
                
            }
        }
        return count;
        
    }
}