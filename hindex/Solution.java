import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;


public class Solution{
    public static void main(String[] args) {
        Tests tests = new Tests();
        Class<?> testClass = tests.getClass();
        Field[] testClassFields = testClass.getFields();

        for(int i = 0; i < testClassFields.length; i = i+2){
            Field field = testClassFields[i];
            field.setAccessible(true);

            int[] arrayTemp = {};
            int expResult = -2;
            int result = -1;

            if(Pattern.matches("ar[0-9]+", field.getName())){
                try {
                    Object fieldObject = field.get(testClass);
                    arrayTemp = (int[])fieldObject;
                    result = hIndex(arrayTemp);
                } catch (Exception e) {
                    result = -1;
                }
            }

            field = testClassFields[i+1];
            field.setAccessible(true);

            if(Pattern.matches("expRes[0-9]+", field.getName())){
                try {
                    Object fieldObject = field.get(testClass);
                    expResult = (int)fieldObject;
                } catch (Exception e) {
                    result = -4;
                }
            }

            if(result != expResult){
                System.out.println("Failed : Expected "+expResult+" Got "+result+" | "+Arrays.toString(arrayTemp));
            }
        }

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