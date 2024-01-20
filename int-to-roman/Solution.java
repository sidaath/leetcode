class Solution{
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        int num = 3749;
        int thousands   = num/1000;                                                         //3
        int hundreds    = (num - (thousands * 1000))/100;                                   //4
        int tens        = (num - (thousands * 1000) - (hundreds * 100))/10;                 //7
        int ones        = (num - (thousands * 1000) - (hundreds * 100) - (tens * 10));      //8


        System.out.println("1000s   = "+thousands);
        System.out.println("100s    = "+hundreds);
        System.out.println("10s     = "+tens);
        System.out.println("1s      = "+ones);

        for(int i = 0; i < thousands; i++){
            s.append("M");
        }
        System.out.println("After 1000s string = "+s.toString());

        int tempH = hundreds*100;
        while(tempH != 0 ){
            if(tempH == 900){
                s.append("CM");
                tempH = tempH - 900;
            }else if(tempH == 400){
                s.append("CD");
                tempH = tempH - 400;
            }else if(tempH >=500){
                s.append("D");
                tempH = tempH - 500;
            }else{
                tempH = tempH / 100;
                for(int j = 0; j < tempH; j++){
                    s.append("C");
                }
                tempH = 0;
            }
        }
        System.out.println("After 100s string = "+s.toString());

        int tempT = tens * 10;
        while(tempT != 0){
            if(tempT == 90){
                s.append("XC");
                tempT = tempT - 90; 
            }else if(tempT == 40){
                s.append("XL");
                tempT = tempT - 40;
            }else if(tempT >= 50){
                s.append("L");
                tempT = tempT - 50;
            }else {
                tempT = tempT / 10;
                for(int k = 0; k < tempT; k++ ){
                    s.append("X");
                }
                tempT = 0;
            }
        }
        System.out.println("After 10s string = "+s.toString());

        int tempO = ones;
        while(tempO != 0){
            if(tempO == 9){
                s.append("IX");
                tempO = tempO - 9; 
            }else if(tempO == 4){
                s.append("IV");
                tempO = tempO - 4;
            }else if(tempO >= 5){
                s.append("V");
                tempO = tempO - 5;
            }else {
                tempO = tempO / 1;
                for(int l = 0; l < tempO; l++ ){
                    s.append("I");
                }
                tempO = 0;
            }
        }
        System.out.println("After 1s string = "+s.toString());

        
    }
}