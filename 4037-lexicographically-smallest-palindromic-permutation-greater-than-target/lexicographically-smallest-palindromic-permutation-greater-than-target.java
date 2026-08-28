class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        int oddCount = 0;
        int middleChar = -1;
        for (int c = 0; c < 26; c++) {
            if (freq[c] % 2 == 1) {
                oddCount++;
                middleChar = c;
            }
        }
        if (n % 2 == 0) {
            if (oddCount != 0) return "";
        } else {
            if (oddCount != 1) return "";
        }

        int halfLen = n / 2;
        int[] pool = new int[26];
        for (int c = 0; c < 26; c++) pool[c] = freq[c] / 2;

        int[] running = pool.clone();
        int lastValidB = -1;
        int[] lastValidSnapshot = null;
        boolean exactMatch = true;

        for (int b = 0; b < halfLen; b++) {
            int tChar = target.charAt(b) - 'a';
            for (int c = tChar + 1; c < 26; c++) {
                if (running[c] > 0) {
                    lastValidB = b;
                    lastValidSnapshot = running.clone();
                    break;
                }
            }

            
            if (running[tChar] > 0) {
                running[tChar]--;
            } else {
                exactMatch = false;
                break;
            }
        }

        String candidate1 = null;
        if (exactMatch) {
            StringBuilder front = new StringBuilder(target.substring(0, halfLen));
            StringBuilder full = new StringBuilder(front);
            if (n % 2 == 1) full.append((char) ('a' + middleChar));
            full.append(front.reverse());
            if (full.toString().compareTo(target) > 0) {
                candidate1 = full.toString();
            }
        }

        if (candidate1 != null) return candidate1;

        if (lastValidB != -1) {
            int tChar = target.charAt(lastValidB) - 'a';
            int chosen = -1;
            for (int c = tChar + 1; c < 26; c++) {
                if (lastValidSnapshot[c] > 0) { chosen = c; break; }
            }
            lastValidSnapshot[chosen]--;

            StringBuilder front = new StringBuilder();
            front.append(target, 0, lastValidB);
            front.append((char) ('a' + chosen));
            for (int c = 0; c < 26; c++) {
                for (int k = 0; k < lastValidSnapshot[c]; k++) {
                    front.append((char) ('a' + c));
                }
            }

            StringBuilder full = new StringBuilder(front);
            if (n % 2 == 1) full.append((char) ('a' + middleChar));
            full.append(new StringBuilder(front).reverse());
            return full.toString();
        }

        return "";
        
    }
}