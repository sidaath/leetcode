import java.lang.reflect.Field;
import java.util.ArrayList;
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

        ArrayList<Integer> citationCounts = new ArrayList<>();

        for (int i = 0; i < citations.length+1; i++) {
            citationCounts.add(i, 0);
        }
        for (int i = 0; i < citations.length+1; i++) {
            if (debug) System.out.println("val = "+citationCounts.get(i));
        }

        int bookCitations       = -1;
        int valueToAdd          = -1;

        for (int i = 0; i < booksCount; i++){
            bookCitations = citations[i];
            if(debug) System.out.println("bookCitations = "+bookCitations);

            if(bookCitations >= booksCount){
                if(debug) System.out.println("   in GREATER THAN");
                valueToAdd = citationCounts.get(booksCount) + 1;
                if(debug) System.out.println("   adding "+valueToAdd+"to "+booksCount);
                citationCounts.add(booksCount, valueToAdd);
            }else{
                if(debug) System.out.println("   in LESS THAN");
                valueToAdd = citationCounts.get(bookCitations) + 1;
                if(debug) System.out.println("   adding "+valueToAdd+" to "+bookCitations);
                citationCounts.add(bookCitations, valueToAdd);
                if(debug) System.out.println("   citations["+bookCitations+"] = "+citationCounts.get(bookCitations));
            }
        }
        if(debug) System.out.println("**********************8");

        for (int i = 0; i < citations.length+1; i++) {
            if(debug) System.out.println("val"+i+" = "+citationCounts.get(i));
        }
        
        return 0;
    }
}