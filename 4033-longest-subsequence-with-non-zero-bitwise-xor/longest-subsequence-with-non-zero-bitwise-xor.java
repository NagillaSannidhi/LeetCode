class Solution {
    public int longestSubsequence(int[] nums) {
        int total = 0;
        boolean hasNonZero = false;
        
        for (int x : nums) {
            total ^= x;
            if (x != 0) {
                hasNonZero = true;
            }
        }
        
        int n = nums.length;
        if (total != 0) {
            return n;
        }
        if (hasNonZero) {
            return n - 1;
        }
        return 0;
        
    }
}