class Solution {
    public int totalFruit(int[] fruits) {

        Map <Integer, Integer> freq = new HashMap<>();

        int low = 0;

        int n = fruits.length;

        int res = 0;

        for(int high=0; high<n; high++){
            int temp = fruits[high];
            freq.put(temp, freq.getOrDefault(temp,0)+1);

            while(freq.size() > 2){
                int l = fruits[low];

                freq.put(l, freq.get(l)-1);

                if(freq.get(l) == 0){
                    freq.remove(l);
                }
                low++;
            }

            int len = high-low +1;
            res = Math.max(res, len);
        }

        return res;

        
    }
}