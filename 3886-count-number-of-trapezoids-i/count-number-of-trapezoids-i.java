import java.util.*;

public class Solution {
    public static int countTrapezoids(int[][] points) {
        final long MOD = 1_000_000_007L;

        // Count points grouped by y-level
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] p : points) {
            int y = p[1];
            map.put(y, map.getOrDefault(y, 0) + 1);
        }

        // Compute H(y) = C(k,2) for each y-level with k >= 2
        ArrayList<Long> H = new ArrayList<>();
        for (int k : map.values()) {
            if (k >= 2) {
                long val = (long) k * (k - 1) / 2;
                H.add(val % MOD);
            }
        }

        if (H.size() < 2) return 0;

        long sumH = 0, sumSq = 0;
        for (long h : H) {
            sumH = (sumH + h) % MOD;
            sumSq = (sumSq + (h * h) % MOD) % MOD;
        }

        long ans = ((sumH * sumH) % MOD - sumSq + MOD) % MOD;
        ans = (ans * 500000004L) % MOD; // multiply by modular inverse of 2
        return (int) ans;
    }
}
