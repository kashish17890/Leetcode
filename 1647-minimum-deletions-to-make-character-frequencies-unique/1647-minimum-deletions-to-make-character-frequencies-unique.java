import java.util.HashSet;

class Solution {
    public int minDeletions(String s) {
        // Step 1: Count the frequency of each character
        int[] frequency = new int[26];
        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a']++;
        }
        
        // Step 2: Keep track of used frequencies and deletions
        HashSet<Integer> usedFrequencies = new HashSet<>();
        int totalDeletions = 0;
        
        // Step 3: Iterate through all character frequencies
        for (int count : frequency) {
            // While this frequency is already taken, decrement it
            while (count > 0 && usedFrequencies.contains(count)) {
                count--;
                totalDeletions++;
            }
            // If the remaining frequency is valid (>0), mark it as used
            if (count > 0) {
                usedFrequencies.add(count);
            }
        }
        
        return totalDeletions;
    }
}
