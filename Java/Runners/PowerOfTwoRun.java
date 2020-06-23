package Runners;

import com.lc1.*;


public class PowerOfTwoRun {
    public static void main(String[] args) {
        PowerOfTwo231 s = new PowerOfTwo231();


        //System.out.println(s.isPowerOfTwo(2));
        System.out.println(2&(2-1));
        System.out.println(4&3);
        System.out.println(5&4);
        System.out.println(8&7);
        System.out.println(1&6);
        System.out.println(1&7);
        System.out.println(1&8);

        int[] nums = {1,2,3,4};
        int[] rs = new int[nums.length];
        for(int i=0;i<rs.length;i++){
            if(i==0) {
                rs[i]=nums[i];
            }
            else{
                rs[i]=rs[i-1]+nums[i];
            }
        }
        for(int i=0;i<rs.length;i++){
            System.out.print(rs[i]+" ");
        }


    }
}
