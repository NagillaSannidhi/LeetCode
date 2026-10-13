class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] squares = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        long ans = 0;
        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {
            ans += squares[n - 1 - i];
            ans -= squares[i];
        }

        if (n % 2 != 0) {
            ans += squares[n / 2];
        }

        return ans;
        
    }
}