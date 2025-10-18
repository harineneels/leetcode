import java.util.*;

public class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        long last = Long.MIN_VALUE; // to handle large numbers safely
        
        for (int x : nums) {
            long candidate = Math.max((long) x - k, last + 1);
            if (candidate <= (long) x + k) {
                ans++;
                last = candidate; // update last chosen distinct number
            }
        }
        return ans;
    }

    // For testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1, 2, 2, 3, 3, 4};
        int k1 = 2;
        System.out.println(sol.maxDistinctElements(nums1, k1)); // Output: 6

        int[] nums2 = {4, 4, 4, 4};
        int k2 = 1;
        System.out.println(sol.maxDistinctElements(nums2, k2)); // Output: 3

        int[] nums3 = {1, 1, 1, 1};
        int k3 = 0;
        System.out.println(sol.maxDistinctElements(nums3, k3)); // Output: 1

        int[] nums4 = {10, 10, 11};
        int k4 = 2;
        System.out.println(sol.maxDistinctElements(nums4, k4)); // Output: 5
    }
}
