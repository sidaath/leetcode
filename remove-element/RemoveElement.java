class Solution {
    public int removeElement(int[] nums, int val) {
        int endArray = nums.length - 1;
        int beginArray = 0;
        int count = 0;
    
        if(val > 50){
            return nums.length;
        }

        if(endArray == beginArray){
            return  nums[0] == val ?  0 : 1;
        }

        while (endArray >= beginArray){
            if(nums[beginArray] == val && nums[endArray] != val){
                nums[beginArray] = nums[endArray];
                nums[endArray] = -1;
                count++;
                beginArray  = beginArray + 1;
                endArray    = endArray - 1;
            }else if(nums[beginArray] == val && nums[endArray] == val){
                nums[endArray] = -1;
                count++;
                endArray = endArray - 1;
            }else if (nums[beginArray] != val && nums[endArray] == val){
                nums[endArray] = -1;
                count++;
                endArray = endArray - 1;
            }else{
                beginArray = beginArray + 1;
            }
        }

        return nums.length - count;
    }
}