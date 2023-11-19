

public class Solution{
    
    public int jump(int[] nums){
        int distance            = nums.length - 1;
        int distanceTravelled   = 0;

        int curIndex = nums.length-1;


        int count = 0;
        //lowest number of additions that equal to or greater than distance
        //subset with smallest length that adds up to or greate than distance

        


       while(distanceTravelled < distance){
        System.out.println("Starting while loop");
        System.out.println("curIndex = "+curIndex);
        System.out.println("Dis Travlled = "+distanceTravelled);
        

        int distanceFromPoint = 0;
        System.out.println("Init distanceFromPoint = "+distanceFromPoint);
        int curIndexTemp = curIndex;
        System.out.println("Init curIndexTemp = "+curIndexTemp);

        for(int i = curIndex; i >= 0; i--){
            System.out.println("i = "+i+" curIndexTemp = "+curIndexTemp+" distanceFromPoint = "+distanceFromPoint);
            if(curIndex - i > distanceFromPoint && i+nums[i] >= curIndex){
                System.out.println("changing distance from point and curIndexTemp");
                distanceFromPoint = curIndex - i;
                curIndexTemp = i;
                System.out.println("New vals | curIndexTemp = "+curIndexTemp+" | distanceFromPoint = "+distanceFromPoint);
            }
        }

        distanceTravelled = distanceTravelled + (curIndex - curIndexTemp);
        curIndex = curIndexTemp;
        System.out.println("SET NEXT LOOP VALS : curIndex = "+curIndex+" | distanceTravelled = "+distanceTravelled);
        count++;
       }

       return count;
    }
}