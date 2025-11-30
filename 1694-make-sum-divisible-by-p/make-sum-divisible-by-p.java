import java.util.HashMap;

class Solution {
    public int minSubarray(int[] nums, int p) {
        long total = 0;
        for (int num : nums) total += num;
        
        int need = (int)(total % p);
        if (need == 0) return 0; // already divisible
        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // prefix sum 0 at index -1
        
        long prefix = 0;
        int ans = nums.length;
        
        for (int i = 0; i < nums.length; i++) {
            prefix = (prefix + nums[i]) % p;
            
            int target = (int)((prefix - need + p) % p);
            
            if (map.containsKey(target)) {
                ans = Math.min(ans, i - map.get(target));
            }
            
            map.put((int)prefix, i);
        }
        
        return ans == nums.length ? -1 : ans;
    }
}
