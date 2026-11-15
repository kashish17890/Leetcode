class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        
        // count[0] tracks even prefix sums (initialized to 1 for an empty prefix of sum 0)
        // count[1] tracks odd prefix sums
        int[] count = new int[]{1, 0};
        
        int prefixSum = 0;
        int result = 0;
        
        for (int num : arr) {
            prefixSum += num;
            int parity = prefixSum & 1; // 0 if even, 1 if odd
            
            // Odd sum requires pairing current prefix with an opposite-parity prefix
            result = (result + count[parity ^ 1]) % MOD;
            
            // Record the current prefix sum parity
            count[parity]++;
        }
        
        return result;
    }
}
