import java.util.Arrays;

public class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        
        // 1. Count character frequencies
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        
        // 2. Validate palindrome feasibility (At most 1 odd frequency allowed)
        int oddCount = 0;
        int oddCharIndex = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                oddCount++;
                oddCharIndex = i;
            }
        }
        if (oddCount > 1) {
            return ""; // Not a valid palindromic permutation
        }
        
        // 3. Extract the pools for the left half and middle character
        int[] halfCnt = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }
        
        char midChar = oddCharIndex != -1 ? (char) ('a' + oddCharIndex) : '\0';
        int halfLen = n / 2;
        char[] leftHalf = new char[halfLen];
        
        // 4. Try to construct the matching prefix with the target
        if (canMatchPrefix(0, leftHalf, halfCnt, target, midChar, n)) {
            return buildFullPalindrome(leftHalf, midChar, n);
        }
        
        return "";
    }
    
    // Backtracking to greedily build the first half matching or beating the target
    private boolean canMatchPrefix(int idx, char[] leftHalf, int[] halfCnt, String target, char midChar, int n) {
        if (idx == leftHalf.length) {
            // Reached the end of the left half. Check if the completed string is strictly greater than target.
            String candidate = buildFullPalindrome(leftHalf, midChar, n);
            return candidate.compareTo(target) > 0;
        }
        
        char targetChar = target.charAt(idx);
        int targetIdx = targetChar - 'a';
        
        // Strategy A: Match the target character directly to stay identical as long as possible
        if (halfCnt[targetIdx] > 0) {
            leftHalf[idx] = targetChar;
            halfCnt[targetIdx]--;
            if (canMatchPrefix(idx + 1, leftHalf, halfCnt, target, midChar, n)) {
                return true;
            }
            // Backtrack
            halfCnt[targetIdx]++;
        }
        
        // Strategy B: Pivot at this index by picking the smallest available character STRICTLY GREATER than targetChar
        for (int i = targetIdx + 1; i < 26; i++) {
            if (halfCnt[i] > 0) {
                leftHalf[idx] = (char) ('a' + i);
                halfCnt[i]--;
                // Fill the remaining indices with the absolute lexicographically smallest available characters
                fillSmallestRemaining(idx + 1, leftHalf, halfCnt);
                return true; 
            }
        }
        
        return false;
    }
    
    // Helper to fill the remaining slots greedily from 'a' to 'z'
    private void fillSmallestRemaining(int startIdx, char[] leftHalf, int[] halfCnt) {
        int charPtr = 0;
        for (int i = startIdx; i < leftHalf.length; i++) {
            while (charPtr < 26 && halfCnt[charPtr] == 0) {
                charPtr++;
            }
            leftHalf[i] = (char) ('a' + charPtr);
            halfCnt[charPtr]--;
        }
    }
    
    // Helper to mirror the left half and form the complete palindrome
    private String buildFullPalindrome(char[] leftHalf, char midChar, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(leftHalf);
        if (midChar != '\0') {
            sb.append(midChar);
        }
        for (int i = leftHalf.length - 1; i >= 0; i--) {
            sb.append(leftHalf[i]);
        }
        return sb.toString();
    }
}
