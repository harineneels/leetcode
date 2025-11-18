class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int n = bits.length;

        while (i < n - 1) {   // stop before the last bit
            if (bits[i] == 1) {
                i += 2;       // 2-bit character (10 or 11)
            } else {
                i += 1;       // 1-bit character (0)
            }
        }

        // If we land exactly on the last bit → it's 0 → one-bit character
        return i == n - 1;
    }
}
