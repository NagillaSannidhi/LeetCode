class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        int[] running = freq.clone();
        int lastValidI = -1;
        int[] lastValidFreq = null;

        for (int i = 0; i < n; i++) {
            int tChar = target.charAt(i) - 'a';

            
            for (int c = tChar + 1; c < 26; c++) {
                if (running[c] > 0) {
                    lastValidI = i;
                    lastValidFreq = running.clone();
                    break;
                }
            }

            
            if (running[tChar] > 0) {
                running[tChar]--;
            } else {
                break;
            }
        }

        if (lastValidI == -1) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(target, 0, lastValidI);

        int tChar = target.charAt(lastValidI) - 'a';
        int chosen = -1;
        for (int c = tChar + 1; c < 26; c++) {
            if (lastValidFreq[c] > 0) { chosen = c; break; }
        }
        lastValidFreq[chosen]--;
        sb.append((char) ('a' + chosen));

        for (int c = 0; c < 26; c++) {
            for (int k = 0; k < lastValidFreq[c]; k++) {
                sb.append((char) ('a' + c));
            }
        }

        return sb.toString();
        
    }
}