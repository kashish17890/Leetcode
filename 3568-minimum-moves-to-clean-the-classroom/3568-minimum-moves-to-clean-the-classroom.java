import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startRow = 0, startCol = 0;
        int litterCount = 0;
        int[][] litterIndex = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterIndex[i], -1);
        }
        
        // Map the coordinates and track total litter items
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startRow = r;
                    startCol = c;
                } else if (ch == 'L') {
                    litterIndex[r][c] = litterCount++;
                }
            }
        }
        
        // Edge Case: No litter to collect at all
        if (litterCount == 0) {
            return 0;
        }
        
        int targetMask = (1 << litterCount) - 1;
        
        // visited array tracks: [row][col][remaining_energy][litter_mask]
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << litterCount];
        
        // Use an array-backed circular queue to bypass object creation overhead
        int[] queue = new int[m * n * (energy + 1) * (1 << litterCount)];
        int head = 0, tail = 0;
        
        int initialMask = 0;
        if (classroom[startRow].charAt(startCol) == 'L') {
            initialMask |= (1 << litterIndex[startRow][startCol]);
        }
        
        // Compress state into an integer: row(5 bits), col(5 bits), energy(6 bits), mask(10 bits)
        int initialState = (startRow << 21) | (startCol << 16) | (energy << 10) | initialMask;
        queue[tail++] = initialState;
        visited[startRow][startCol][energy][initialMask] = true;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int moves = 0;
        
        while (head < tail) {
            int size = tail - head;
            for (int k = 0; k < size; k++) {
                int curr = queue[head++];
                
                // Decode state variables
                int r = (curr >> 21) & 0x1F;
                int c = (curr >> 16) & 0x1F;
                int e = (curr >> 10) & 0x3F;
                int mask = curr & 0x3FF;
                
                if (mask == targetMask) {
                    return moves;
                }
                
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }
                    
                    int nextEnergy = e - 1;
                    if (nextEnergy < 0) {
                        continue;
                    }
                    
                    char nextCell = classroom[nr].charAt(nc);
                    int nextMask = mask;
                    if (nextCell == 'L') {
                        nextMask |= (1 << litterIndex[nr][nc]);
                    }
                    
                    // CRITICAL EDGE CASE: If your energy drops to exactly 0, you can only step 
                    // onto the cell if it's a Reset tile ('R') OR if it finishes the game.
                    if (nextEnergy == 0 && nextCell != 'R' && nextMask != targetMask) {
                        continue;
                    }
                    
                    if (nextCell == 'R') {
                        nextEnergy = energy;
                    }
                    
                    if (!visited[nr][nc][nextEnergy][nextMask]) {
                        visited[nr][nc][nextEnergy][nextMask] = true;
                        int nextState = (nr << 21) | (nc << 16) | (nextEnergy << 10) | nextMask;
                        queue[tail++] = nextState;
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}
