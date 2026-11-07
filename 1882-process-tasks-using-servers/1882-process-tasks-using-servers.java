import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];
        
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        PriorityQueue<int[]> used = new PriorityQueue<>((a, b) -> a[2] != b[2] ? Integer.compare(a[2], b[2]) : (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
        
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i, 0});
        }
        
        int time = 0;
        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);
            
            if (free.isEmpty() && used.peek()[2] > time) {
                time = used.peek()[2];
            }
            
            while (!used.isEmpty() && used.peek()[2] <= time) {
                free.offer(used.poll());
            }
            
            int[] curr = free.poll();
            ans[i] = curr[1];
            curr[2] = time + tasks[i];
            used.offer(curr);
        }
        
        return ans;
    }
}
