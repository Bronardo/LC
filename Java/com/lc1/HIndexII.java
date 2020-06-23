package com.lc1;

public class HIndexII {
    public int hIndex(int[] citations) {
        int n = citations.length;
        if(n<1) return 0;
        int h  = 0;
        for(int i=n-1;i>=0;i--){
            if(citations[i]<h+1) break;
            h++;
        }

        return h;
    }
}
