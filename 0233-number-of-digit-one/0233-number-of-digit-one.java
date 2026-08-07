class Solution {
    public int countDigitOne(int n) {
        if (n <= 0) return 0;
        
        long count = 0;
        // Iterate through each digit place: 1, 10, 100, 1000...
        for (long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            
            // Count complete groups of 'i' size up to the current position
            count += (n / divider) * i;
            
            // Count remaining 1s in the partial trailing group
            long remainder = n % divider;
            count += Math.min(Math.max(remainder - i + 1, 0), i);
        }
        
        return (int) count;
    }
}
