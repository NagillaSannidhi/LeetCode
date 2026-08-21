class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long minCoin = Long.MAX_VALUE;
        for (int c : coins) minCoin = Math.min(minCoin, c);

        long lo = 1, hi = minCoin * k;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (countLE(coins, mid) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    private long countLE(int[] coins, long x) {
        int n = coins.length;
        long total = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            long l = 1;
            int bits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    l = lcm(l, coins[i]);
                    bits++;
                    if (l > x) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (!overflow) {
                long cnt = x / l;
                if (bits % 2 == 1) {
                    total += cnt;
                } else {
                    total -= cnt;
                }
            }
        }
        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
        
    
}