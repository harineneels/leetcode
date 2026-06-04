class Solution {
    public int totalWaviness(int num1, int num2) {
        int total = 0;

        for (int n = num1; n <= num2; n++) {
            String s = String.valueOf(n);

            for (int i = 1; i < s.length() - 1; i++) {
                int left = s.charAt(i - 1);
                int mid = s.charAt(i);
                int right = s.charAt(i + 1);

                if ((mid > left && mid > right) ||
                    (mid < left && mid < right)) {
                    total++;
                }
            }
        }

        return total;
    }
}