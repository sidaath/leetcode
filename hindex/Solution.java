import java.util.HashMap;
import java.util.Map.Entry;

public class Solution{
    public static void main(String[] args) {
        int[] ar1 = {1};
        int[] ar2 = {1,1,1,1,1,5};
        int[] ar3 = {5,6,7,8};
        int[] ar4 = {0,5,1,6,3};
        int[] ar5 = {5,5,6,7,8};
        int[] ar6 = {1,0,4,5,2,3};
        
        System.out.println("************** START ****************");
        // int res1 = hIndex(ar1);
        // System.out.println("res1 = "+res1);
        // System.out.println("**************************************");
        // int res2 = hIndex(ar2);
        // System.out.println("res2 = "+res2);
        // System.out.println("**************************************");
        // int res3 = hIndex(ar3);
        // System.out.println("res3 = "+res3);
        // System.out.println("**************************************");
        // int res4 = hIndex(ar4);
        // System.out.println("res4 = "+res4);
        // System.out.println("**************************************");
        // int res5 = hIndex(ar5);
        // System.out.println("res5 = "+res5);
        // System.out.println("**************************************");
        int res6 = hIndex(ar6);
        System.out.println("res6 = "+res6);
        System.out.println("**************************************");
        
    }

    public static int hIndex(int[] citations) {
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
                        if (key <= Integer.valueOf(citations[i])){
                            int curVal = citMap.get(key);
                            citMap.put(key, curVal+1);
                        }else{
                            int curVal = citMap.get(Integer.valueOf(citations[i]));
                            citMap.put(Integer.valueOf(citations[i]), curVal+1);
                        }
                    }
                }else{
                    //key absent
                    citMap.put(Integer.valueOf(citations[i]), Integer.valueOf(0));
                    //System.out.println("running for loop on keyset, cit[i] = "+citations[i]);
                    for (Integer key : citMap.keySet()) {
                        //System.out.println("key = "+key+" cur val = "+citMap.get(key));
                        if (key <= Integer.valueOf(citations[i])){
                            int curVal = citMap.get(key);
                            citMap.put(key, curVal+1);
                        }else{
                            int curVal = citMap.get(Integer.valueOf(citations[i]));
                            citMap.put(Integer.valueOf(citations[i]), curVal+1);
                        }
                        //System.out.println("mVal = "+ citMap.get(key));
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