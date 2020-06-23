package com.lc1;

import java.util.*;

public class SurroundedRegions {
    private class Cluster{
        boolean[][] map;
        boolean isLive;
        Cluster(int row, int colum){
            isLive = false;
            map = new boolean[row][colum];
            for(boolean[] r : map)
                Arrays.fill(r,false);
        }
    }

    int row = 0 ;
    int colum = 0;
    boolean breathing = false;
    boolean[][] visited;
    char[][] temp;
    public void solve(char[][] board) {
        /*states of O
        1. Surrounded -> to X
        2. on the edge -> live
        3. connected to another O ->
            beathing ? live : change to X
        */
        temp = board;
        row = board.length;
        if(row ==0) return;
        colum = board[0].length;
        if(colum == 0) return;
        visited  = new boolean[row][colum];
        //init not required as above inits false by default
//        for(int i=0;i<row;i++){
//            for(int j=0;j<colum;j++){
//                visited[i][j] = false;
//            }
//        }

        for(int i=1;i<row-1;i++){
            for(int j=1;j<colum-1;j++){
                if(i==1 && j==4){
                    System.out.println('1');
                }
                if(board[i][j]=='O' && !visited[i][j]){
                    breathing=false;
                    findBreatheRec(board,i,j);
                    if(!breathing) flip(board,i,j);
                }
            }
        }
    }

    private void findBreatheRec(char[][] board,int i , int j){
        if(i<0||i>row-1||j<0||j>colum-1) return;
        if(board[i][j]=='X' || visited[i][j]) return;
        if(i==0||i==row-1||j==0||j==colum-1) breathing  = true;
        visited[i][j]=true;
        findBreatheRec(board,i-1,j);
        findBreatheRec(board,i+1,j);
        findBreatheRec(board,i,j-1);
        findBreatheRec(board,i,j+1);
    }

    private void flip(char[][] board,int i, int j){
        if(i<0||i>row-1||j<0||j>colum-1) return;
        if(board[i][j]=='X') return;
        temp[i][j] = 'X';
        flip(board,i-1,j);
        flip(board,i+1,j);
        flip(board,i,j-1);
        flip(board,i,j+1);
    }

//    private boolean isEdgeO(char a,int i, int j){
//        if(a=='O'&&(i==0||j==0||i==row-1||j==colum-1)) return true;
//        return false;
//    }


    public void solveBestMem(char[][] board) {
        int m = board.length;
        if (m < 3) {
            return;
        }
        int n = board[0].length;
        if (n < 3) {
            return;
        }
        // O(mn) space
        Queue<List<Integer>> q = new LinkedList<>();
        // O(mn) space
        // O(mn) time
        Set<List<Integer>> visited = new HashSet<>();
        // O(n) time
        for (int c = 0; c < n; ++c) {
            if (board[0][c] == 'O') {
                List<Integer> pair = Arrays.asList(0, c);
                q.add(pair);
                visited.add(pair);
            }
            if (board[m - 1][c] == 'O') {
                List<Integer> pair = Arrays.asList(m - 1, c);
                q.add(pair);
                visited.add(pair);
            }
        }
        // O(m) time
        for (int r = 1; r < m - 1; ++r) {
            if (board[r][0] == 'O') {
                List<Integer> pair = Arrays.asList(r, 0);
                q.add(pair);
                visited.add(pair);
            }
            if (board[r][n - 1] == 'O') {
                List<Integer> pair = Arrays.asList(r, n - 1);
                q.add(pair);
                visited.add(pair);
            }
        }
        // O(mn) time
        while (!q.isEmpty()) {
            List<Integer> pair = q.remove();
            int r = pair.get(0);
            int c = pair.get(1);
            List<Integer> upPair = Arrays.asList(r - 1, c);
            if (r > 0 && !visited.contains(upPair) && board[r - 1][c] == 'O') {
                q.add(upPair);
                visited.add(upPair);
            }
            List<Integer> leftPair = Arrays.asList(r, c - 1);
            if (c > 0 && !visited.contains(leftPair) && board[r][c - 1] == 'O') {
                q.add(leftPair);
                visited.add(leftPair);
            }
            List<Integer> rightPair = Arrays.asList(r, c + 1);
            if (c < n - 1 && !visited.contains(rightPair) && board[r][c + 1] == 'O') {
                q.add(rightPair);
                visited.add(rightPair);
            }
            List<Integer> downPair = Arrays.asList(r + 1, c);
            if (r < m - 1 && !visited.contains(downPair) && board[r + 1][c] == 'O') {
                q.add(downPair);
                visited.add(downPair);
            }
        }
        // O(mn) time
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (!visited.contains(Arrays.asList(r, c))) {
                    board[r][c] = 'X';
                }
            }
        }
    }

}
