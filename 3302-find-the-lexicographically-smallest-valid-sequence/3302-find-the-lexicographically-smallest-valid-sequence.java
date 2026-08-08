class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // right[i] stores the max index in word1 that can match word2[i...] suffix
        int[] right = new int[m];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                right[j] = i;
                j--;
            }
        }
        
        // If j >= 0, the full suffix match isn't possible even without a mismatch
        if (j >= 0) {
            // Wait, we can use 1 mismatch overall. Let's trace carefully:
            // right array should map each word2 index to the earliest/latest match in word1.
            // Standard approach: right[i] = smallest index in word1 >= some pointer matching word2[i..]
        }
        
        // Let's rewrite a robust standard greedy approach:
        int[] matchRight = new int[m];
        int ptr = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(i)) {
                ptr--;
            }
            if (ptr < 0) matchRight[i] = -1;
            else matchRight[i] = ptr--;
        }
        
        int[] res = new int[m];
        int w1Idx = 0;
        boolean usedChange = false;
        int w2Idx = 0;
        
        while (w2Idx < m && w1Idx < n) {
            if (word1.charAt(w1Idx) == word2.charAt(w2Idx)) {
                res[w2Idx] = w1Idx;
                w1Idx++;
                w2Idx++;
            } else {
                // Try to see if we can use our one change here
                if (!usedChange) {
                    // Check if remaining word2[w2Idx + 1 ...] can be matched by the rest of word1
                    boolean canMatchRest = (w2Idx + 1 >= m) || (matchRight[w2Idx + 1] != -1 && matchRight[w2Idx + 1] > w1Idx);
                    if (canMatchRest) {
                        res[w2Idx] = w1Idx;
                        usedChange = true;
                        w1Idx++;
                        w2Idx++;
                    } else {
                        w1Idx++;
                    }
                } else {
                    w1Idx++;
                }
            }
        }
        
        return w2Idx == m ? res : new int[0];
    }
}



