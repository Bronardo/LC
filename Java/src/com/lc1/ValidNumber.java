package com.lc1;

import java.util.HashMap;
import java.util.Map;

public class ValidNumber {
    public boolean isNumber(String s) {
        s=s.trim();
        if(s.length()==0) return false;
        s=s.replaceAll("^(\\+|\\-)?((\\d+\\.?\\d*)|(\\d*\\.?\\d+))(e(\\+|\\-)?\\d+)?$","");
        return s.length()==0;
    }
    //try using regex matcher
    public boolean isNumber4(String s) {
        s=s.trim();
        if(s.length()==0) return false;
        return s.matches("^(\\+|\\-)?((\\d+\\.?\\d*)|(\\d*\\.?\\d+))(e(\\+|\\-)?\\d+)?$");
    }
    //best speed
    public boolean isNumber2(String s) {
        boolean hasDec = false;
        boolean hasExp = false;
        boolean hasExpSign = false;
        boolean hasNum = false;
        boolean hasExpNum = false;
        int start = 0;
        while(start<s.length() && s.charAt(start) == ' '){
            start++;
        }
        int end = s.length()-1;
        while(end>=0 && s.charAt(end) == ' '){
            end--;
        }
        for(int i = start; i<=end; i++){
            if(s.charAt(i) == '.'){
                if(hasDec || hasExp){
                    return false;
                }else{
                    hasDec = true;
                }
            }else if(s.charAt(i) == '+' || s.charAt(i) == '-'){
                if(!hasExp && i!=start || hasExpSign || hasExpNum){
                    return false;
                }else if(!hasExpSign && hasExp){
                    hasExpSign = true;
                }
            }else if(s.charAt(i) == 'e'||s.charAt(i) == 'E'){
                if(hasExp || !hasNum){
                    return false;
                }else{
                    hasExp = true;
                }
            }else if(s.charAt(i)>='0' && s.charAt(i)<='9'){
                hasNum = true;
                if(hasExp){
                    hasExpNum = true;
                }
            }else{
                return false;
            }
        }
        return hasNum && !hasExp || hasExp && hasExpNum;
    }


    //Best Memory
    //https://leetcode.com/problems/valid-number/discuss/346042/C%2B%2BState-Machine-Solution-28-lines

    //https://leetcode.com/problems/valid-number/discuss/23805/C%2B%2B-DFA-Implementation-in-3ms

//     public boolean isNumber(String s) {

//         /* \\s - matches single whitespace character
//               +  one or more times
//               ?	once or not at all
//         */

//         // __+-123.456e+-789__
//         return s.matches("(\\s*)[+-]?((\\.[0-9]+)|([0-9]+(\\.[0-9]*)?))(e[+-]?[0-9]+)?(\\s*)");
//     }

    private int STATE_INVALID = Integer.MAX_VALUE;

    private enum CharType {
        DIGIT,
        DOT,
        SPACE,
        SIGN,
        EXP;

        public static CharType getInstance(char ch) {
            if(Character.isDigit(ch)) {
                return DIGIT;
            }

            if(ch == '.') {
                return DOT;
            }

            if(ch == ' ') {
                return SPACE;
            }

            if(ch == '-' || ch == '+' ) {
                return SIGN;
            }

            if(ch == 'e') {
                return EXP;
            }

            return null;
        }
    }

    public boolean isNumber3(String s) {

        s = s.trim();
        int state = 0;
        Map<CharType, Integer>[] dfa = buildDFA();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            state = dfa[state].getOrDefault(CharType.getInstance(ch), STATE_INVALID);
            if(state == STATE_INVALID) {
                return false;
            }
        }
        return isValidState(state);
    }

    private boolean isValidState(int state) {

        if(state == 2 || state == 4 || state == 6 ) {
            return true;
        }

        return false;
    }

    private Map<CharType, Integer>[] buildDFA() {

        Map<CharType, Integer>[] dfa = (Map<CharType, Integer>[]) new Map[8];

        //State 0
        Map<CharType, Integer> transitionMap = new HashMap<>();
        dfa[0] = transitionMap;
        transitionMap.put(CharType.DOT, 1);
        transitionMap.put(CharType.DIGIT, 2);
        transitionMap.put(CharType.SIGN, 3);

        //State 1
        transitionMap = new HashMap<>();
        dfa[1] = transitionMap;
        transitionMap.put(CharType.DIGIT, 4);

        //State 2
        transitionMap = new HashMap<>();
        dfa[2] = transitionMap;
        transitionMap.put(CharType.DIGIT, 2);
        transitionMap.put(CharType.DOT, 4);
        transitionMap.put(CharType.EXP, 5);

        //State 3
        transitionMap = new HashMap<>();
        dfa[3] = transitionMap;
        transitionMap.put(CharType.DOT, 1);
        transitionMap.put(CharType.DIGIT, 2);

        //State 4
        transitionMap = new HashMap<>();
        dfa[4] = transitionMap;
        transitionMap.put(CharType.DIGIT, 4);
        transitionMap.put(CharType.EXP, 5);

        //State 5
        transitionMap = new HashMap<>();
        dfa[5] = transitionMap;
        transitionMap.put(CharType.DIGIT, 6);
        transitionMap.put(CharType.SIGN, 7);

        //State 6
        transitionMap = new HashMap<>();
        dfa[6] = transitionMap;
        transitionMap.put(CharType.DIGIT, 6);

        //State 7
        transitionMap = new HashMap<>();
        dfa[7] = transitionMap;
        transitionMap.put(CharType.DIGIT, 6);
        return dfa;
    }


}
