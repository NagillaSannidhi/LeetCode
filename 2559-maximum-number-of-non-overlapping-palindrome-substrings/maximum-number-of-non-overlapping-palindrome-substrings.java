class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        if (n < k) return 0;

        boolean[][] isPal = new boolean[n][n];
        for (int i = 0; i < n; i++) isPal[i][i] = true;
        for (int i = 0; i < n - 1; i++) {
            isPal[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                isPal[i][j] = (s.charAt(i) == s.charAt(j)) && isPal[i + 1][j - 1];
            }
        }
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1]; 

            
            if (i >= k && isPal[i - k][i - 1]) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }

            
            if (i >= k + 1 && isPal[i - k - 1][i - 1]) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }

        return dp[n];
        
    }
}