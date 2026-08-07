class Solution {
    public int maximumCandies(int[] candies, long k) {
        int left = 1;
        int right = 0;
        
        // Find the maximum candies in a single pile to set upper bound
        for (int candy : candies) {
            right = Math.max(right, candy);
        }
        
        int result = 0;
        
        // Binary search for the maximum candies per child
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canDistribute(candies, mid, k)) {
                result = mid; // mid is possible, try to find a larger valid size
                left = mid + 1;
            } else {
                right = mid - 1; // mid is too large, search smaller sizes
            }
        }
        
        return result;
    }
    
    // Helper function to check if we can give 'perChild' candies to 'k' children
    private boolean canDistribute(int[] candies, int perChild, long k) {
        long count = 0;
        for (int candy : candies) {
            count += candy / perChild;
            if (count >= k) {
                return true; // Early exit if we already have enough piles
            }
        }
        return count >= k;
    }
}
