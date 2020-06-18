package com.lcContest;

import java.util.*;

public class LeastNumberofUniqueIntegersafterKRemovals {
    public int solution1(int[] arr, int k){
        //put array into hashmap
        HashMap<Integer, Integer> keyCount = new HashMap<Integer,Integer>();
        for(int i=0;i<arr.length;i++){
            if(keyCount.containsKey(arr[i])){
                keyCount.replace(arr[i],keyCount.get(arr[i])+1);
            }
            else{
                keyCount.put(arr[i],1);
            }
        }
        //sort according count
        List<Integer> listCounts = new ArrayList<>(keyCount.values());
        Collections.sort(listCounts);
        //just work with the listCounts;
        int total = 0;
        int size = listCounts.size();
        for(int i=0;i<listCounts.size();i++){
            if(total+listCounts.get(i)<=k){
                size--;
                total = total +listCounts.get(i);
            }
            else{
                break;
            }
        }
        return size;


        //delete by counts;
        /*
        for(int i=0;i<listCounts.size();i++){
            if(k==0) break;
            int count = listCounts.get(i);
            if(count<=k){
                //delete that Count key;
                for (Map.Entry<Integer,Integer> entry : keyCount.entrySet()){
                    if(entry.getValue()==count){
                        keyCount.remove(entry.getKey());
                        k=k-count;
                        break;
                    }
                }
            }
            else{
                //remove from current count;
                for (Map.Entry<Integer,Integer> entry : keyCount.entrySet()){
                    if(entry.getValue()==count){
                        keyCount.replace(entry.getKey(),entry.getValue()-k);
                        k=0;
                        break;
                    }
                }

            }
        }
        int noIden = keyCount.size();
        return noIden;
        */

    }
    public int solutionBestRT(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: arr) {
            map.put(n,map.getOrDefault(n,0)+1);
        }
        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list);
        int count = 0;
        while (k > 0) {
            k -= list.get(count);
            count++;
        }
        if (k < 0) count--;
        return list.size()-count;
    }
    public int solutionBestMem(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : arr) freq.put(i, freq.getOrDefault(i, 0) + 1);
        PriorityQueue<Integer> pq = new PriorityQueue<>(freq.values());
        while (!pq.isEmpty() && k - pq.peek() >= 0) {
            k -= pq.remove();
        }
        return pq.size();
    }

}
