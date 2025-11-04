import java.util.*;

public class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            Map<Integer, Integer> freq = new HashMap<>();

            // Count frequency in the current subarray
            for (int j = i; j < i + k; j++) {
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            }

            // Sort elements by frequency desc, then by value desc
            List<int[]> list = new ArrayList<>();
            for (int num : freq.keySet()) {
                list.add(new int[]{num, freq.get(num)});
            }

            list.sort((a, b) -> {
                if (b[1] != a[1]) return b[1] - a[1]; // higher freq first
                return b[0] - a[0]; // higher value first if same freq
            });

            // Keep top x frequent elements
            Set<Integer> keep = new HashSet<>();
            for (int j = 0; j < Math.min(x, list.size()); j++) {
                keep.add(list.get(j)[0]);
            }

            // Sum only those kept elements
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                if (keep.contains(nums[j])) sum += nums[j];
            }

            ans[i] = sum;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1, 1, 2, 2, 3, 4, 2, 3};
        int k1 = 6, x1 = 2;
        System.out.println(Arrays.toString(sol.findXSum(nums1, k1, x1))); 
        // Output: [6, 10, 12]

        int[] nums2 = {3, 8, 7, 8, 7, 5};
        int k2 = 2, x2 = 2;
        System.out.println(Arrays.toString(sol.findXSum(nums2, k2, x2))); 
        // Output: [11, 15, 15, 15, 12]
    }
}
