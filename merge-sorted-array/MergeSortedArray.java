class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pointerNum1 = m - 1;
        int pointerNum2 = n - 1;
        int finalPlace = m + n -1;

        if (n == 0){
            finalPlace = -1; 
        }else if(m == 0){
            for(int i = 0; i < n; i++){
                nums1[i] = nums2[i];
            }
            finalPlace = -1;
        }

        while (finalPlace >= 0 ){

            if(pointerNum1 < 0 || pointerNum2 < 0){
                if(pointerNum1 <0){
                    while(pointerNum2 >= 0){
                        nums1[finalPlace] = nums2[pointerNum2];
                        pointerNum2 = pointerNum2 - 1;
                        finalPlace = finalPlace - 1;
                    }
                }else{
                    while(pointerNum1 >= 0){
                        nums1[finalPlace] = nums1[pointerNum1];
                        pointerNum1 = pointerNum1 - 1;
                        finalPlace = finalPlace - 1; 
                    }
                }
            }

            if (pointerNum1 >= 0 && pointerNum2 >= 0 && nums1[pointerNum1] > nums2[pointerNum2]){
                while(pointerNum1 >= 0 && pointerNum2 >= 0 && nums1[pointerNum1] > nums2[pointerNum2]){
                    nums1[finalPlace] = nums1[pointerNum1];
                    finalPlace = finalPlace -1;
                    pointerNum1 = pointerNum1 - 1;
                }
            }else if (pointerNum1 >= 0 && pointerNum2 >= 0 && nums1[pointerNum1] < nums2[pointerNum2]){
                while(pointerNum1 >= 0 && pointerNum2 >= 0 && nums1[pointerNum1] < nums2[pointerNum2]){
                    nums1[finalPlace] = nums2[pointerNum2];
                    finalPlace = finalPlace - 1;
                    pointerNum2 = pointerNum2 - 1;
                }
            }else if (pointerNum1 >= 0 && pointerNum2 >= 0 && nums1[pointerNum1] == nums2[pointerNum2]){
                nums1[finalPlace] = nums1[pointerNum1];
                nums1[finalPlace-1] = nums2[pointerNum2];
                finalPlace = finalPlace - 2;
                pointerNum1 = pointerNum1 -1;
                pointerNum2 = pointerNum2 -1;
            }
        }
    }
}