class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Step 1: Flood-fill all land (0) connected to the boundaries.
        // These can never form a closed island.
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == 0 || r == rows - 1 || c == 0 || c == cols - 1) {
                    if (grid[r][c] == 0) {
                        dfs(grid, r, c);
                    }
                }
            }
        }

        // Step 2: Count the remaining interior closed islands.
        int closedIslandsCount = 0;
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (grid[r][c] == 0) {
                    closedIslandsCount++;
                    dfs(grid, r, c); // Mark the entire island as visited (1)
                }
            }
        }

        return closedIslandsCount;
    }

    private void dfs(int[][] grid, int r, int c) {
        // Boundary check and water check
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 1) {
            return;
        }

        // Turn the land into water to mark it as visited
        grid[r][c] = 1;

        // Traverse in all 4 directions
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
