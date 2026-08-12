class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            freq.merge(nums[right], 1, Integer::sum);

            // shrink window while current element exceeds k
            while (freq.get(nums[right]) > k) {
                freq.merge(nums[left], -1, Integer::sum);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
        
    }
}