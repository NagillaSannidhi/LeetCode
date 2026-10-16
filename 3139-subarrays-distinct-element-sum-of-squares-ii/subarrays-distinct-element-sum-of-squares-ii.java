class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[] b1, b2;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        b1 = new long[n + 2];
        b2 = new long[n + 2];

        int[] last = new int[100001]; 
        long f = 0, ans = 0;

        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            int j = last[x];

            long s = rangeSum(j + 1, i); 
            f = (f + (i - j) + 2 * (s % MOD)) % MOD;

            rangeAdd(j + 1, i, 1);
            last[x] = i;

            ans = (ans + f) % MOD;
        }

        return (int) ans;
    }
    private void add(long[] b, int idx, long v) {
        for (; idx <= n; idx += idx & -idx) b[idx] += v;
    }

    private long query(long[] b, int idx) {
        long s = 0;
        for (; idx > 0; idx -= idx & -idx) s += b[idx];
        return s;
    }

    private void rangeAdd(int l, int r, long v) {
        add(b1, l, v);
        add(b1, r + 1, -v);
        add(b2, l, v * (l - 1));
        add(b2, r + 1, -v * r);
    }

    private long prefixSum(int x) {
        return query(b1, x) * x - query(b2, x);
    }

    private long rangeSum(int l, int r) {
        return prefixSum(r) - prefixSum(l - 1);
    }
}