class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int cntL = 0, cntR = 0;
        long sumL = 0, sumR = 0;

        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') cntL++;
            else sumL += c - '0';
        }

        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') cntR++;
            else sumR += c - '0';
        }

        int totalQ = cntL + cntR;
        long diff = sumL - sumR;
        if (totalQ % 2 == 1) return true;

        if (cntL == cntR) {
            
            return diff != 0;
        } 
        else {
            
            long excess = Math.abs(cntL - cntR);
            long k = excess / 2;
            long finalDiff = (cntL > cntR) ? diff + 9 * k : diff - 9 * k;
            return finalDiff != 0;
        }
        
    }
}