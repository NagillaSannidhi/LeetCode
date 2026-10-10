class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] d = new long[n];
        for (int i = 0; i < n; i++) {
            d[i] = Math.abs(nums1[i] - nums2[i]);
        }
        Arrays.sort(d);

        long k = (long) k1 + k2;
        int i = n - 1; 
        long cnt = 0;         
        long level = d[n - 1];

        while (k > 0 && level > 0) {
            while (i >= 0 && d[i] == level) {
                cnt++;
                i--;
            }

            long next = (i >= 0) ? d[i] : 0; 
            long steps = level - next;       
            long need = steps * cnt;        

            if (k >= need) {
                k -= need;
                level = next;
            } else {
                long full = k / cnt;   
                long rem = k % cnt;    
                level -= full;

                long result = rem * (level - 1) * (level - 1)
                            + (cnt - rem) * level * level;
                for (int j = 0; j <= i; j++) result += d[j] * d[j];
                return result;
            }
        }
        long result = cnt * level * level;
        for (int j = 0; j <= i; j++) result += d[j] * d[j];
        return result;
        
    }
}