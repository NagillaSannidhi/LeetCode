class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n]; 
        Arrays.fill(dp, Integer.MAX_VALUE);

        int left = 0, sum = 0;
        int best = Integer.MAX_VALUE; 

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            
            dp[right] = (right > 0) ? dp[right - 1] : Integer.MAX_VALUE;

            if (sum == target) {
                int currentLen = right - left + 1;

                
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    best = Math.min(best, dp[left - 1] + currentLen);
                }

                
                dp[right] = Math.min(dp[right], currentLen);
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
        
    }
}