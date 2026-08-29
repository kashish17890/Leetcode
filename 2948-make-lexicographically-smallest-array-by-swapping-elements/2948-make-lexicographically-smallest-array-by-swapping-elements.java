import java.util.Arrays;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        
        // Sort indices based on the values in nums
        Arrays.sort(idx, (i, j) -> Integer.compare(nums[i], nums[j]));
        
        int[] ans = new int[n];
        for (int i = 0; i < n;) {
            int j = i + 1;
            // Group elements where the difference between adjacent sorted elements <= limit
            while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                ++j;
            }
            
            // Extract the original indices for this group and sort them
            Integer[] t = Arrays.copyOfRange(idx, i, j);
            Arrays.sort(t, (x, y) -> Integer.compare(x, y));
            
            // Assign the sorted values to the sorted original indices
            for (int k = i; k < j; ++k) {
                ans[t[k - i]] = nums[idx[k]];
            }
            
            i = j;
        }
        
        return ans;
    }
}