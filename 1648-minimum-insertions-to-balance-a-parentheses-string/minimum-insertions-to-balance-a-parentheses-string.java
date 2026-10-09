class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int insertions = 0;
        int open = 0;
        int i = 0;

        while (i < n) {
            if (s.charAt(i) == '(') {
                open++;
                i++;
            } else { // s[i] == ')'
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i += 2;
                } else {
                    insertions++;
                    i++;
                }

                if (open > 0) {
                    open--;
                } else {
                    insertions++;
                }
            }
        }
        return insertions + open * 2;
        
    }
}