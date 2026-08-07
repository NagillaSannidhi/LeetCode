class Solution {
    public String smallestNumber(String num, long t) {
        int[] req = factorReq(t);
        if (req == null) return "-1";

        int n = num.length();
        int[][] prefix = new int[n + 1][4];
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            int[] c = (d != 0) ? digitContrib(d) : new int[]{0, 0, 0, 0};
            for (int k = 0; k < 4; k++) prefix[i + 1][k] = prefix[i][k] + c[k];
        }

        int[] baseCounts = minimalMultiset(req[0], req[1], req[2], req[3]);
        int lMin = totalCount(baseCounts);
        if (lMin > n) {
            return constructMinimalNumber(lMin, req);
        }

        int z = num.indexOf('0');
        int startI;
        if (z == -1) {
            int[] rem = subClamped(req, prefix[n]);
            boolean allZero = true;
            for (int k = 0; k < 4; k++) if (rem[k] != 0) allZero = false;
            if (allZero) return num; // num itself already works
            startI = n - 1;
        } else {
            startI = Math.min(n - 1, z);
        }

        for (int i = startI; i >= 0; i--) {
            int remainingPositions = n - i - 1;
            int curDigit = num.charAt(i) - '0';
            for (int dprime = curDigit + 1; dprime <= 9; dprime++) {
                int[] dc = digitContrib(dprime);
                int[] used = new int[4];
                for (int k = 0; k < 4; k++) used[k] = prefix[i][k] + dc[k];
                int[] R = subClamped(req, used);
                int[] counts = minimalMultiset(R[0], R[1], R[2], R[3]);
                int m = totalCount(counts);
                if (m <= remainingPositions) {
                    int pad = remainingPositions - m;
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append((char) ('0' + dprime));
                    for (int p = 0; p < pad; p++) sb.append('1');
                    sb.append(countsToSortedString(counts));
                    return sb.toString();
                }
            }
        }
        return constructMinimalNumber(n + 1, req);
    }
    private int[] factorReq(long t) {
        int[] req = new int[4];
        int[] primes = {2, 3, 5, 7};
        for (int idx = 0; idx < 4; idx++) {
            while (t % primes[idx] == 0) {
                req[idx]++;
                t /= primes[idx];
            }
        }
        if (t != 1) return null;
        return req;
    }
    private int[] digitContrib(int d) {
        switch (d) {
            case 1: return new int[]{0, 0, 0, 0};
            case 2: return new int[]{1, 0, 0, 0};
            case 3: return new int[]{0, 1, 0, 0};
            case 4: return new int[]{2, 0, 0, 0};
            case 5: return new int[]{0, 0, 1, 0};
            case 6: return new int[]{1, 1, 0, 0};
            case 7: return new int[]{0, 0, 0, 1};
            case 8: return new int[]{3, 0, 0, 0};
            case 9: return new int[]{0, 2, 0, 0};
            default: return new int[]{0, 0, 0, 0};
        }
    }
    private int[] minimalMultiset(int c2, int c3, int c5, int c7) {
        c2 = Math.max(c2, 0);
        c3 = Math.max(c3, 0);
        c5 = Math.max(c5, 0);
        c7 = Math.max(c7, 0);

        int nines = c3 / 2, c3r = c3 % 2;
        int eights = c2 / 3, c2r = c2 % 3;
        int sixes = 0;
        if (c3r == 1 && c2r >= 1) {
            sixes = 1;
            c3r = 0;
            c2r -= 1;
        }
        int fours = (c2r == 2) ? 1 : 0;
        int twos = (c2r == 1) ? 1 : 0;
        int threes = (c3r == 1) ? 1 : 0;

        int[] counts = new int[10]; 
        counts[9] = nines;
        counts[8] = eights;
        counts[7] = c7;
        counts[6] = sixes;
        counts[5] = c5;
        counts[4] = fours;
        counts[3] = threes;
        counts[2] = twos;
        return counts;
    }

    private int totalCount(int[] counts) {
        int s = 0;
        for (int d = 2; d <= 9; d++) s += counts[d];
        return s;
    }

    private String countsToSortedString(int[] counts) {
        StringBuilder sb = new StringBuilder();
        for (int d = 2; d <= 9; d++) {
            for (int k = 0; k < counts[d]; k++) sb.append((char) ('0' + d));
        }
        return sb.toString();
    }

    private String constructMinimalNumber(int length, int[] req) {
        int[] counts = minimalMultiset(req[0], req[1], req[2], req[3]);
        int m = totalCount(counts);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - m; i++) sb.append('1');
        sb.append(countsToSortedString(counts));
        return sb.toString();
    }

    private int[] subClamped(int[] req, int[] vec) {
        int[] r = new int[4];
        for (int k = 0; k < 4; k++) r[k] = Math.max(req[k] - vec[k], 0);
        return r;
        
    }
}