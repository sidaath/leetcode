import java.util.HashMap;
import java.util.Stack;

class JumpGame {
    public boolean canJump(int[] nums) {
        boolean result = false;

        if(nums.length == 1 || nums[0] >= nums.length  - 1) return true;
        if(nums.length > 1 && nums[0] == 0 ) return false;

        int currentPosition   = 0;
        int nextPosition = 0;
        int nextJump     = 0;

        int[] prevRec = new int[2];
        int   prevPosition = 0;
        int   prevJump = 0;
        
        Stack<int[]> jumpHistory = new Stack<>();
        HashMap<Integer, Integer> badPositions = new HashMap<>();

        for(currentPosition = 0; currentPosition < nums.length;){
            if(currentPosition + nums[currentPosition] >= nums.length - 1) return true;

            // System.out.println("Begin main loop, currentPos="+currentPosition);

            int temp = nums[currentPosition + nums[currentPosition]];
            if(temp != 0 && badPositions.containsKey(currentPosition + nums[currentPosition]) == false){ //max jump location not 0
                nextPosition = currentPosition + nums[currentPosition];
                jumpHistory.push(new int[]{currentPosition, nums[currentPosition]});
                currentPosition = nextPosition;
                // System.out.println("Jumping tp "+currentPosition);
            }else{
                // System.out.println("Max jump("+nums[currentPosition]+") location == 0");
                if(nums[currentPosition] - 1 == 0 || nums[currentPosition] - 1 == -1){ //came back to original
                    // System.out.println("cannot calculate another jump for this position");
                    badPositions.put(currentPosition, 1);
                    // System.out.println("Bad pos = "+badPositions.get(currentPosition));
                    if(jumpHistory.isEmpty()){
                        return false;
                    }
                    prevRec = jumpHistory.pop();
                    prevPosition = prevRec[0];
                    prevJump = prevRec[1];
                    // System.out.println("Prev pos = "+prevPosition+" prev jump = "+prevJump);
                    if(prevPosition == 0 && prevJump == 1){
                        return false;
                    }else if(prevPosition == 0 && prevJump > 1){
                        // System.out.println("Jumping to beginning, calcd pos="+(prevPosition + prevJump - 1));
                        // System.out.println("A Adding simulated jump to stack");
                        jumpHistory.push(new int[]{0, prevPosition + (prevJump - 1)});
                        currentPosition = prevPosition + (prevJump - 1);
                    }else{
                        // System.out.println("New jump - minus 1 from old pos jump + simulated Jump");
                        jumpHistory.push(new int[]{0, prevPosition + (prevJump - 1)});
                        currentPosition = prevPosition + (prevJump - 1);
                    }
                    continue;
                } 
                nextJump = currentPosition + nums[currentPosition] - 1;
                // System.out.println("Next jump = "+nextJump);
                while(nextJump > currentPosition && nums[nextJump] == 0 ){
                    // System.out.println("num at next jump == 0, next jump = "+nextJump);
                    nextJump--;
                }
                // System.out.println("end while loop, next pos = "+nextJump);
                nextPosition =  nextJump;
                if(nextPosition == currentPosition){ //no non-zero jump from current position
                    // System.out.println("while loop came back to current position "+currentPosition);
                    // System.out.println("cannot calculate another jump for this position");
                    if (jumpHistory.isEmpty()){
                        // System.out.println("Stack is empty, return false");
                        return false;
                    }else{
                        // System.out.println("Stack not empty, go back");
                        badPositions.put(currentPosition, 1);
                        // System.out.println("Bad pos = "+badPositions.get(currentPosition));
                        prevRec = jumpHistory.pop();
                        prevPosition = prevRec[0];
                        prevJump = prevRec[1];
                        // System.out.println("Prev pos = "+prevPosition+" prev jump = "+prevJump);
                        if(prevPosition == 0 && prevJump == 1){
                            return false;
                        }else if(prevPosition == 0 && prevJump > 1){
                            // System.out.println("Jumping to beginning, calcd pos="+(prevPosition + prevJump - 1));
                            currentPosition = prevPosition + (prevJump - 1);
                        }else{
                            // System.out.println(" Z New jump (old pos) = "+prevPosition);
                            jumpHistory.push(new int[]{0, prevPosition + (prevJump - 1)});
                            currentPosition = prevPosition + (prevJump - 1);
                        }
                        continue;
                    }
                }else if(nums[nextPosition] != 0){
                    // System.out.println("found non zero jump, next pos = "+nextPosition);
                    if(badPositions.containsKey(nextPosition) == true){
                        // System.out.println("non zero jump is bad");
                        if (jumpHistory.isEmpty()){
                            // System.out.println("B Stack is empty, return false");
                            return false;
                        }else{
                            // System.out.println("B Stack not empty, go back");
                            badPositions.put(currentPosition, 1);
                            // System.out.println("B Bad pos = "+badPositions.get(currentPosition));
                            prevRec = jumpHistory.pop();
                            prevPosition = prevRec[0];
                            prevJump = prevRec[1];
                            // System.out.println("B Prev pos = "+prevPosition+" prev jump = "+prevJump);
                            if(prevPosition == 0 && prevJump == 1){
                                return false;
                            }else if(prevPosition == 0 && prevJump > 1){
                                // System.out.println("B Jumping to beginning, calcd pos="+(prevPosition + prevJump - 1));
                                // System.out.println("B Adding simulated jump to stack");
                                jumpHistory.push(new int[]{0, prevPosition + (prevJump - 1)});
                                currentPosition = prevPosition + (prevJump - 1);
                            }else{
                                // System.out.println("B New jump (old pos) = "+prevPosition);
                                currentPosition = prevPosition;
                            }
                        continue;
                        } 
                    }else{
                        jumpHistory.push(new int[]{currentPosition, nextJump-currentPosition});
                        // System.out.println("Jumping to "+nextPosition+", pushed to stack.");
                        currentPosition = nextPosition;
                    }
                }else if(nums[nextPosition] == 0){
                    // System.out.println("No non-zero jump from current position "+currentPosition);
                    return false;
                }

            }


        }

        // int [] array = new int[2];
        // System.out.println("*** MEMORY ***");
        // while(!jumpHistory.empty()){
        //     array = jumpHistory.pop();
        //     System.out.println("["+array[0]+","+array[1]+"]");
        // }

        return result;
    }
}