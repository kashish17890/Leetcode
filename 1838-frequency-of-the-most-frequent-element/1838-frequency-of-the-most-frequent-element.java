class Solution {
    public int maxFrequency(int[] nums, int k) {
        java.util.Arrays.sort(nums);
        int left = 0;
        int maxFreq = 0;
        long currentSum = 0;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
           
            while ((long) nums[right] * (right - left + 1) > currentSum + k) {
                currentSum -= nums[left];
                left++;
            }
            
            maxFreq = Math.max(maxFreq, right - left + 1);
        }
        
        return maxFreq;
    }
}
