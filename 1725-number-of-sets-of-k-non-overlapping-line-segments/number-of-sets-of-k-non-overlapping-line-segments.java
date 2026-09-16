class Solution {
    public int numberOfSets(int n, int k) {
        final int MOD = 1_000_000_007;
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }
        

        
        long[] runningSum = new long[k + 1];

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                
                runningSum[j] = (runningSum[j] + dp[i - 1][j - 1]) % MOD;

                dp[i][j] = (dp[i - 1][j] + runningSum[j]) % MOD;
            }
        }

        return (int) dp[n][k];
        
    }
}