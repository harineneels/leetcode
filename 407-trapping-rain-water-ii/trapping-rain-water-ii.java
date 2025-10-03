import java.util.*;

public class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        if (m <= 2 || n <= 2) return 0; // No space to trap water

        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Cell> heap = new PriorityQueue<>(Comparator.comparingInt(c -> c.height));

        // Add all boundary cells to the heap
        for (int i = 0; i < m; i++) {
            heap.offer(new Cell(i, 0, heightMap[i][0]));
            heap.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }
        for (int j = 1; j < n - 1; j++) {
            heap.offer(new Cell(0, j, heightMap[0][j]));
            heap.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }

        int totalWater = 0;
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        while (!heap.isEmpty()) {
            Cell cell = heap.poll();
            for (int[] d : dirs) {
                int x = cell.row + d[0];
                int y = cell.col + d[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) continue;

                visited[x][y] = true;
                int neighborHeight = heightMap[x][y];
                totalWater += Math.max(0, cell.height - neighborHeight);
                heap.offer(new Cell(x, y, Math.max(neighborHeight, cell.height)));
            }
        }

        return totalWater;
    }

    static class Cell {
        int row, col, height;
        Cell(int r, int c, int h) {
            row = r;
            col = c;
            height = h;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] heightMap1 = {
            {1,4,3,1,3,2},
            {3,2,1,3,2,4},
            {2,3,3,2,3,1}
        };
        System.out.println(sol.trapRainWater(heightMap1)); // Output: 4

        int[][] heightMap2 = {
            {3,3,3,3,3},
            {3,2,2,2,3},
            {3,2,1,2,3},
            {3,2,2,2,3},
            {3,3,3,3,3}
        };
        System.out.println(sol.trapRainWater(heightMap2)); // Output: 10
    }
}
