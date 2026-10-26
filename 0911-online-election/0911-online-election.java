import java.util.*;
class TopVotedCandidate {
    int[] times;
    int[] leaders;
    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        int n = persons.length;
        leaders = new int[n];

        Map<Integer, Integer> voteCount = new HashMap<>();
        int currentLeader = -1;
        int maxVotes = 0;
        for (int i = 0; i < n; i++) {
            int person = persons[i];
            int count = voteCount.getOrDefault(person, 0) + 1;
            voteCount.put(person, count);
            if (count >= maxVotes) {
                maxVotes = count;
                currentLeader = person;
            }
            leaders[i] = currentLeader;
        }
    }

    public int q(int t) {
        int left = 0;
        int right = times.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (times[mid] <= t) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return leaders[right];
    }
}
