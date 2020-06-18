package com.lc1;

import java.util.*;

public class CheapestFlightsWithinKStops787 {
    // does not work; go to solution 2
    final int MAX = Integer.MAX_VALUE;
    public int solution1(int n, int[][] flights, int src, int dst, int K) {
        //implementation of Dijkstra's algorithm
        //since the constraint of up to k stops we need to store our data as <destination, price, prev, stops>
        //and stops need to init as k meaning,decrease stops for each update
        //once only one 1 stop remains, check if arrived at dst, if not return -1; other wise return that price
        //double infinity = Double.POSITIVE_INFINITY;
        //about init with infinity: since given price >0 so we check for 0 to be infinity.
        //However, this will cause problem if src and dst is the same, so we check it...if actually need to.
        //if(src==dst) return 0;
        //init data
        int[][] data = new int[n][4];
        for(int i=0;i<n;i++) {
            data[i][3] = K;
            data[i][1] = MAX;
        }
        data[src][1] = 0;
        //init unVisited
        List<Integer> unVisited = new ArrayList<>();
        for(int i=0;i<n;i++) unVisited.add(i);

        int current=-1;
        while(unVisited.size()>0){
            //find the min price current
            int min=MAX;
            boolean found = false;
            for(Integer i : unVisited){
                if(data[i][1]<min){
                    min= data[i][1];
                    current = i;
                    found =true;
                }
            }
            if(!found) break;

            for(int i=0;i<flights.length;i++){      //iterate flight
                if(flights[i][0]==current){         //find the adjacent flight
                    //calculate the distance = new flight's price + current flight price
                    int newPrice  = flights[i][2]+data[current][1];
                    int newDest = flights[i][1];
                    //compare with the current price
                    if(data[current][3]>=0){
                        if(data[newDest][1]>newPrice){
                            data[newDest][1]= newPrice;
                            data[newDest][2]= current;
                            data[newDest][3]=data[current][3]-1;
                        } else if(data[newDest][3]<0){
                            data[newDest][1]= newPrice;
                            data[newDest][2]= current;
                            data[newDest][3]=data[current][3]-1;
                        }
                    }
                    /*
                    if(data[newDest][1]newPrice && data[current][3]>=0) {
                        //we find a cheaper price but how about the stops?
                        //if adding a stop exceeds k then this stop is not valid;
                        //say we reach a destination with 2 pre one with better price one with better step.
                        data[newDest][1]= newPrice;
                        data[newDest][2]= current;
                        data[newDest][3]=data[current][3]-1;
                    }
                    if(data[newDest][3]<0 && data[current][3]>=0){
                        data[newDest][1]= newPrice;
                        data[newDest][2]= current;
                        data[newDest][3]=data[current][3]-1;
                    }
                    */
                }
            }
            unVisited.remove((Integer) current);
        }
        if(data[dst][3]<-1 || data[dst][1]==MAX) return -1;
        return data[dst][1];
    }

    //implementation of Bellman-Ford will not work
    public int solution2(int n, int[][] flights, int src, int dst, int K){
        int[] distance = new int[n];
        int[] predecessor = new int[n];
        for(int i = 0 ; i<n;i++) distance[i] = MAX;
        distance[src] = 0;

        //update distance K+1 times;
        for(int i=0;i<K+1;i++){
            for(int[] f : flights){
                if(distance[f[0]]+f[2] <distance[f[1]]){
                    distance[f[1]] = distance[f[0]]+f[2];
                    predecessor[f[1]] = f[0];
                }
            }
        }
        if(distance[dst]==MAX) return -1;
        return distance[dst];
    }

    //
    public int solution3(int n, int[][] flights, int src, int dst, int K){
        //try solve with 3d data for nth node and for k steps <price,prev>
        //init data
        int[][][] data = new int[n][K+1][2];
        //init visited tracker;
        Boolean[] visited = new Boolean[n];
        for(int i=0;i<n;i++) {
            visited[i]=false;
            for(int j=0;j<K+1;j++){
                data[i][j][0] = MAX;
            }
        }
        for(int i=0;i<K+1;i++){
            data[src][i][0] = 0;
        }
        //init toVisit which will store reachable
        LinkedList<Integer> toVisit = new LinkedList<>();

        visited[src]=true;
        toVisit.push(src);
        while(!toVisit.isEmpty()) {
            //pop a node to visit
            int current = toVisit.pop();
            visited[current] = true;
            for (int i = 0; i < flights.length; i++) {      //iterate flight can upgrade by traverse flights and
                if (flights[i][0] == current) {
                    //calculate the distance = new flight's price + current flight price
                    int newDest = flights[i][1];

                }
            }
        }

        return 0;

    }

    public int solution4(int n, int[][] flights, int src, int dst, int K){
        //have a priority queue to store the <n,price,steps> meaning cost to arrive at nth node
        //traverse the current node to find possible next step if found r
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] start = {src,0,0};
        pq.add(start);
        while(pq.size()>0){
            int[] current = pq.poll();
            if(current[0]==dst) return current[1];
            for(int[] flight :flights){
                //find neighbor
                if(flight[0]==current[0]){
                    //current<node,price,steps> flight<node,new node, price>
                    if(current[2]<=K){ //not exceeding the steps
                        int[] newEntry = {flight[1],current[1]+flight[2],current[2]+1};
                        pq.add(newEntry);
                    }
                }
            }
        }
        return -1;
    }


}
