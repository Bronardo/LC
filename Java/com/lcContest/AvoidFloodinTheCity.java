package com.lcContest;

import java.util.*;

/*
5442. Avoid Flood in The City
User Accepted:103
User Tried:543
Total Accepted:103
Total Submissions:907
Difficulty:Medium
Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water. If it rains over a lake which is full of water, there will be a flood. Your goal is to avoid the flood in any lake.

Given an integer array rains where:

rains[i] > 0 means there will be rains over the rains[i] lake.
rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
Return an array ans where:

ans.length == rains.length
ans[i] == -1 if rains[i] > 0.
ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.

Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes. (see example 4)

 */
public class AvoidFloodinTheCity {
    public int[] avoidFlood(int[] rains) {
        Map<Integer,Integer> lastRained = new HashMap<>();
        int[] ans = new int[rains.length];
        for(int i=0;i<rains.length;i++){
            if(rains[i]==0) {
                ans[i]=0;
            }else{
                ans[i] = -1;
                if(lastRained.containsKey(rains[i])){
                    boolean foundDry =false;
                    for(int j=lastRained.get(rains[i])+1;j<i;j++){
                        if(ans[j]==0) {
                            ans[j] = rains[i];
                            lastRained.replace(rains[i],i);
                            foundDry=true;
                            break;
                        }
                    }
                    if(!foundDry) return new int[]{};
                }else{
                    lastRained.put(rains[i],i);
                }
            }
        }
        //fill the dry days;
        for(int i=0;i<ans.length;i++){
            if(ans[i]==0) ans[i] = 1;
        }
        return ans;
    }
}
