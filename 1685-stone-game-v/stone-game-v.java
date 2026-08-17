class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        int[][] dp = new int[n][n]; // dp[i][i] = 0 by default

        for (int length = 2; length <= n; length++) {
            for (int i = 0; i + length - 1 < n; i++) {
                int j = i + length - 1;
                int best = 0;
                for (int k = i; k < j; k++) {
                    int left = prefix[k + 1] - prefix[i];
                    int right = prefix[j + 1] - prefix[k + 1];
                    int candidate;
                    if (left < right) {
                        candidate = left + dp[i][k];
                    } else if (left > right) {
                        candidate = right + dp[k + 1][j];
                    } else {
                        candidate = left + Math.max(dp[i][k], dp[k + 1][j]);
                    }
                    best = Math.max(best, candidate);
                }
                dp[i][j] = best;
            }
        }

        return dp[0][n - 1];
        
    }
}