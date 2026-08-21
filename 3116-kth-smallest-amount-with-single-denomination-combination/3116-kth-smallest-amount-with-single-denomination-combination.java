import java.util.Arrays;

class Solution {
    private int[] coins;
    private int k;

    public long findKthSmallest(int[] coins, int k) {
        this.coins = coins;
        this.k = k;
        
        // Find the minimum coin denomination to set a tighter upper bound
        long minCoin = Integer.MAX_VALUE;
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        
        // Binary search bounds
        long left = 1;
        long right = minCoin * k; 
        long answer = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            if (countMultiples(mid) >= k) {
                answer = mid;       // Try to find a smaller valid amount
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    // Counts the total unique multiples <= maxVal using Inclusion-Exclusion
    private long countMultiples(long maxVal) {
        long count = 0;
        int n = coins.length;
        int totalSubsets = 1 << n; // 2^n total combinations

        // Start from 1 to skip the empty subset
        for (int i = 1; i < totalSubsets; i++) {
            long currentLcm = 1;
            int elementCount = 0;
            boolean overflow = false;

            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    elementCount++;
                    currentLcm = lcm(currentLcm, coins[j]);
                    
                    // Optimization: If LCM exceeds maxVal, its count contribution will be 0
                    if (currentLcm > maxVal) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            // Apply inclusion-exclusion principle
            if (elementCount % 2 == 1) {
                count += maxVal / currentLcm;
            } else {
                count -= maxVal / currentLcm;
            }
        }
        return count;
    }

    // Helper method to compute GCD
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Helper method to compute LCM
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
