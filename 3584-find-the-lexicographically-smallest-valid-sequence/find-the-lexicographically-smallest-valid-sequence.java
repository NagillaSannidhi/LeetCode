class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        if (m > n) return new int[0];

        int[] suf = new int[m + 1];
        suf[m] = n;

        int pos = n - 1;
        int j = m - 1;
        boolean impossible = false;

        while (j >= 0) {
            while (pos >= 0 && word1.charAt(pos) != word2.charAt(j)) {
                pos--;
            }
            if (pos < 0) {
                impossible = true;
                break;
            }
            suf[j] = pos;
            pos--;
            j--;
        }

        if (impossible) {
            for (int k = j; k >= 0; k--) {
                suf[k] = -1;
            }
        }

        int[] result = new int[m];
        int idx = 0;
        boolean mismatched = false;

        for (int i = 0; i < n && idx < m; i++) {
            if (word1.charAt(i) == word2.charAt(idx)) {
                result[idx++] = i;
            } else if (!mismatched && i < suf[idx + 1]) {   // <-- fixed: strict '<'
                result[idx++] = i;
                mismatched = true;
            }
        }

        return idx == m ? result : new int[0];
        
    }
}