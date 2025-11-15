class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        long answer = 0L;

        // positions of zeros with sentinels
        java.util.ArrayList<Integer> zpos = new java.util.ArrayList<>();
        zpos.add(-1); // left sentinel
        for (int i = 0; i < n; i++) if (s.charAt(i) == '0') zpos.add(i);
        zpos.add(n); // right sentinel

        int zerosCount = zpos.size() - 2; // excluding sentinels

        // case z = 0: substrings with zero zeros -> all-one segments
        long run = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') run++;
            else {
                answer += run * (run + 1) / 2;
                run = 0;
            }
        }
        answer += run * (run + 1) / 2;

        // For z >= 1 up to 200 (or up to zerosCount)
        int ZMAX = Math.min(200, zerosCount);
        for (int z = 1; z <= ZMAX; z++) {
            // slide over windows of z zeros in zpos
            // zpos indexes: 0 = -1, 1..zerosCount actual zero positions, zerosCount+1 = n
            for (int l = 1; l + z - 1 <= zerosCount; l++) {
                int r = l + z - 1;

                int leftOptions = zpos.get(l) - zpos.get(l - 1);           // choices for left endpoint
                int rightOptions = zpos.get(r + 1) - zpos.get(r);         // choices for right endpoint

                int baseLen = zpos.get(r) - zpos.get(l) + 1;              // minimal length covering these zeros
                int Lmin = z * z + z;                                     // required length
                int req = Lmin - baseLen;                                 // need a + b >= req

                long totalPairs = 1L * leftOptions * rightOptions;
                if (req <= 0) {
                    answer += totalPairs; // every combination works
                } else {
                    int t = req - 1; // we need to subtract pairs with a+b <= t
                    long bad = countPairsWithSumAtMost(leftOptions, rightOptions, t);
                    answer += (totalPairs - bad);
                }
            }
        }

        // The problem signature was int; cast to int if you know constraints ensure it fits.
        // If large n could produce >2^31-1 substrings, change return type to long.
        return (int) answer;
    }

    // Count number of pairs (a,b) with 0 <= a < A, 0 <= b < B, and a + b <= t
    private long countPairsWithSumAtMost(int A, int B, int t) {
        if (t < 0) return 0L;
        int aMax = A - 1;
        int bMax = B - 1;
        if (A == 0 || B == 0) return 0L;

        // ensure aMax <= bMax by swapping if needed
        if (aMax > bMax) {
            int tmp = aMax; aMax = bMax; bMax = tmp;
        }

        // now aMax <= bMax
        if (t <= aMax) {
            // triangular numbers
            long x = t + 1L;
            return x * (x + 1) / 2;
        } else if (t <= bMax) {
            long m = aMax;
            long first = (m + 1L) * (m + 2L) / 2; // sum up to aMax
            long extra = (t - m) * (m + 1L);
            return first + extra;
        } else {
            int s = aMax + bMax;
            if (t >= s) {
                // all pairs
                return 1L * (aMax + 1) * (bMax + 1);
            } else {
                // symmetric decreasing tail
                long rem = s - t; // rem >= 1
                // number of excluded pairs = rem*(rem+1)/2
                long total = 1L * (aMax + 1) * (bMax + 1);
                long excluded = rem * (rem + 1) / 2;
                return total - excluded;
            }
        }
    }
}
