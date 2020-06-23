package com.lc1;

import java.util.*;

public class FindCriticalandPseudoCriticalEdgesinMinimumSpanningTree {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<Integer> criticals = new LinkedList<>();
        List<Integer> pseuduos = new LinkedList<>();

        Map<int[],Integer> edgesIndexMap = new HashMap<>();
        for(int i=0;i<edges.length;i++){
            edgesIndexMap.put(edges[i],i);
        }
        Arrays.sort(edges,(a,b)->Integer.compare(a[2],b[2]));
        int minWeight =findMST(n,edges,null,null);

        for(int[] e:edges){
            int index = edgesIndexMap.get(e);
            int weight = findMST(n, edges, null, e);
            if(weight > minWeight){
                criticals.add(index);
            }else{
                weight = findMST(n, edges, e, null);
                if(weight == minWeight){
                    pseuduos.add(index);
                }
            }
        }
        return Arrays.asList(criticals, pseuduos);
    }
    private int findMST(int n, int[][] edges, int[] with,int[] without){
        Graph g = new Graph(n);
        int weight =0;
        if(with !=null){
            g.addEdge(with[0],with[1]);
            weight+=with[2];
        }
        for(int[] e:edges){
            if(e!=without&&g.addEdge(e[0],e[1])){
                weight+=e[2];
            }
        }
        return g.edgeCounts==1? weight:Integer.MAX_VALUE;
    }
}

class Graph{
    int[] prev; //stores the root vertex this one connects to, is itself if not connect or is a root
    int edgeCounts; //stores the edges needed to complete a minumum spanning tree (not weighted) will be 1 if it is
    public Graph(int n){
        prev = new int[n];
        edgeCounts =n;
        for(int i=0;i<n;i++){
            prev[i] = i;
        }
    }
    public int findRoot(int i){
        if(prev[i]==i) return i;
        else{
            prev[i] = findRoot(prev[i]);
            return prev[i];
        }
    }
    public boolean addEdge(int i,int j){
        int r1 = findRoot(i);
        int r2 = findRoot(j);
        if(r1!=r2){
            edgeCounts--;
            prev[r1] = r2;
            return true;
        }else{
            return false;
        }
    }
}
