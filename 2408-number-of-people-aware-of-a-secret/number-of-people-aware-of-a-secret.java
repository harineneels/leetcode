class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int MOD = 1_000_000_007;
        long[] dp = new long[n + 1];
        dp[1] = 1; // day 1: 1 person knows

        long share = 0;  // people who can share
        for (int day = 2; day <= n; day++) {
            // people who start sharing today
            if (day - delay >= 1) {
                share = (share + dp[day - delay]) % MOD;
            }
            // people who forget today (stop sharing)
            if (day - forget >= 1) {
                share = (share - dp[day - forget] + MOD) % MOD;
            }
            dp[day] = share; // new people learning today
        }

        long ans = 0;
        // sum up people who still remember on day n
        for (int day = n - forget + 1; day <= n; day++) {
            if (day >= 1) {
                ans = (ans + dp[day]) % MOD;
            }
        }
        return (int) ans;
    }
}
