class Solution {
    public int countIslands(int[][] grid, int k) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int divisibleIslandCount = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If we find unvisited land (assuming land cells > 0, water = 0)
                if (grid[r][c] > 0 && !visited[r][c]) {
                    // Calculate the total value of this entire island
                    long islandTotal = dfs(grid, r, c, visited);
                    
                    // Check if the total island value is divisible by k
                    if (islandTotal % k == 0) {
                        divisibleIslandCount++;
                    }
                }
            }
        }

        return divisibleIslandCount;
    }

    private long dfs(int[][] grid, int r, int c, boolean[][] visited) {
        // Boundary checks, water checks, and visited checks
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || visited[r][c]) {
            return 0;
        }

        // Mark current cell as visited
        visited[r][c] = true;

        // Current cell value
        long sum = grid[r][c];

        // Traverse all 4 cardinal directions (up, down, left, right)
        sum += dfs(grid, r + 1, c, visited);
        sum += dfs(grid, r - 1, c, visited);
        sum += dfs(grid, r, c + 1, visited);
        sum += dfs(grid, r, c - 1, visited);

        return sum;
    }
}
