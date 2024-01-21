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
        int booksCount = citations.length;
        int[] citationCounts = new int[booksCount + 1];
        int offset          = booksCount;

        int thisCitation = 0;
        for(int i = 0; i < booksCount; i++){
            thisCitation = citations[i];

            if(thisCitation >= booksCount){
                if(debug) System.out.println(thisCitation+" >= "+booksCount+", increment citationCounts["+(offset)+"] to "+(citationCounts[offset] + 1));
                citationCounts[offset] = citationCounts[offset] + 1;
            }else{
                if(debug) System.out.println(thisCitation+" < "+booksCount+", increment citationCounts["+(thisCitation)+"] to "+(citationCounts[thisCitation] + 1));
                citationCounts[thisCitation] = citationCounts[thisCitation] + 1;
            }
        }

        int citationSum = 0;
        if(debug) System.out.println("Start : citationSum = "+citationSum);
        for (int i = offset; i >= 0; i--) {
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