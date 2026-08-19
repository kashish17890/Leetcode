class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        if (k == 1) {
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            int ans = -1;

            for (int num : map.keySet()) {
                if (map.get(num) == 1) {
                    ans = Math.max(ans, num);
                }
            }

            return ans;
        }

        if (k == n) {
            int ans = 0;

            for (int num : nums) {
                ans = Math.max(ans, num);
            }

            return ans;
        }

        int ans = -1;

        if (isUnique(nums, nums[0])) {
            ans = Math.max(ans, nums[0]);
        }

        if (isUnique(nums, nums[n - 1])) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }

    private boolean isUnique(int[] nums, int target) {
        int count = 0;

        for (int num : nums) {
            if (num == target) {
                count++;
            }
        }

        return count == 1;
    }
}