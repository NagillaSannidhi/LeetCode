class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0; 
        int insertions = 0;  
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openNeeded++;
            } else { // c == ')'
                if (openNeeded > 0) {
                    openNeeded--; 
                } else {
                    insertions++;
                }
            }
        }
        return insertions + openNeeded;
    
        
    }
}