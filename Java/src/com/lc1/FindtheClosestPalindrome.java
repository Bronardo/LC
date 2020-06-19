package com.lc1;

import java.util.LinkedList;

public class FindtheClosestPalindrome {
    public String nearestPalindromic(String n) {
        //**..**a|c|b**..** if even number digits there is no c
        //compare (a,b)         b-5         b       b+5
        //                  a1  a2     a3   a4  a5  a6      a7
        //1: a+1
        //2:
        //3:
        //4:
        //5: a
        //6:a
        //7:
        int nlength = n.length();
        if(nlength<1) return n;
        if(n.equals("10")||n.equals("11")) return "9";
        boolean oddLength = nlength%2 ==1;
        int mid =  oddLength? nlength/2 : nlength/2-1;
        String big,equal,small,front,middle,tail;
        int bigger,same,smaller,origin,midInt;
        try{
            front = n.substring(0,mid);
        }catch(Exception e){
            front = "";
        }
        middle = n.substring(mid,mid+1);
        try{
            tail =n.substring(mid+1,nlength);
        }catch(Exception e){
            tail = "";
        }
        String newTail="";
        for(int i=front.length()-1;i>=0;i--){
            newTail = newTail+ front.charAt(i);
        }
        midInt=Integer.parseInt(middle);
        if(oddLength){
            big = front+(midInt+1) + newTail;
            equal = front+middle+newTail;
            small = front+(midInt-1)+newTail;
        }else{
            big = front+(midInt+1)+(midInt+1) + newTail;
            equal = front+middle+middle+newTail;
            small = front+(midInt-1)+(midInt-1)+newTail;
        }

        bigger = Integer.parseInt(big.substring(mid,nlength));
        same = Integer.parseInt(equal.substring(mid,nlength));
        smaller = Integer.parseInt(small.substring(mid,nlength));
        origin = Integer.parseInt(n.substring(mid,nlength));
        int diff1, diff2,diff3;
        if(origin==same){
            diff1= Math.abs(origin - bigger);
            diff3= Math.abs(origin-smaller);
            if(diff1<diff3){
                return big;
            } else return small;
        }
        diff1 = Math.abs(origin-bigger);
        diff2 = Math.abs(origin- same);
        diff3 = Math.abs(origin - smaller);
        int temp = Integer.MAX_VALUE;
        if(temp>diff1){
            temp=diff1;
            if(temp>diff2){
                temp=diff2;
                if(temp>diff3){
                    temp=diff3;
                }
            }
        }
        if(temp==diff1) return big;
        if(temp==diff2) return equal;
        if(temp==diff3) return small;

        //even length
//
//        front = n.substring(0,mid-1);
//        middle = n.substring(mid,mid);
//        tail = n.substring(mid+1,nlength-1);
//        String newTail="";
//        for(int i=front.length()-1;i>0;i--){
//            newTail = newTail+ front.charAt(i);
//        }
//        midInt=Integer.parseInt(middle);
//        big = front+(midInt+1)+(midInt+1) + newTail;
//        equal = front+middle+newTail;
//        small = front+(midInt-1)+(midInt-1)+newTail;
//        bigger = Integer.parseInt(big.substring(mid,nlength-1));
//        same = Integer.parseInt(equal.substring(mid,nlength-1));
//        smaller = Integer.parseInt(small.substring(mid,nlength-1));
//        origin = Integer.parseInt(n.substring(mid,nlength-1));
//        int diff1, diff2,diff3;
//        if(origin==same){
//            diff1= Math.abs(origin - bigger);
//            diff3= Math.abs(origin-smaller);
//            if(diff1<diff3){
//                return big;
//            } else return small;
//        }
//        diff1 = Math.abs(origin-bigger);
//        diff2 = Math.abs(origin- same);
//        diff3 = Math.abs(origin - smaller);
//        int temp = Integer.MAX_VALUE;
//        if(temp>diff1){
//            temp=diff1;
//            if(temp>diff2){
//                temp=diff2;
//                if(temp>diff3){
//                    temp=diff3;
//                }
//            }
//        }
//        if(temp==diff1) return big;
//        if(temp==diff2) return equal;
//        if(temp==diff3) return small;

        return "";
    }
    public String solve2(String n){
        int nlength = n.length();
        if(nlength<1) return n;
        boolean oddLength = nlength%2 ==1;
        int mid =  oddLength? nlength/2 : nlength/2-1;
        Palindrome same;
        if(oddLength){
            same = new Palindrome(n.substring(0,mid),n.substring(mid,mid+1));
        }else{
            same = new Palindrome(n.substring(0,mid+1),"");
        }
        long diff1;
        diff1 =  Long.parseLong(same.string) -Long.parseLong(n);
        if(diff1==0){
            Palindrome next = same.nextPalindrome();
            Palindrome prev = same.prevPalindrome();
            long diff2 = Long.parseLong(next.string) - Long.parseLong(n);
            long diff3 = Long.parseLong(prev.string) - Long.parseLong(n);
            if(Math.abs(diff2)>=Math.abs(diff3))
                return prev.string;
            else
                return next.string;
        }else {
            Palindrome other;
            other = diff1 > 0 ? same.prevPalindrome() : same.nextPalindrome();
            long diff2 = Long.parseLong(other.string) - Long.parseLong(n);
            if(Math.abs(diff1)==Math.abs(diff2)){
                return diff1<0? same.string:other.string;
            }else if(Math.abs(diff1) > Math.abs(diff2)) {
                return other.string;
            } else {
                return same.string;
            }
        }
    }
    public class Palindrome{
        String frontString,midString,tailString,string;
        int frontValue,midValue,tailValue;
        public Palindrome(String front,String mid){
            frontString = front;
            midString = mid;
            tailString="";
            if(frontString.length()>0)
                frontValue=Integer.parseInt(frontString);
            if(midString.length()>0) {
                midValue = Integer.parseInt(midString);
            }
            for (int i = frontString.length() - 1; i >= 0; i--) {
                tailString = tailString + frontString.charAt(i);
            }
            if(tailString.length()>0){
                tailValue = Integer.parseInt(tailString);
            }
            string=frontString+midString+tailString;
        }

