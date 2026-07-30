class Solution {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> finalList = new ArrayList<>();

        for(int i=0; i<numRows; i++){
            if(i==0){
                List<Integer> list = new ArrayList<>();
                list.add(1);
                finalList.add(list);
            }
            
            else{
                List<Integer> prevlist = finalList.get(i - 1);
                int len = prevlist.size() - 1;
                List<Integer> current = new ArrayList<>();
                current.add(1);
                for(int j=0; j<len;j++){
                    current.add(prevlist.get(j) + prevlist.get(j + 1));
                }
                current.add(1);
                finalList.add(current);
                 
            }
            
        }

        return finalList;
        
    }
}