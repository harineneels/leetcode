class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for (int b : batteries) sum += b;

        long left = 1, right = sum / n, ans = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (canRun(mid, n, batteries)) {
                ans = mid;
                left = mid + 1; // try for more minutes
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean canRun(long t, int n, int[] batteries) {
        long total = 0;
        for (int b : batteries) {
            total += Math.min(b, t);
        }
        return total >= (long) n * t;
    }
}
