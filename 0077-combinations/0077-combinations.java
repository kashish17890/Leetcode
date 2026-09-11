import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), n, k, 1);
        return result;
    }
    private void backtrack(List<List<Integer>> result, List<Integer> current, int n, int k, int start) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        int needed = k - current.size();
        if (start + needed - 1 > n) {
            return;
        }
        for (int i = start; i <= n; i++) {
            current.add(i);
            backtrack(result, current, n, k, i + 1);
            current.remove(current.size() - 1);
        }
    }
}
