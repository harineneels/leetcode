class Solution {
    public int numSub(String s) {
        long mod = 1000000007;
        long count = 0;   // consecutive '1's
        long result = 0;  // total substrings
        
        for (char c : s.toCharArray()) {
            if (c == '1') {
                count++;
            } else {
                result += count * (count + 1) / 2;
                result %= mod;
                count = 0;
            }
        }
        
        // Add last sequence of 1s
        result += count * (count + 1) / 2;
        result %= mod;
        
        return (int) result;
    }
}
