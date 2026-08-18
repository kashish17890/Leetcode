public class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // This tracks: Number -> How many subarrays it appears in
        Map<Integer, Integer> subarrayCounts = new HashMap<>();
        
        // 1. Move the window across the array
        for (int i = 0; i <= n - k; i++) {
            
            // Temporary set to avoid counting duplicate numbers inside the SAME window
            Set<Integer> uniqueInWindow = new HashSet<>();
            
            // 2. Collect all numbers inside the current window
            for (int j = i; j < i + k; j++) {
                uniqueInWindow.add(nums[j]);
            }
            
            // 3. Update our global hashmap for each unique number found in this window
            for (int num : uniqueInWindow) {
                int currentCount = subarrayCounts.getOrDefault(num, 0);
                subarrayCounts.put(num, currentCount + 1);
            }
        }
        
        // 4. Find the largest number that appeared in EXACTLY 1 subarray
        int largestResult = -1;
        for (int num : subarrayCounts.keySet()) {
            int appearances = subarrayCounts.get(num);
            
            if (appearances == 1) {
                largestResult = Math.max(largestResult, num);
            }
        }
        
        return largestResult;
    }
}

