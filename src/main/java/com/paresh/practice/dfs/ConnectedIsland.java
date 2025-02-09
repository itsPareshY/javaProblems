package com.paresh.practice.dfs;

public class ConnectedIsland {

    public int countIsland (int[][] arr){
        int count = 0;

        int rowCount = arr.length;
        int colCount = arr[0].length;

        for (int row = 0 ; row < rowCount ; row++){
            for(int col = 0 ; col < colCount ; col++){
                if(arr[row][col] == 1){
                    count ++;
                    //perform dfs and mark all connected island row.e. 1 as 0(visited)
                    dfs(arr, row, col , rowCount, colCount);
                }
            }
        }
        return count;
    }

    public void dfs(int[][] arr , int row, int col, int rowCount, int colCount){
        // Check bounds and whether the current cell is land ('1')
        if(row < 0 || col < 0 || row >= rowCount || col >= colCount || arr[row][col] != 1){
            return;
        }

        // Mark the current cell as visited by changing '1' to '0'
        arr[row][col] = 0;

      /*
      Simpler way to explore the four possible directions (up, down, left, right)
      int[] rowOffsets = {-1, 1, 0, 0};
        int[] colOffsets = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            dfs(arr, row + rowOffsets[i], col + colOffsets[i], rowCount, colCount);
        }
      */
        // Explore the four possible directions (up, down, left, right)
        dfs(arr, row + 1, col, rowCount, colCount); // Down
        dfs(arr, row - 1, col, rowCount, colCount); // Up
        dfs(arr, row, col + 1, rowCount, colCount); // Right
        dfs(arr, row, col - 1, rowCount, colCount); // Left

        /**
         * For Diagonal connection
         *   dfs(grid, row + 1, col + 1, rowCount, colCount); // Down-right
         *     dfs(grid, row - 1, col - 1, rowCount, colCount); // Up-left
         *     dfs(grid, row - 1, col + 1, rowCount, colCount); // Up-right
         *     dfs(grid, row + 1, col - 1, rowCount, colCount); // Down-left
         */
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1}
        };

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Connected islads : " + new ConnectedIsland().countIsland(grid));

    }
}
