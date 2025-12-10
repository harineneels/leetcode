import java.util.*;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int m = potions.length;
        int[] ans = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            long needed = (success + spells[i] - 1) / spells[i];  
            // minimum potion value required

            int idx = lowerBound(potions, needed);

            ans[i] = (idx == -1) ? 0 : (m - idx);
        }

        return ans;
    }

    // Standard lower bound (first index with value >= target)
    private int lowerBound(int[] arr, long target) {
        int left = 0, right = arr.length - 1;
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}
