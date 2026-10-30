
class Solution {
    public int[] maxSlidingWindow(int[] arr, int k) {

        int n = arr.length;

        int[] left = new int[n];
        int[] right = new int[n];
        int[] ans = new int[n-k+1];

        // Calculate left maximum
        for(int i = 0; i < n; i++) {
            if(i % k == 0)
                left[i] = arr[i];
            else
                left[i] = Math.max(left[i-1], arr[i]);
        }

        // Calculate right maximum
        for(int i = n-1; i >= 0; i--) {
            if(i == n-1 || (i+1) % k == 0)
                right[i] = arr[i];
            else
                right[i] = Math.max(right[i+1], arr[i]);
        }

        // Find maximum of every window
        for(int i = 0; i <= n-k; i++) {
            ans[i] = Math.max(right[i], left[i+k-1]);
        }

        return ans;
    }
}
