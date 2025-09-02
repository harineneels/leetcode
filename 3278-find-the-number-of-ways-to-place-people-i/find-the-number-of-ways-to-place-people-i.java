import java.util.Arrays;

class Solution {
    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (a, b) ->
            a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]
        );

        int ans = 0;
        int n = points.length;

        for (int i = 0; i < n; i++) {
            int yA = points[i][1];
            int maxY = Integer.MIN_VALUE;  // track highest y seen so far in inner loop

            for (int j = i + 1; j < n; j++) {
                int yB = points[j][1];
                // If B is horizontally to the right and below or at A,
                // and no one is in between (tracked by maxY)
                if (yB <= yA && yB > maxY) {
                    ans++;
                    maxY = yB;
                }
            }
        }

        return ans;
    }
}
