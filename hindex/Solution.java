import java.util.HashMap;

public class Solution{

    public int hIndex(int[] citations) {
        //read integer
        //if greater than 0, put to map as key
        //value of map -> +1 for each citation that is greater than key

        HashMap<Integer, Integer> citMap = new HashMap<>();

        if(citations.length == 1 && citations[0] > 0) return 1;

        for(int i = 0; i < citations.length; i++){
            if (citations[i] > 0){
                if(citMap.containsKey(Integer.valueOf(citations[i]))){
                    //key already present
                    //so increment value for that key
                    //increment value for all keys less than or equal to current val
                    for (Integer key : citMap.keySet()) {
                        System.out.println("pr k = "+key);
                        if (key <= Integer.valueOf(citations[i])){
                            System.out.println("in if for k "+key);
                            int curVal = citMap.get(key);
                            citMap.put(key, curVal+1);
                        }
                    }
                }else{
                    //key absent
                    citMap.put(Integer.valueOf(citations[i]), Integer.valueOf(0));
                    for (Integer key : citMap.keySet()) {
                        System.out.println("ab k = "+key);
                        if (key <= Integer.valueOf(citations[i])){
                            System.out.println("in if for k "+key);
                            int curVal = citMap.get(key);
                            citMap.put(key, curVal+1);
                        }
                    }
                }
            }
        }

        int max = 0;
        for (Integer key : citMap.keySet()) {
            System.out.println("key = "+key+" vl = "+citMap.get(key));
            if(key <= citMap.get(key) && key > max){
                max = key;
            }
        }
        return max;
    }
}