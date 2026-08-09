import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        // Map to store: [edge position -> count of rows having an edge here]
        Map<Integer, Integer> edgeCounts = new HashMap<>();
        int maxEdges = 0;
        
        // Iterate through each row of the brick wall
        for (List<Integer> row : wall) {
            int currentEdgePosition = 0;
            
            // Loop through bricks EXCEPT the very last brick of the row
            // (We ignore the final edge because the problem forbids cutting the outer borders)
            for (int i = 0; i < row.size() - 1; i++) {
                currentEdgePosition += row.get(i);
                
                // Track how many rows share this exact edge position
                int currentCount = edgeCounts.getOrDefault(currentEdgePosition, 0) + 1;
                edgeCounts.put(currentEdgePosition, currentCount);
                
                // Update our global maximum of aligned edges
                maxEdges = Math.max(maxEdges, currentCount);
            }
        }
        
        // Minimum bricks cut = Total rows - Maximum aligned edges
        return wall.size() - maxEdges;
    }
}
