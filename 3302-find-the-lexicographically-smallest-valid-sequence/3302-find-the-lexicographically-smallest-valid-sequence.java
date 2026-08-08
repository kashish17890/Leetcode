class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        int[] right = new int[m];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                right[j] = i;
                j--;
            }
        }
        
        if (j >= 0) {
        }
        
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
                if (!usedChange) {
                    
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



