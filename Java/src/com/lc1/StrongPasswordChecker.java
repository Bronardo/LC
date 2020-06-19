package com.lc1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StrongPasswordChecker {
    public int strongPasswordChecker(String s) {
        String lowers = "abcdefghijklmnopqrstuvwxyz";
        String uppers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        int n = s.length();
        if(n==0) return 6;
        boolean tooLong,tooShort,noUpper,noLower,noDigit;
        tooLong =false;
        tooShort=false;
        if(n>20) tooLong = true;
        if(n<6) tooShort = true;
        noUpper=true;
        noLower=true;
        noDigit=true;
        //flag[i] for the ith char 0:good 1:repeat 2:tooLong 3: tooShort 4: noUpper 5:noLower 6:noDigit
        //issueRepeat stores a repeat problem at ith char for j long
        LinkedList<int[]> issueRepeat = new LinkedList<>();
        //charSet stores all seen char with its locations; the list length is the number repeats
        HashMap<Character,List<Integer>> charSet = new HashMap<Character, List<Integer>>();
        //iterate through the s
        char a;
        char temp='\\';
        int repeatCount=0;
        for(int i=0;i<n;i++){
            a= s.charAt(i);
            if(noLower) if(lowers.indexOf(a)>=0) noLower =false;
            if(noUpper) if(uppers.indexOf(a)>=0) noUpper=false;
            if(noDigit) if(numbers.indexOf(a)>=0) noDigit=false;
            if(a==temp){
                temp = a;
                repeatCount++;
            }else{
                if(repeatCount>2){
                    int[] b = {i,repeatCount};
                    issueRepeat.add(b);
                }
                temp =a;
                repeatCount=1;
            }
        }
        if(repeatCount>2){
            int[] b = {n-1,repeatCount};
            issueRepeat.add(b);
        }
        int changes = 0;
        if(noUpper) changes++;
        if(noLower) changes++;
        if(noDigit) changes++;
        if(tooLong){
            if(!issueRepeat.isEmpty()){
                int deletion=n-20;
                int charChanges = changes;
                changes=0;
                for(int[] rep : issueRepeat){
                    if(deletion>0){
                        if(rep[1]-2<=deletion) {
                            deletion -=(rep[1]-2);
                            changes+=rep[1]-2;
                        }
                        if(rep[1]-2>deletion){
                            rep[1] = rep[1]-deletion;
                            changes+=deletion;
                            deletion=0;
                            //change strategy to modification
                            if(rep[1]/3<=charChanges){
                                changes+=rep[1]/3;
                                charChanges-=rep[1]/3;
                            }else{
                                changes+=rep[1]/3;
                                charChanges=0;
                            }
                        }
                    }else{
                        if(rep[1]/3<=charChanges){
                            changes+=rep[1]/3;
                            charChanges-=rep[1]/3;
                        }else{
                            changes+=rep[1]/3;
                            charChanges=0;
                        }
                    }
                }
                changes+=charChanges+deletion;
            }else{
                changes+= n - 20;
            }
        }else if(tooShort){
            if(!issueRepeat.isEmpty()){
                int addition = 6-n;
                int charChanges = changes;
                changes=0;
                int[] rep = issueRepeat.pop();
                if((rep[1]-1)/2<=addition){
                    addition = addition - ((rep[1]-1)/2);
                    changes+=(rep[1]-1)/2;
                    charChanges-=(rep[1]-1)/2;
                    changes+=Math.max(charChanges,addition);
                }else{
                    changes+=addition;
                    charChanges-=addition;
                    rep[1] = rep[1] -addition*2;
                    addition=0;
                    //change strategy to mod
                    changes+=Math.max(charChanges,rep[1]/3);
                }

            }else{
                changes = Math.max(changes,6-n);
            }
        }else{
            int mod = 0;
            while(!issueRepeat.isEmpty()){
                int[] rep = issueRepeat.pop();
                mod+= rep[1]/3;
            }
            changes = Math.max(changes,mod);
        }

        return changes;
    }

}
