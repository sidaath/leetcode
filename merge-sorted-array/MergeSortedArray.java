import java.util.Map;
import java.util.TreeMap;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        Map<Integer, Integer> hashMap = new TreeMap<Integer, Integer>();

        for(int i = 0; i < m; i ++){
            System.out.println("Int i = "+i);
            if(hashMap.containsKey(nums1[i])){
                hashMap.put(nums1[i], (hashMap.get(nums1[i]) + 1));
            }else{
                hashMap.put(nums1[i], 1);
            }
        }

        for (int i = 0; i < n; i++){
            if(hashMap.containsKey(nums2[i])){
                hashMap.put(nums2[i], (hashMap.get(nums2[i]) + 1));
            }else{
                hashMap.put(nums2[i], 1);
            }
        }

        int currentPos = 0;

        int key = 0;
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()){
            key = entry.getKey();
            count = entry.getValue();
            int nextPos = currentPos + count;
            while(currentPos < nextPos){
                nums1[currentPos] = key;
                currentPos = currentPos + 1;
            }    
        }
    }
}