import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        // Step 1: Find the maximum matching prefix between s and target
        int matchedLen = 0;
        while (matchedLen < n && count[target.charAt(matchedLen) - 'a'] > 0) {
            count[target.charAt(matchedLen) - 'a']--;
            matchedLen++;
        }

        // Step 2: Backtrack from right to left to find the divergence point
        for (int k = matchedLen; k >= 0; k--) {
            if (k < n) {
                char targetChar = target.charAt(k);
                // Look for a strictly greater character available in our frequency map
                for (int c = (targetChar - 'a') + 1; c < 26; c++) {
                    if (count[c] > 0) {
                        count[c]--; // Use this greater character
                        
                        // Construct the final result
                        StringBuilder sb = new StringBuilder();
                        sb.append(target, 0, k);
                        sb.append((char) ('a' + c));
                        
                        // Append remaining characters in ascending order to keep it smallest
                        for (int i = 0; i < 26; i++) {
                            while (count[i] > 0) {
                                sb.append((char) ('a' + i));
                                count[i]--;
                            }
                        }
                        return sb.toString();
                    }
                }
            }
            // Backtrack: restore the character at k-1 to the available count pool
            if (k > 0) {
                count[target.charAt(k - 1) - 'a']++;
            }
        }

        return "";
    }
}
