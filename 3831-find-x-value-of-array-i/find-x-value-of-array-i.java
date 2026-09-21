class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];  

        for (int num : nums) {
            long[] newDp = new long[k];
            int numMod = num % k;
            newDp[numMod] += 1;

            for (int r = 0; r < k; r++) {
                if (dp[r] == 0) continue;
                int newRemainder = (int) ((long) r * numMod % k);
                newDp[newRemainder] += dp[r];
            }
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
        
    }
}