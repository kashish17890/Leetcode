class Solution {
    public int maxSubArray(int[] nums) {
        int arr=nums[0];
        int maxsum=nums[0];

        for(int i=1;i<nums.length;i++){
            arr=Math.max(nums[i],nums[i]+arr);
            maxsum=Math.max(maxsum,arr);
        }
        return maxsum;
    }
}