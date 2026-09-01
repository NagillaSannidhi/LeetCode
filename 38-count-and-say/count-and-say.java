class Solution {
    public String countAndSay(int n) {
        String result = "1";
        for (int i = 1; i < n; i++) {
            result = nextTerm(result);
        }
        return result;
    }

    private String nextTerm(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int len = s.length();
        while (i < len) {
            char c = s.charAt(i);
            int count = 1;
            while (i + 1 < len && s.charAt(i + 1) == c) {
                count++;
                i++;
            }
            sb.append(count).append(c);
            i++;
        }
        return sb.toString();
        
    }
}