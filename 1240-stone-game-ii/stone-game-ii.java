class Solution {
    int n;
    int[] suffixSum;
    Integer[][] memo;
    
    public int stoneGameII(int[] piles) {
        n = piles.length;
        suffixSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        memo = new Integer[n][n + 1];
        return dp(0, 1);
    }

    private int dp(int i, int M) {
        if (i >= n) return 0;
        if (i + 2 * M >= n) {
            return suffixSum[i]; // take everything left
        }
        if (memo[i][M] != null) return memo[i][M];

        int best = 0;
        for (int X = 1; X <= 2 * M; X++) {
            if (i + X > n) break;
            int newM = Math.max(M, X);
            best = Math.max(best, suffixSum[i] - dp(i + X, newM));
        }

        memo[i][M] = best;
        return best;
        
    }
}