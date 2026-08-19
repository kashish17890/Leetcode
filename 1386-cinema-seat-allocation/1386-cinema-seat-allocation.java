class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < reservedSeats.length; i++) {
            int row = reservedSeats[i][0];
            int seat = reservedSeats[i][1];

            if (!map.containsKey(row)) {
                map.put(row, new HashSet<>());
            }

            map.get(row).add(seat);
        }

        int ans = (n - map.size()) * 2;

        for (HashSet<Integer> seats : map.values()) {
            boolean left = true;
            boolean middle = true;
            boolean right = true;

            for (int i = 2; i <= 5; i++) {
                if (seats.contains(i)) {
                    left = false;
                    break;
                }
            }

            for (int i = 4; i <= 7; i++) {
                if (seats.contains(i)) {
                    middle = false;
                    break;
                }
            }

            for (int i = 6; i <= 9; i++) {
                if (seats.contains(i)) {
                    right = false;
                    break;
                }
            }

            if (left && right) {
                ans += 2;
            } else if (left || middle || right) {
                ans += 1;
            }
        }

        return ans;
    }
}