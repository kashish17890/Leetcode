class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        long sum = 0;
        long max = 0;

        for(int i = 0; i < nums.length; i++) {

            sum += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            if(i >= k) {
                int out = nums[i-k];
                sum -= out;

                map.put(out, map.get(out) - 1);

                if(map.get(out) == 0) {
                    map.remove(out);
                }
            }

            if(i >= k-1 && map.size() == k) {
                max = Math.max(max, sum);
            }
        }

        return max;
    }
}