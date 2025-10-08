import java.util.Arrays;

public class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            long spell = spells[i];
            
            // Minimum potion strength needed for success
            long required = (success + spell - 1) / spell; // Ceiling division

            // Binary search to find the first potion >= required
            int index = lowerBound(potions, required);

            // Count of potions that form successful pairs
            result[i] = m - index;
        }
        return result;
    }

    // Custom lowerBound function (similar to Python's bisect_left)
    private int lowerBound(int[] arr, long target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // Example test
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] spells1 = {5, 1, 3};
        int[] potions1 = {1, 2, 3, 4, 5};
        int success1 = 7;
        System.out.println(Arrays.toString(sol.successfulPairs(spells1, potions1, success1)));
        // Output: [4, 0, 3]

        int[] spells2 = {3, 1, 2};
        int[] potions2 = {8, 5, 8};
        int success2 = 16;
        System.out.println(Arrays.toString(sol.successfulPairs(spells2, potions2, success2)));
        // Output: [2, 0, 2]
    }
}
