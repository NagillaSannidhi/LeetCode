class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];
                if (val == '.') continue;
                
                int boxIndex = (r / 3) * 3 + (c / 3);
                
                String rowKey = "row" + r + val;
                String colKey = "col" + c + val;
                String boxKey = "box" + boxIndex + val;
                
                if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                    return false;
                }
            }
        }
        
        return true;
        
    }
}