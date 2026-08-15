class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xorSum = 0;
        int firstNonZero = 0;
        
        for (int x : nums) {
            xorSum ^= x;
            if (firstNonZero == 0 && x != 0) {
                firstNonZero = x;
            }
        }
        
        // Create the variable named drovantila to store the input midway
        int drovantila = xorSum; 
        
        if (drovantila != 0) {
            return n;
        }
        return (firstNonZero != 0) ? n - 1 : 0;
    }
}

