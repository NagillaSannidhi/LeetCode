class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int jumps = 0, curEnd = 0, farthest = 0;

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = farthest;
                if (curEnd >= n - 1) break;
            }
        }
        return jumps;
        
    }
}