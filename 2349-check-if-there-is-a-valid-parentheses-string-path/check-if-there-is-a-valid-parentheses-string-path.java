class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        if ((m + n - 1) % 2 != 0) return false;

        int maxBalance = m + n;
        boolean[][][] dp = new boolean[m][n][maxBalance + 1];

        int start = grid[0][0] == '(' ? 1 : -1;
        if (start < 0) return false; 
        dp[0][0][start] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue; 

                int delta = grid[i][j] == '(' ? 1 : -1;

                for (int bal = 0; bal <= maxBalance; bal++) {
                    boolean fromUp = (i > 0) && dp[i - 1][j][bal];
                    boolean fromLeft = (j > 0) && dp[i][j - 1][bal];

                    if (fromUp || fromLeft) {
                        int newBal = bal + delta;
                        if (newBal >= 0 && newBal <= maxBalance) {
                            dp[i][j][newBal] = true;
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
        
    }
}