class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        int[] queens = new int[n]; 

        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; 
        boolean[] diag2 = new boolean[2 * n - 1]; 

        backtrack(0, n, queens, cols, diag1, diag2, results);
        return results;
    }

    private void backtrack(int row, int n, int[] queens, boolean[] cols,
                            boolean[] diag1, boolean[] diag2,
                            List<List<String>> results) {
        if (row == n) {
            results.add(buildBoard(queens, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + (n - 1);
            int d2 = row + col;

            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue; 
            }

            
            queens[row] = col;
            cols[col] = diag1[d1] = diag2[d2] = true;

            backtrack(row + 1, n, queens, cols, diag1, diag2, results);

            
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }

    private List<String> buildBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            char[] rowChars = new char[n];
            Arrays.fill(rowChars, '.');
            rowChars[queens[row]] = 'Q';
            board.add(new String(rowChars));
        }
        return board;
    }
}