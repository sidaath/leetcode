import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
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
                    result = hIndex(arrayTemp, false);
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
                hIndex(arrayTemp, true);
            }else{
                System.out.println("Pass");
            }
        }

    }

    public static int hIndex(int[] citations, boolean debug) {
        int booksCount = citations.length;
        HashMap <Integer, Integer> dataStore = new HashMap<>();
        HashMap <Integer, Integer> repitions = new HashMap<>();

        int bookCitations       = -1;
        int valueToAdd          = -1;
        int curCitationCount    = -1;
        int repCount            = -1;

        for (int i = 0; i < booksCount; i++){
            bookCitations = citations[i];

            if(dataStore.containsKey(bookCitations) == false){
                valueToAdd = 1;
                for(Entry<Integer, Integer> entry : dataStore.entrySet()){
                    if(entry.getKey() < bookCitations){
                        dataStore.put(entry.getKey(), (entry.getValue()+1));
                    }

                    if(entry.getKey() > bookCitations){
                        valueToAdd = valueToAdd + repitions.get(entry.getKey());
                    }
                }
                dataStore.put(bookCitations, valueToAdd);
                repitions.put(bookCitations, 1);
            }else{
                curCitationCount = dataStore.get(bookCitations);
                repCount         = repitions.get(bookCitations);
                for(Entry<Integer, Integer> entry : dataStore.entrySet()){
                    if(entry.getKey() == bookCitations){
                        curCitationCount++;
                    }
                    if(entry.getKey() < bookCitations){
                        dataStore.put(entry.getKey(), entry.getValue()+1);
                    }
                }
                dataStore.put(bookCitations, curCitationCount);
                repitions.put(bookCitations, repCount+1);
            }
        }

        int max = 0;
        for(Entry<Integer, Integer> entry : dataStore.entrySet()){
            if(debug) System.out.println("K - "+entry.getKey()+" | V - "+entry.getValue());
            if(entry.getKey() > max){
                if(entry.getKey() <= entry.getValue()){
                    max = entry.getKey();
                }else if(entry.getValue() >= booksCount){
                    max = booksCount;
                }else if(entry.getValue() > max){
                    max = entry.getValue();
                }
            }
        }
        
        if(max >= booksCount){
            return booksCount;
        }else{
            return max;
        }
    }
}