        public Palindrome nextPalindrome(){
            Palindrome a = new Palindrome(frontString,midString);
            if(midString.length()>0){
                //carry up
                if(midValue==9){
                    a.midValue=0;a.midString=""+0;
                    //exchange bit
                    if(Math.pow(10,a.frontString.length())-a.frontValue==1){
                        a.midString="";
                        a.frontValue= a.frontValue+1;
                    }
                    a.frontString=""+a.frontValue;
                }else{
                    a.midValue = a.midValue+1;
                    a.midString = ""+a.midValue;
                }
            }else{
                //exchange bit
                if(Math.pow(10,a.frontString.length())-a.frontValue==1){
                    a.midString="0";
                    a.frontValue= (a.frontValue+1)/10;
                }else{
                    a.frontValue = a.frontValue+1;
                }
                a.frontString = ""+a.frontValue;
            }
            return new Palindrome(a.frontString,a.midString);
        }
        public Palindrome prevPalindrome(){
            Palindrome a = new Palindrome(frontString,midString);
            if(a.midString.length()>0){
                //carry down
                if(a.midValue==0){
                    //exchange bit
                    if(Math.pow(10,a.frontString.length()-1) ==  a.frontValue){
                        a.midString="";
                        a.frontValue = a.frontValue*10-1;
                    }else{
                        a.midString= "9";
                        a.frontValue = a.frontValue-1;
                    }
                    a.frontString =a.frontValue==0?"":""+ a.frontValue;
                }else{
                    a.midValue = a.midValue-1;
                    a.midString = ""+a.midValue;
                }
            }else{
                //exchange bit
                if(Math.pow(10,a.frontString.length()-1) ==  a.frontValue){
                    a.midString="9";
                    a.frontValue = a.frontValue-1;
                }else{
                    a.frontValue= a.frontValue-1;
                }
                a.frontString =a.frontValue==0?"":""+ a.frontValue;
            }

            return new Palindrome(a.frontString,a.midString);
        }

    }
}
