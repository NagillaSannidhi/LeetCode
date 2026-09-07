class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        int n = s.length();

        long dp = 1; 
        long[] last = new long[26]; 
        Arrays.fill(last, -1); 

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            long newDp = (2 * dp) % MOD;

            if (last[c] != -1) {
                newDp = (newDp - last[c] + MOD) % MOD;
            }

            last[c] = dp;   
            dp = newDp;
        }
        return (int) ((dp - 1 + MOD) % MOD);
        
    }
}