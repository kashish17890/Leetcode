class Solution {
    public boolean checkPerfectNumber(int num) {
        // Edge case: 1 is not a perfect number
        if (num <= 1) {
            return false;
        }
        
        int sum = 1; // 1 is always a proper divisor for any num > 1
        
        // Loop up to the square root of num to find pairs of divisors
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i; // Add the smaller divisor
                
                // Add the matching larger divisor if it's not the same square root value
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }
        
        // If the sum of divisors matches the original number, it's perfect
        return sum == num;
    }
}
