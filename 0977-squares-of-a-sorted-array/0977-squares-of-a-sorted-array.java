class Solution {
    public int[] sortedSquares(int[] nums) {

        int n = nums.length;
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>(); 
        
        // Seperate positive from Negative 
        for(int num: nums){
            if(num<0){
                neg.add(num);
            }
            else{
                pos.add(num);
            }
        }

        // if no negative elements are present in original array 
        if(neg.size() == 0){
            for(int i=0; i<pos.size(); i++){
                pos.set(i, pos.get(i) * pos.get(i));
            }
            return pos.stream().mapToInt(Integer::intValue).toArray();
        }

        // If no positive array are present in original array
        if(pos.size() == 0){
            for(int i=0; i<neg.size(); i++){
                neg.set(i, neg.get(i) * neg.get(i));
            }
            Collections.reverse(neg);
            return neg.stream().mapToInt(Integer::intValue).toArray();
        }

        // Both negative and positive exist
        int i=0, j=0, id=0;
        int n1=neg.size();
        int n2= pos.size();

        int[] res = new int[n1 + n2];

        // Square negatives and reverse them
        for (i = 0; i < n1; i++)
            neg.set(i, neg.get(i) * neg.get(i));
        Collections.reverse(neg);

        // Square positive
        for(i=0;i<n2;i++){
            pos.set(i, pos.get(i) * pos.get(i));
        }

        i=0;
        j=0;
        while(i<n1 && j<n2){
            if(neg.get(i) <= pos.get(j)){
                res[id++] = neg.get(i++);
            }else{
                res[id++] = pos.get(j++);
            }
        }

        while(i<n1){
            res[id++] = neg.get(i++);
        }
        while(j<n2){
            res[id++] = pos.get(j++);
        }
        return res;
    }
}



/* 
class Solution {
    public int[] sortedSquares(int[] nums) {

        int n = nums.length;
        int[] res = new int[n];

        int left = 0;
        int right = n - 1;
        int index = n - 1;

        while (left <= right) {

            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                res[index--] = leftSquare;
                left++;
            } else {
                res[index--] = rightSquare;
                right--;
            }
        }

        return res;
    }
}

 */