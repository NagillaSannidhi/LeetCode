class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> windowCount = new HashMap<>();

        for (int start = 0; start + k <= n; start++) {
            Set<Integer> distinct = new HashSet<>();
            for (int i = start; i < start + k; i++) {
                distinct.add(nums[i]);
            }
            for (int val : distinct) {
                windowCount.merge(val, 1, Integer::sum);
            }
        }

        int ans = -1;
        for (Map.Entry<Integer, Integer> e : windowCount.entrySet()) {
            if (e.getValue() <= 1) {
                ans = Math.max(ans, e.getKey());
            }
        }
        return ans;
        
    }
}