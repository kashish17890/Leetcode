import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count the frequency of each character
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Step 2: Sort frequencies in ascending order
        Arrays.sort(freq);
        
        int totalPushes = 0;
        int distinctKeysUsed = 0;
        
        // Step 3: Iterate backwards from the most frequent characters
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) {
                break; // No more characters left to map
            }
            
            // Calculate push cost based on how many keys have already been assigned
            int pushCost = (distinctKeysUsed / 8) + 1;
            totalPushes += freq[i] * pushCost;
            
            distinctKeysUsed++;
        }
        
        return totalPushes;
    }
}
