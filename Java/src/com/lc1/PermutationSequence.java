package com.lc1;

import java.util.LinkedList;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        LinkedList<String> list = new LinkedList<>();
        for(int i=1;i<=n;i++){
            list.add(""+i);
        }
        String result ="";
        int a=n-1;
        int cycle;
        while(a>0){
            int f = factorial(a);
            cycle = k/f;
            k=k%f;
            if(k==0){
                result+= list.remove(cycle-1);
                for(int i=list.size()-1;i>=0;i--){
                    result+=list.remove(i);
                }
                break;
            }else{
                result+= list.remove(cycle);
                a--;
            }
        }

//        return list.isEmpty()? result:result+list.toString().replaceAll("\\p{Punct}","").replaceAll("\\s","");
        return list.isEmpty()? result:result+list.get(0);
    }


    public static int factorial (int n) {
        if(n == 0) return 1;
        return n*factorial(n-1);
    }
    public static int factorialWithLoop(int n){
        int res=1;
        for(int i=1;i<=n;i++) res*=i;
        return res;
    }
}
