class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Calculate prefix sums in-place to save space
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // Step 2: Initialize base case
        // If the game reaches the last possible state, the player must take all remaining stones.
        int maxDifference = stones[n - 1];
        
        // Step 3: Bottom-up DP transition from right to left
        for (int i = n - 2; i > 0; i--) {
            // dp[i] = max(dp[i + 1], pref[i] - dp[i + 1])
            maxDifference = Math.max(maxDifference, stones[i] - maxDifference);
        }
        
        return maxDifference;
    }
}
