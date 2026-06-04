class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        
        // Create a DP table initialized to false
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Base case: Empty string matches empty pattern
        dp[0][0] = true;
        
        // Base case: Deals with patterns like a*, a*b*, or .* that can match an empty string
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Case 1: The current characters match, or pattern has '.'
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // Case 2: The current pattern character is '*'
                else if (p.charAt(j - 1) == '*') {
                    // Subcase A: Count '*' as zero occurrences of the preceding element
                    dp[i][j] = dp[i][j - 2];
                    
                    // Subcase B: Count '*' as one or more occurrences
                    // This is only possible if the preceding pattern character matches the current string character
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}
