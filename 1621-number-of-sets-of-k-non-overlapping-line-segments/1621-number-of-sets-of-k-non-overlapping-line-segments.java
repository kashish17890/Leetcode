class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        long num = 1;
        long den = 1;
        
        int totalPoints = n + k - 1;
        int choose = 2 * k;
        
        // Optimize combinations calculation: C(N, K) == C(N, N-K)
        if (choose > totalPoints - choose) {
            choose = totalPoints - choose;
        }
        
        for (int i = 1; i <= choose; i++) {
            num = (num * (totalPoints - i + 1)) % MOD;
            den = (den * i) % MOD;
        }
        
        // Since we need to divide by den modulo MOD, we find its modular inverse
        return (int) ((num * modularInverse(den, MOD)) % MOD);
    }
    
    // Fermat's Little Theorem for Modular Inverse
    private long modularInverse(long n, long mod) {
        return power(n, mod - 2, mod);
    }
    
    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}
