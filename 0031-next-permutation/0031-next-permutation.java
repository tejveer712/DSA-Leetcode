class Solution {
    public void nextPermutation(int[] nums) {

        int pivot = -1;
        int len = nums.length;

        // Find the first decreasing element from end
        for(int i=len-2 ; i>=0; i--){
            // If smaller found
            if(nums[i] < nums[i+1]){
                //store index
                pivot = i;
                break;
            }
        }

        // If no index found
        if (pivot == -1) {
            // Reverse the entire array
            reverse(nums, 0, len - 1);
            return;
        }

        // Find just larger element
        for(int i=len-1; i>pivot ;i--){
            //Swap them
            if(nums[i] > nums[pivot]){
                swap(nums, i, pivot);
                break;
            }
        }

        // Reverse part after index
        reverse(nums, pivot + 1, len - 1);

    }    

    // Helper to reverse array
    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    // Helper to swap
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
        
    
}