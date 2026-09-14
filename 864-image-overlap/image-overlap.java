class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        List<int[]> onesA = new ArrayList<>();
        List<int[]> onesB = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) onesA.add(new int[]{r, c});
                if (img2[r][c] == 1) onesB.add(new int[]{r, c});
            }
        }

        
        Map<Integer, Integer> offsetCount = new HashMap<>();
        int maxOverlap = 0;

        for (int[] a : onesA) {
            for (int[] b : onesB) {
                int dx = b[0] - a[0];
                int dy = b[1] - a[1];
                
                int key = (dx + n) * 200 + (dy + n); 
                int count = offsetCount.merge(key, 1, Integer::sum);
                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        return maxOverlap;
        
    }
}