class Solution {
    public boolean checkDivisibility(int n) {
        int digitSum = 0;
        int digitProduct = 1;
        int temp = n;
        
        // Extract and process each digit
        while (temp > 0) {
            int digit = temp % 10;
            digitSum += digit;
            digitProduct *= digit;
            temp /= 10;
        }
        
        // Check if n is divisible by (digitSum + digitProduct)
        return n % (digitSum + digitProduct) == 0;
    }
}
