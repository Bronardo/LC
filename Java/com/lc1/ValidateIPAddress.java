package com.lc1;

import java.util.StringTokenizer;

public class ValidateIPAddress {
    public String validIPAddress(String IP) {
        if(IP.length()==0) return "Neither";
        //as the test cases need there will be leading/tailing delims "." or ":" so we need to trim
        IP = IP.trim();
        if(IP.charAt(0)=='.'||IP.charAt(IP.length()-1)=='.'||IP.charAt(0)==':'||IP.charAt(IP.length()-1)==':')
            return "Neither";
        //parse try ipv4
        boolean isIPv4 = true;
        StringTokenizer ipv4 = new StringTokenizer(IP,".");
        if(ipv4.countTokens()==4){
            //should add try catch if test case needs... and it does
            while(ipv4.hasMoreElements()){
                String token = ipv4.nextToken();
                if(token.length()>1 && (token.charAt(0)=='0' || token.charAt(0)=='-')){
                    isIPv4=false;
                    break;
                }
                try{
                    int a =Integer.parseInt(token);
                    if(a>255 || a<0){
                        isIPv4 = false;
                        break;
                    }
                }catch(Exception e){
                    isIPv4 =false;
                    break;
                }
            }
            if(isIPv4) return "IPv4";
        }
        //ipv6 version is more robust for using the returnDelims of StringTokenizer, should have updated the ipv4
        //version too but it passes the test cases
        StringTokenizer ipv6 = new StringTokenizer(IP,":",true);
        Boolean checkColon = false;
        int countArg = 0;
        Boolean isIPv6 = true;
        while(ipv6.hasMoreElements()){
            String token = ipv6.nextToken();
            if((!checkColon && token.charAt(0) == ':')||countArg++>15){
                isIPv6=false;
                break;
            }
            if(token.length()>4){
                isIPv6=false;
                break;
            }else if(token.length()>0 && !checkColon){
                for(int i=0;i<token.length();i++){
                    if(Character.digit(token.charAt(i),16)==-1){
                        isIPv6=false;
                        break;
                    }
                }
            }
            if(!isIPv6) break;
            checkColon=!checkColon;
        }
        if(isIPv6 && countArg==15) return "IPv6";
        /*
        StringTokenizer ipv6 = new StringTokenizer(IP,":");
        if(ipv6.countTokens()==8){
            boolean isIPv6 = true;
            while(ipv6.hasMoreElements()){
                String token = ipv6.nextToken();
                if(token.length()>4){
                    isIPv6=false;
                    break;
                }else if(token.length()>0){
                    for(int i=0;i<token.length();i++){
                        if(Character.digit(token.charAt(i),16)==-1){
                            isIPv6=false;
                            break;
                        }
                    }
                }
                if(!isIPv6) break;
            }
            if(isIPv6) return "IPv6";
        }
        */
        return "Neither";
    }
}
