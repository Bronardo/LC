package com.lc1;

import java.util.Collections;
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
    public int strongPasswordChecker2(String s) {
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
        LinkedList<Integer> issueRepeat = new LinkedList<>();
        //charSet stores all seen char with its locations; the list length is the number repeats
        //HashMap<Character,List<Integer>> charSet = new HashMap<Character, List<Integer>>();
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
                    issueRepeat.add(repeatCount);
                }
                temp =a;
                repeatCount=1;
            }
        }
        if(repeatCount>2){
            issueRepeat.add(repeatCount);
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
                Collections.sort(issueRepeat);
//                int p = 0;
//                while(deletion>0){
//                    int repLength = issueRepeat.get(p);
//                    boolean removedFlag = false;
//                    if(repLength>=3)
//                        issueRepeat.set(p,issueRepeat.get(p)-1);
//                    else {
//                        issueRepeat.remove(p);
//                        removedFlag =true;
//                    }
//                    changes++;
//                    deletion--;
//                    if(issueRepeat.size()==0) break;
//                    if(removedFlag){
//                        if(p>issueRepeat.size()-1) p=0;
//                    }else{
//                        p++;
//                        if(p>issueRepeat.size()-1) p=0;
//                    }
//                }
                for(int rep : issueRepeat){
                    if(deletion>0){
                        if(rep-2<=deletion) {
                            deletion -=(rep-2);
                            changes+=rep-2;
                        }
                        else if(rep-2>deletion){
                            rep = rep-deletion;
                            changes+=deletion;
                            deletion=0;
                            //change strategy to modification
                            if(rep/3<=charChanges){
                                changes+=rep/3;
                                charChanges-=rep/3;
                            }else{
                                changes+=rep/3;
                                charChanges=0;
                            }
                        }
                    }else{
                        if(rep/3<=charChanges){
                            changes+=rep/3;
                            charChanges-=rep/3;
                        }else{
                            changes+=rep/3;
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
                int rep = issueRepeat.pop();
                if((rep-1)/2<=addition){
                    addition = addition - ((rep-1)/2);
                    changes+=(rep-1)/2;
                    charChanges-=(rep-1)/2;
                    changes+=Math.max(charChanges,addition);
                }else{
                    changes+=addition;
                    charChanges-=addition;
                    rep = rep -addition*2;
                    addition=0;
                    //change strategy to mod
                    changes+=Math.max(charChanges,rep/3);
                }

            }else{
                changes = Math.max(changes,6-n);
            }
        }else{
            int mod = 0;
            while(!issueRepeat.isEmpty()){
                int rep = issueRepeat.pop();
                mod+= rep/3;
            }
            changes = Math.max(changes,mod);
        }

        return changes;
    }
    /*If length of the string is larger than 20, we will need to delete some chars and appropriate deletion can
     reduce the number of replacements needed to break repeating sequences after deletion. For any repeating
     sequences with len % 3 == 0, we need to make len/3 replacements and we can reduce the number of replacements
     len/3 by one by deleting one character. Similarly, for any repeating sequences with len % 3 == 1, we can
     reduce one replacement by deleting two characters. Now we know that for all sequences, including those already
     deleted chars from, we have len % 3 == 2 so we can reduce every replacement by deleting three characters such
     that every deleting action counts. @hzhu007
    */
    public int strongPasswordChecker3(String s) {
        int one = 0, two = 0, chg = 0, p = 0, l = s.length(), r = 0, up = 0, lo = 0, d = 0;
        while (p < l) {
            char c = s.charAt(p);
            if (Character.isUpperCase(c)) up = 1;
            if (Character.isLowerCase(c)) lo = 1;
            if (Character.isDigit(c)) d = 1;
            if (p > 1 && c == s.charAt(p - 1) && c == s.charAt(p - 2)) {
                r = 2;
                while (p < l && s.charAt(p) == c) {
                    p++;
                    r++;
                }
                if (r % 3 == 0) one++;
                else if(r % 3 == 1) two++;
                chg += r / 3;
            } else p++;
        }
        int miss = 3 - up - lo - d;
        if (l < 6) {
            return Math.max(miss, 6 - l);
        } else if (l <= 20) {
            return Math.max(miss, chg);
        } else{
            int del = l - 20;
            chg -= Math.min(del, one);
            chg -= Math.min(Math.max(del - one, 0), two * 2) / 2;
            chg -= Math.max(del - one - 2 * two, 0) / 3;
            return del + Math.max(chg, miss);
        }
    }


}
