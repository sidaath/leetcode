class Solution {
    public int removeDuplicates(int[] nums) {
        int nextValidNumberIndex =1;
        int count = 1;

        for (int i = 1; nextValidNumberIndex < nums.length; i++){
            nums[i] = nums[nextValidNumberIndex];

            if(nums[i] == nums[i-1]){
                while(nextValidNumberIndex<nums.length && nums[nextValidNumberIndex] == nums[i-1]){
                    nextValidNumberIndex = nextValidNumberIndex + 1;
                }
            }else{
                nextValidNumberIndex++;
            }
            count++;
        }
        return count;
    }
}