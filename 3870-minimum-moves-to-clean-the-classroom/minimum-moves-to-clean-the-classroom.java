class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int sx = 0, sy = 0, cnt = 0;
        int[][] litterIdx = new int[m][n];
        for (int[] row : litterIdx) Arrays.fill(row, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    sx = i; sy = j;
                } else if (c == 'L') {
                    litterIdx[i][j] = cnt++;
                }
            }
        }

        if (cnt == 0) return 0;
        int full = (1 << cnt) - 1;

        
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << cnt];
        visited[sx][sy][energy][0] = true;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy, energy, 0, 0}); // x, y, e, mask, moves

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], e = cur[2], mask = cur[3], moves = cur[4];

            if (mask == full) return moves;
            if (e == 0) continue; 

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                char c = classroom[nx].charAt(ny);
                if (c == 'X') continue;

                int ne = e - 1;
                int nmask = mask;
                if (litterIdx[nx][ny] != -1) {
                    nmask |= (1 << litterIdx[nx][ny]);
                }
                if (c == 'R') {
                    ne = energy;
                }

                if (!visited[nx][ny][ne][nmask]) {
                    visited[nx][ny][ne][nmask] = true;
                    q.offer(new int[]{nx, ny, ne, nmask, moves + 1});
                }
            }
        }

        return -1;
        
    }
}