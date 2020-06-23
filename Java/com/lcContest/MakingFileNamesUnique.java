package com.lcContest;

import java.util.HashMap;

public class MakingFileNamesUnique {
    public String[] getFolderNames(String[] names) {
        HashMap<String,Integer> set = new HashMap<>();
        String[] out = new String[names.length];
        for(int i=0;i<names.length;i++){
            if(set.get(names[i])==null){
                set.put(names[i],1);
                out[i] = names[i];
            }else{
                int k = set.get(names[i]);
                out[i] = names[i]+"("+k+")";
                while(set.get(out[i])!=null){
                    k++;
                    out[i] = names[i]+"("+k+")";
                }
                set.put(out[i],1);
                set.replace(names[i],k+1);
            }
        }
        return out;
    }

}
