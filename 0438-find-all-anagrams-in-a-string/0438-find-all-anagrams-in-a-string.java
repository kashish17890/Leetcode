import java.util.*;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Fill frequency array for pattern p
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int pLen = p.length();
        int sLen = s.length();

        for (int i = 0; i < sLen; i++) {
            // Add current character to the sliding window
            sCount[s.charAt(i) - 'a']++;

            // Remove the character that is left behind the window
            if (i >= pLen) {
                sCount[s.charAt(i - pLen) - 'a']--;
            }

            // Compare frequency arrays
            if (i >= pLen - 1 && Arrays.equals(pCount, sCount)) {
                result.add(i - pLen + 1);
            }
        }

        return result;
    }
}
