class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        char mid = 0;
        int[] halfCounts = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCounts[i] = freq[i] / 2;
            if (freq[i] % 2 == 1) {
                mid = (char) ('a' + i);
            }
        }

        int halfLen = n / 2;

        long totalArrangements = countPerms(halfCounts, halfLen, k);
        if (totalArrangements < k) {
            return "";
        }

        int[] counts = halfCounts.clone();
        int remainingLen = halfLen;
        long remainingK = k;
        StringBuilder halfStr = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (counts[c] == 0) continue;
                counts[c]--;
                long cnt = countPerms(counts, remainingLen - 1, remainingK);
                if (cnt >= remainingK) {
                    halfStr.append((char) ('a' + c));
                    remainingLen--;
                    break;
                } else {
                    remainingK -= cnt;
                    counts[c]++;
                }
            }
        }

        String half = halfStr.toString();
        StringBuilder result = new StringBuilder();
        result.append(half);
        if (mid != 0) result.append(mid);
        result.append(new StringBuilder(half).reverse());

        return result.toString();
    }

    private long countPerms(int[] counts, int totalLen, long cap) {
        long result = 1;
        int remaining = totalLen;
        for (int c : counts) {
            if (c == 0) continue;
            result *= combCapped(remaining, c, cap);
            if (result > cap) return cap + 1;
            remaining -= c;
        }
        return result;
    }

    private long combCapped(int n, int r, long cap) {
        if (r < 0 || r > n) return 0;
        r = Math.min(r, n - r);
        long result = 1;
        for (int i = 1; i <= r; i++) {
            result = result * (n - r + i) / i;
            if (result > cap) return cap + 1;
        }
        return result;
        
    }
}