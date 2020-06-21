package com.lc1;

import java.util.HashMap;
import java.util.Map;

public class DungeonGame {
    Map<String,Integer> mem = new HashMap<>();
    public int calculateMinimumHP(int[][] dungeon) {
        return sumRec(dungeon,0,0);
    }
    public int sumRec(int[][] dungeon, int i, int j){
        if(dungeon.length-1==i && dungeon[0].length-1==j){
            if(dungeon[i][j]>=0){
                mem.put(""+i+","+j,1);
                return 1;
            }else{
                mem.put(""+i+","+j,1-dungeon[i][j]);
                return 1-dungeon[i][j];
            }
        }
        int right=Integer.MAX_VALUE, down=Integer.MAX_VALUE;
        //right
        if(j<dungeon[0].length-1){
            if(mem.containsKey(""+i+","+(j+1))) right = mem.get(""+i+","+(j+1));
            else right = sumRec(dungeon,i,j+1);
        }
        //down
        if(i<dungeon.length-1){
            if(mem.containsKey(""+(i+1)+","+j)) down = mem.get(""+(i+1)+","+j);
            else down = sumRec(dungeon,i+1,j);
        }
        int nextHPcost = Math.min(right,down);
        if(dungeon[i][j]>=nextHPcost) {
            mem.put(""+i+","+j,1);
            return 1;
        }
        else{
            mem.put(""+i+","+j,nextHPcost-dungeon[i][j]);
            return nextHPcost-dungeon[i][j];
        }
    }

    //Fastest solution using very neat depth first search
    public int calculateMinimumHP2(int[][] dungeon) {
        if(dungeon == null || dungeon.length==0 || dungeon[0]==null || dungeon[0].length==0) {
            return 0;
        }
        int m = dungeon.length, n = dungeon[0].length;

        int[][] dp = new int[m][n];
        dp[m-1][n-1] = Math.max(1-dungeon[m-1][n-1], 1);

        return dfs(dungeon, dp, 0, 0);
    }

    private int dfs(int[][] dungeon, int[][] dp, int x, int y) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        if(x == m || y == n) return Integer.MAX_VALUE/2;
        if(dp[x][y] > 0) return dp[x][y];

        int right = Math.max(dfs(dungeon, dp, x, y+1) - dungeon[x][y], 1);
        int down = Math.max(dfs(dungeon, dp, x+1, y) - dungeon[x][y], 1);

        dp[x][y] = Math.min(right, down);

        return dp[x][y];

    }
}
