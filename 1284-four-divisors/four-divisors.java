class Solution {

    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        for (int n : nums) {

            // Case 2: p^3
            int p = (int) Math.round(Math.cbrt(n));
            if (p * p * p == n && isPrime(p)) {
                totalSum += 1 + p + (p * p) + n;
                continue;
            }

            // Case 1: p * q
            for (int d = 2; d * d <= n; d++) {
                if (n % d == 0) {
                    int other = n / d;
                    if (d != other && isPrime(d) && isPrime(other)) {
                        totalSum += 1 + d + other + n;
                    }
                    break; // only one divisor pair allowed
                }
            }
        }

        return totalSum;
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
