package com.lc1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargestDivisibleSubset368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i : nums){
            list.add(i);
        }
        //sort it
        if(true){
            Collections.sort(list);
            System.out.println(list);
        }
        //if(validDivSet(list)) return list;
        //return recLDS(list);
        //return findFromSmallRec(list,0);
        //return loopit(list);
        return loopit2(list);
    }

    private List<Integer> loopit2(List<Integer> nums){
        if(nums.size()<2) return nums;
        int n = nums.size();
        List<List<Integer>> solutionSet;
        solutionSet = new ArrayList<>();
        for(int i=0;i<n;i++){
            List<Integer> list = new ArrayList<>();
            list.add(nums.get(i));
            solutionSet.add(list);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(nums.get(i)%nums.get(j)==0){
                    if(solutionSet.get(j).size()+1>solutionSet.get(i).size()){
                        solutionSet.set(i,new ArrayList<>(solutionSet.get(j)));
                        solutionSet.get(i).add(nums.get(i));
                    }
                }
            }
        }
        int pointer=0;
        for(int i=0;i<n;i++){
            if(solutionSet.get(i).size()>solutionSet.get(pointer).size()) pointer=i;
        }

        return solutionSet.get(pointer);
    }

    private List<Integer> loopit(List<Integer> nums){
        if(nums.size()<2) return nums;
        int n = nums.size();
        List<Integer> largest;
        largest = new ArrayList<>();
        for(int i=0; i<n-1;i++){
            List<Integer> candidate;
            candidate = new ArrayList<>();
            candidate.add(nums.get(i));
            for(int j=i+1;j<n;j++){
                if(nums.get(j)%candidate.get(candidate.size()-1)==0){
                    candidate.add(nums.get(j));
                }
            }
            if(candidate.size()>largest.size()){
                largest=candidate;
            }
        }
        return largest;
    }

    private boolean divCompare(int s1, int s2){
        if(s1%s2==0) return true;
        if(s2%s1==0) return true;
        return false;
    }

    private boolean validDivSet(List<Integer> set){
        if(set.size()==1) return true;
        for(int i =0;i<set.size()-1;i++){
            for(int j=i+1;j<set.size();j++){
                if(set.get(j)%set.get(i)!=0){
                    return false;
                }
            }
        }
        return true;
    }

    private List<Integer> findFromSmallRec(List<Integer> nums,int currentSize){
        if(nums.size()==currentSize || nums.size()==1) return nums;

        for(int i =0;i<nums.size();i++){
            for(int j=i+1;j<nums.size();i++){
                if(nums.get(j)%nums.get(i)==0);
            }
        }
        return null;
    }


    private List<Integer> recLDS(List<Integer> nums){
        if(validDivSet(nums)) return nums;
        List<Integer> largest = new ArrayList<>();
        for(Integer i : nums){
            List<Integer> minusOne;
            minusOne = new ArrayList<>(nums);
            minusOne.remove(i);
            List<Integer> valid = recLDS(minusOne);
            if(valid.size()==nums.size()-1) return valid;
            if(valid.size()>0){
                if(largest.size()==0 || valid.size()>largest.size()){
                    largest = valid;
                }
            }
        }
        return largest;
    }


    public List<Integer> largestDivisibleSubsetBestMemory(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int[] backtrack = new int[len];
        Arrays.fill(backtrack, -1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j]+1 > dp[i]) {
                    dp[i] = dp[j]+1;
                    backtrack[i] = j;
                }
            }
        }
        int max = -1;
        int maxIndex = 0;
        for (int i = 0; i < len; i++) {
            if (dp[i] > max) {
                max = dp[i];
                maxIndex = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (maxIndex != -1) {
            res.add(nums[maxIndex]);
            maxIndex = backtrack[maxIndex];
        }
        return res;
    }


    int maxV;
    int maxL;
    int cs;
    int next[];
    int len[];
    public List<Integer> largestDivisibleSubsetBestRunTime(int[] nums) {

        if(nums.length==0)
            return new ArrayList<>();

        next=new int[nums.length];
        len=new int[nums.length];

        Arrays.sort(nums);
        maxV=nums[nums.length-1];


        for(int j=0;j<nums.length;j++){
            dp(1,j,nums);

        }

        List<Integer>list=new ArrayList<>();
        int i=cs;
        while(i!=-1){
            list.add(nums[i]);
            i=next[i];
        }
        return list;

    }



    private void dp(int cL,int start,int[] nums){

        if(len[start]==0){
            len[start]=1;
            next[start]=-1;
        }
        if(len[start]>maxL){
            maxL=len[start];
            cs=start;
        }



        int limit=maxV>>Math.max(maxL-cL,0);
        int max=0;
        for(int j=start+1;j<nums.length&&nums[j]<=limit;j++){

            if(nums[j]%nums[start]==0){

                if(len[j]==0){
                    dp(cL+1,j,nums);
                }

                if(len[j]>max){
                    max=len[j];
                    next[start]=j;
                    len[start]=len[j]+1;
                    if(len[start]>maxL){
                        maxL=len[start];
                        cs=start;
                        limit=maxV>>Math.max(0,maxL-cL);
                    }


                }


            }


        }



    }
}
