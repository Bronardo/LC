package com.lcContest;

public class XOROperationinanArray {
    public int xorOperation(int n, int start) {
        int result = 0;
        for(int i=0;i<n;i++){
            result = (start+2*i)^result;
        }
        return result;
    }
}
