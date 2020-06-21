package com.lc1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestDuplicateSubstring {
    long mod = Long.MAX_VALUE/26;
    public String longestDupSubstring(String S) {
        int n = S.length();
        if(n<2) return S;
        //from 1 binary search up for a possible solution;
        int left = 0;
        int right = n-1;
        String result="";
        while(right>=left){
            int mid = (left+right)/2;
            Set<Long> set = new HashSet<>();
            long hash = 0,power = 1;
            boolean found = false;
            for(int i=0;i<mid;i++){
                hash = (26 * hash + (S.charAt(i) - 'a'))%mod;
                power = (power * 26) % mod;
            }
            set.add(hash);

            for(int i = 0; i + mid < n; i++){
                hash = (hash*26 + (S.charAt(i + mid) - 'a') - ((S.charAt(i) - 'a')*power))%mod;
                if(hash < 0) hash += mod;
                if(set.contains(hash)){
                    found=true;
                    result = S.substring(i + 1, i + mid + 1);
                    break;
                }

                set.add(hash);
            }

            if(found){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return result;
    }

    public String binarySearch(String S, int left, int right){
        String result ="";
        //try search for dup sub of length mid;
        if(right>=left){
            int mid = (left+right)/2;
            result = RabinKarp(S,mid);
            if(result.length()==0){
                return binarySearch(S,left,mid-1);
            }else{
                String temp  = binarySearch(S,mid+1,right);
                return temp.length()>0? temp:result;
            }
        }
        return result;
    }
    public String patternSearch(String S, int K){
        int n = S.length();
        for(int i=0;i+K<=n-1;i++){
            String a = S.substring(i,i+K);
            if(a.compareTo("akyj")==0){
                System.out.println(a);
            }
            if(S.indexOf(a,i+1)>=0) return a;
            //checking the substring start at i with length K have another substring.
//            for(int j=i+1;j+K<=n;j++){
//                if(S.substring(j,j+K).indexOf())
//            }
        }
        return "";
    }

    //the hint says Rabin Karp but in java we have String.indexOf AND both O(n+m) -> O(nm). so....really?
    //Yes! says String.indexOf is not good enough.
    //lets just use rolling hash.
    public String RabinKarp(String S, int K){
        int n=S.length();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i+K<=n;i++){
            String sub = S.substring(i,i+K);
            int hash = sub.hashCode();
            if(map.size()==0){
                map.put(hash,1);
            }else if(map.containsKey(hash)) return sub;
            else map.put(hash,1);
        }
        return "";
    }
    //below is a trie node solution

    private String S;

    // T = O(N*K), S = O(N), where N is length of S and K avg depth of trie.
    public String longestDupSubstring2(String S) {
        this.S = S;
        int maxLo = 0, maxLength = 0;
        TrieNode root = new TrieNode(0, 0);
        for (int i = 1; i + maxLength < S.length(); i++) {
            int len = addNew(root, i);
            if (len > maxLength) {
                maxLength = len;
                maxLo = i;
            }
        }
        return S.substring(maxLo, maxLo + maxLength);
    }

    private boolean isLeaf(TrieNode node) {
        return node.next == null;
    }

    private int getIndex(int i, int depth) {
        return S.charAt(i + depth) - 'a';
    }

    private int addNew(TrieNode node, int i) {
        int depth = node.depth;
        if (i + depth == S.length()) return depth;
        if (isLeaf(node)) {
            node.next = new TrieNode[26];
            node.next[getIndex(node.i, node.depth)] = new TrieNode(node.i, depth + 1);
        }
        int c = getIndex(i, node.depth);
        TrieNode x = node.next[c];
        if (x == null) {
            node.next[c] = new TrieNode(i, depth + 1);
            return depth;
        }
        return addNew(x, i);
    }

    private static class TrieNode {
        private TrieNode[] next;
        private int i;
        private int depth;

        public TrieNode(int i, int depth) {
            this.i = i;
            this.depth = depth;
        }
    }
}
