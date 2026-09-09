class Solution {
    public long countCommas(long n) {
        long total = 0;
        long threshold = 1000L;

        while (threshold <= n) {
            total += n - threshold + 1;
            threshold *= 1000L;
        }

        return total;
        
    }
}