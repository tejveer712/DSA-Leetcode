class Solution {
    public int lengthOfLongestSubstring(String s) {

        int n = s.length();

        int low  = 0;
        int res = 0;

        Map <Character, Integer> f = new HashMap<>();

        for(int high =0; high<n; high++){
            char c = s.charAt(high);
            f.put(c, f.getOrDefault(c,0)+1);


            int k = high - low + 1;

            while(f.size() < k){
                char tempL = s.charAt(low);

                f.put(tempL, f.get(tempL) - 1);
                if(f.get(tempL) == 0){
                    f.remove(tempL);
                }
                low++;

                k = high-low+1;
        }
        res = Math.max(res, high-low+1);
        }
        return res;
    }

    
}