class Solution {
    public long maximumTotalSum(int[] maximumHeight) {
        Arrays.sort(maximumHeight);

        long sum = 0;
        int prev = Integer.MAX_VALUE;

        for (int i = maximumHeight.length - 1; i >= 0; i--) {
            int curr = Math.min(maximumHeight[i], prev - 1);

            if (curr <= 0) {
                return -1;
            }

            sum += curr;
            prev = curr;
        }

        return sum;
    }
}