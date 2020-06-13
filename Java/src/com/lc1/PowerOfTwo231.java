package com.lc1;

public class PowerOfTwo231 {
    public boolean isPowerOfTwo(int n) {
        double max = Math.log(n)/Math.log(2);
        for(int i=0;i<=max;i++){
            if((Math.pow(2,i))==n) return true;
        }
        return false;
    }
    public boolean isPowerOfTwo2(int n) {
        if(n<=0) return false;
        return (n&(n-1)) == 0;  //1000000000&111111111=0    101&100 = 100
    }

    public boolean isPowerOfTwo3(int n) {
        long i=1;
        while(i<n){
            i*=2;
        }
        return i==n;
    }

}