import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLengthSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        // Map to track the frequency of each character in the current window
        Map<Character, Integer> counts = new HashMap<>();
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // Add the right character to the map and update its count
            counts.put(rightChar, counts.getOrDefault(rightChar, 0) + 1);
            
            // Shrink the window if the current character's count exceeds 2
            while (counts.get(rightChar) > 2) {
                char leftChar = s.charAt(left);
                counts.put(leftChar, counts.get(leftChar) - 1);
                
                // Optional: Clean up the map if count drops to 0
                if (counts.get(leftChar) == 0) {
                    counts.remove(leftChar);
                }
                left++;
            }
            
            // Update the maximum length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
