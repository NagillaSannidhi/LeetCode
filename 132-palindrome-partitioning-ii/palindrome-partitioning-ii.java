class Solution {
    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        
        boolean[][] isPal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                }
            }
        }

        
        int[] cuts = new int[n];

        for (int i = 0; i < n; i++) {
            if (isPal[0][i]) {
                cuts[i] = 0; 
                continue;
            }

            cuts[i] = i; 

            for (int j = 1; j <= i; j++) {
                if (isPal[j][i]) {
                    cuts[i] = Math.min(cuts[i], cuts[j - 1] + 1);
                }
            }
        }

        return cuts[n - 1];
        
    }
}