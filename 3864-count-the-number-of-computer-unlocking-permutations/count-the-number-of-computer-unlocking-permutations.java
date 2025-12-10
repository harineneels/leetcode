class Solution {
    static final long MOD = 1_000_000_007;

    public int countPermutations(int[] complexity) {
        int n = complexity.length;

        // Check if complexity[0] is the strict global minimum
        for (int i = 1; i < n; i++) {
            if (complexity[0] >= complexity[i]) {
                return 0;
            }
        }

        // Compute (n - 1)! % MOD
        long result = 1;
        for (int i = 1; i < n; i++) {
            result = (result * i) % MOD;
        }

        return (int) result;
    }
}

