import java.lang.reflect.Field;
import java.util.Arrays;
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
        int booksCount          = citations.length;
        int[] citationCounts    = new int[booksCount + 1];
        int offsetPosition      = booksCount;

        int currentCitation = 0;
        for(int i = 0; i < booksCount; i++){
            //for every citation in array, increment by 1 its position in new array
            //index of new array = value of citations in citations array
            //if citation value is higher number of books, then increment offset value by 1
            currentCitation = citations[i];

            if(currentCitation >= booksCount){
                if(debug) System.out.println(currentCitation+" >= "+booksCount+", increment citationCounts["+(offsetPosition)+"] to "+(citationCounts[offsetPosition] + 1));
                citationCounts[offsetPosition] = citationCounts[offsetPosition] + 1;
            }else{
                if(debug) System.out.println(currentCitation+" < "+booksCount+", increment citationCounts["+(currentCitation)+"] to "+(citationCounts[currentCitation] + 1));
                citationCounts[currentCitation] = citationCounts[currentCitation] + 1;
            }
        }

        int citationSum = 0;
        if(debug) System.out.println("Start : citationSum = "+citationSum);


        //every value in new array should be increased by the sum of all values to its right
        //so start from left, and work towards right - adding current value to the value to its left
        //index i is citations, value at index i is how many books have that many citations
        //if sum >= index, then (sum) books have at least (index) citations, so return index
        for (int i = offsetPosition; i >= 0; i--) {
            if(debug) System.out.println("   i = "+i+" | citationCounts"+i+" = "+citationCounts[i]);
            
            citationSum = citationSum + citationCounts[i];
            if(debug) System.out.println("   new sum = "+citationSum);
            
            if(citationSum >= i){
                return i;
            }
        }
        return 0;
    }
}