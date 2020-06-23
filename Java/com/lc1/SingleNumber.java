/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lc1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author BroNardo
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        Set<Integer> set  = new HashSet();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])) set.remove(nums[i]);
            else set.add(nums[i]);
        }
        return (int)(set.toArray()[0]);
    }
    public int solution2(int[] nums){
        
        int a=0;
        int b=0;
        for(int c:nums){
            int ta=(~a&b&c)|(a&~b&~c);
            b=(~a&~b&c)|(~a&b&~c);
            a=ta;
        }
        return a|b;
    }
}
