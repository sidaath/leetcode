

public class Solution{
    
    public int jump(int[] nums){

        int jumpCount = 0;
        int maxJump = 0;
        int curIndex = 0;
        int prevLoc = 0;

        int[] options = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            options[i] = nums[i];
        }

        while (curIndex < nums.length){
            System.out.println("Current index = "+curIndex);
            maxJump = options[curIndex];

            if(maxJump == 0){
                System.out.println("Option 1 in loop");
                curIndex = prevLoc;
                options[curIndex] = --options[curIndex];
                --jumpCount;
            }else{
                System.out.println("Option 2 in loop");
                maxJump = options[curIndex];   
                if(maxJump > 1){
                    for(int i = 1; i <= maxJump; i++){
                        if(curIndex+i < nums.length && curIndex+maxJump < nums.length && options[curIndex+ i] > options[curIndex + maxJump]){
                            maxJump = i;
                        }
                    }
                }
                prevLoc = curIndex;
                curIndex = maxJump + prevLoc;
                ++jumpCount;
            }
        }




        return jumpCount;
    }
}