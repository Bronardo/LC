package com.lc1;

import java.util.HashMap;
import java.util.Map;

public class LongestDuplicateSubstring {
    public String longestDupSubstring(String S) {
        int n = S.length();
        if(n<2) return S;
        //from 1 binary search up for a possible solution;
        return binarySearch(S,0,n-2);
    }

    public String binarySearch(String S, int left, int right){
        String result ="";
        //try search for dup sub of length mid;
        if(right>=left){
            int mid = (left+right)/2;
            result = RabinKarp(S,mid);
            if(result.length()==0){
                return binarySearch(S,left,mid-1);
            }else{
                String temp  = binarySearch(S,mid+1,right);
                return temp.length()>0? temp:result;
            }
        }
        return result;
    }
    public String patternSearch(String S, int K){
        int n = S.length();
        for(int i=0;i+K<=n-1;i++){
            String a = S.substring(i,i+K);
            if(a.compareTo("akyj")==0){
                System.out.println(a);
            }
            if(S.indexOf(a,i+1)>=0) return a;
            //checking the substring start at i with length K have another substring.
//            for(int j=i+1;j+K<=n;j++){
//                if(S.substring(j,j+K).indexOf())
//            }
        }
        return "";
    }

    //the hint says Rabin Karp but in java we have String.indexOf AND both O(n+m) -> O(nm). so....really?
    //Yes! says String.indexOf is not good enough.
    //lets just use rolling hash.
    public String RabinKarp(String S, int K){
        int n=S.length();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i+K<=n;i++){
            String sub = S.substring(i,i+K);
            int hash = sub.hashCode();
            if(map.size()==0){
                map.put(hash,1);
            }else if(map.containsKey(hash)) return sub;
            else map.put(hash,1);
        }
        return "";
    }
}
