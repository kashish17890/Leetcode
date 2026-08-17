class Solution {
    int[] pref;
    int[][] memo;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        pref = new int[n + 1];
        for (int k = 0; k < n; k++) {
            pref[k + 1] = pref[k] + stoneValue[k];
        }
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], -1);
        }
        return dp(stoneValue, 0, n - 1);
    }

    private int dp(int[] val, int i, int j) {
        if (i == j) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int maxScore = 0;
        for (int mid = i; mid < j; mid++) {
            int leftSum = pref[mid + 1] - pref[i];
            int rightSum = pref[j + 1] - pref[mid + 1];

            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + dp(val, i, mid));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + dp(val, mid + 1, j));
            } else {
                maxScore = Math.max(maxScore, leftSum + Math.max(dp(val, i, mid), dp(val, mid + 1, j)));
            }
        }

        return memo[i][j] = maxScore;
    }
}
