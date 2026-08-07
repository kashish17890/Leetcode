import java.util.*;

public class Solution {

    // Prime factor exponents (2,3,5,7) contributed by each single digit 0-9
    private static final int[][] DIGIT_FACTORS = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {
        int[] primeCount = new int[4]; // exponents of 2,3,5,7 required by t
        if (!getPrimeCount(t, primeCount)) return "-1";

        int[] factorCount = getFactorCount(primeCount); // minimal digit multiset (2-9) representing primeCount
        int need = sum(factorCount);

        // If t alone already needs more digits than num has, answer must be longer than num
        if (need > num.length()) {
            return construct(factorCount);
        }

        int[] primeCountPrefix = getPrimeCountFromString(num);
        int firstZeroIndex = num.indexOf('0');

        if (firstZeroIndex == -1) {
            firstZeroIndex = num.length();
            if (isSubset(primeCount, primeCountPrefix)) {
                return num; // num itself already works
            }
        }

        for (int i = num.length() - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            primeCountPrefix = subtract(primeCountPrefix, DIGIT_FACTORS[d]);
            int spaceAfterThisDigit = num.length() - 1 - i;

            // A '0' digit at or before position i means any prefix through i is invalid
            if (i > firstZeroIndex) continue;

            for (int biggerDigit = d + 1; biggerDigit < 10; biggerDigit++) {
                int[] remaining = subtract(subtract(primeCount, primeCountPrefix), DIGIT_FACTORS[biggerDigit]);
                int[] factorsAfterReplacement = getFactorCount(remaining);
                int sumAfter = sum(factorsAfterReplacement);

                if (sumAfter <= spaceAfterThisDigit) {
                    int fillOnes = spaceAfterThisDigit - sumAfter;
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append((char) ('0' + biggerDigit));
                    for (int k = 0; k < fillOnes; k++) sb.append('1');
                    sb.append(construct(factorsAfterReplacement));
                    return sb.toString();
                }
            }
        }

        // No same-length solution exists -> answer needs one extra digit
        int[] factorsAfterExtension = getFactorCount(primeCount);
        int ones = num.length() + 1 - sum(factorsAfterExtension);
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < ones; k++) sb.append('1');
        sb.append(construct(factorsAfterExtension));
        return sb.toString();
    }

    // Factor t into exponents of 2,3,5,7. Returns false if t has other prime factors.
    private boolean getPrimeCount(long t, int[] count) {
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                t /= primes[i];
                count[i]++;
            }
        }
        return t == 1;
    }

    private int[] getPrimeCountFromString(String num) {
        int[] count = new int[4];
        for (int i = 0; i < num.length(); i++) {
            int d = num.charAt(i) - '0';
            for (int j = 0; j < 4; j++) count[j] += DIGIT_FACTORS[d][j];
        }
        return count;
    }

    // Given required exponents of (2,3,5,7), find the minimal-length multiset of digits 2-9
    // whose product has AT LEAST these exponents. Returns counts indexed by digit (0..9, only 2-9 used).
    private int[] getFactorCount(int[] count) {
        int c2 = count[0], c3 = count[1], c5 = count[2], c7 = count[3];

        int count8 = c2 / 3;
        int remaining2 = c2 % 3;
        int count9 = c3 / 2;
        int count3 = c3 % 2;
        int count4 = remaining2 / 2;
        int count2 = remaining2 % 2;
        int count6 = 0;

        if (count2 == 1 && count3 == 1) {
            count2 = 0;
            count3 = 0;
            count6 = 1;
        }
        if (count3 == 1 && count4 == 1) {
            count2 = 1;
            count6 = 1;
            count3 = 0;
            count4 = 0;
        }

        int[] res = new int[10];
        res[2] = count2;
        res[3] = count3;
        res[4] = count4;
        res[5] = c5;
        res[6] = count6;
        res[7] = c7;
        res[8] = count8;
        res[9] = count9;
        return res;
    }

    private String construct(int[] factors) {
        StringBuilder sb = new StringBuilder();
        for (int digit = 2; digit < 10; digit++) {
            for (int k = 0; k < factors[digit]; k++) {
                sb.append((char) ('0' + digit));
            }
        }
        return sb.toString();
    }

    private boolean isSubset(int[] needed, int[] have) {
        for (int i = 0; i < needed.length; i++) {
            if (have[i] < needed[i]) return false;
        }
        return true;
    }

    private int[] subtract(int[] a, int[] b) {
        int[] res = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = Math.max(0, a[i] - b[i]);
        }
        return res;
    }

    private int sum(int[] factors) {
        int s = 0;
        for (int v : factors) s += v;
        return s;
    }

    // ---- quick manual test ----
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.smallestNumber("1234", 256));   // expect 1488
        System.out.println(sol.smallestNumber("12355", 50));   // expect 12355
        System.out.println(sol.smallestNumber("11111", 26));   // expect -1
    }
}