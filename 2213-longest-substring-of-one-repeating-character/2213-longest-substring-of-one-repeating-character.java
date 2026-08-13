// class Solution {
//     public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
//         StringBuilder sb=new StringBuilder(s);
//         int len=0;
//         int nums[] = new int[queryIndices.length];
        
//         for(int i=0;i<queryIndices.length;i++){
//             sb.setCharAt(queryIndices[i],queryCharacters.charAt(i));

//             while(sb.charAt(i)==sb.charAt(i+1)){
//                 len++;
//             }
//             nums[i]=len;
//             len=0;
//         }
//         return nums;
//     }
// }

class Solution {
    static class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        char prefChar;
        char suffChar;
        int totalLen;

        Node(char c, int len) {
            this.maxLen = len;
            this.prefLen = len;
            this.suffLen = len;
            this.prefChar = c;
            this.suffChar = c;
            this.totalLen = len;
        }

        Node() {}
    }

    private Node[] tree;

    private void merge(Node res, Node left, Node right) {
        res.prefChar = left.prefChar;
        res.suffChar = right.suffChar;
        res.totalLen = left.totalLen + right.totalLen;
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        res.prefLen = left.prefLen;
        res.suffLen = right.suffLen;

        if (left.suffChar == right.prefChar) {
            res.maxLen = Math.max(res.maxLen, left.suffLen + right.prefLen);
            if (left.prefLen == left.totalLen) {
                res.prefLen = left.totalLen + right.prefLen;
            }
            if (right.suffLen == right.totalLen) {
                res.suffLen = right.totalLen + left.suffLen;
            }
        }
    }

    private void build(String s, int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(s.charAt(start), 1);
            return;
        }
        int mid = start + (end - start) / 2;
        build(s, 2 * node, start, mid);
        build(s, 2 * node + 1, mid + 1, end);
        tree[node] = new Node();
        merge(tree[node], tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char val) {
        if (start == end) {
            tree[node] = new Node(val, 1);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        merge(tree[node], tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        tree = new Node[4 * n];
        build(s, 1, 0, n - 1);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            result[i] = tree[1].maxLen;
        }
        return result;
    }
}
