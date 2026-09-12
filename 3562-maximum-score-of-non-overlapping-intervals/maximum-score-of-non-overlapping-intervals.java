class Solution {
    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            List<Integer> iv = intervals.get(i);
            arr[i][0] = iv.get(0);
            arr[i][1] = iv.get(1);
            arr[i][2] = iv.get(2);
            arr[i][3] = i;
        }

        
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);

        int[] ends = new int[n];
        for (int i = 0; i < n; i++) ends[i] = arr[i][1];

        
        State[][] dp = new State[n + 1][5];
        for (int j = 0; j <= 4; j++) {
            dp[0][j] = new State(0, new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            int l = arr[i - 1][0];
            int w = arr[i - 1][2];
            int idx = arr[i - 1][3];

            
            int lo = 0, hi = i - 1;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (ends[mid] < l) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            int p = lo; 

            for (int j = 0; j <= 4; j++) {
                dp[i][j] = dp[i - 1][j]; 

                if (j >= 1) {
                    State prev = dp[p][j - 1];
                    List<Integer> newList = new ArrayList<>(prev.indices);
                    newList.add(idx);
                    Collections.sort(newList);

                    State candidate = new State(prev.score + w, newList);
                    if (isBetter(candidate, dp[i][j])) {
                        dp[i][j] = candidate;
                    }
                }
            }
        }

        State best = dp[n][4];
        int[] result = new int[best.indices.size()];
        for (int k = 0; k < result.length; k++) result[k] = best.indices.get(k);
        return result;
    }

   
    private boolean isBetter(State a, State b) {
        if (a.score != b.score) return a.score > b.score;

        int len = Math.min(a.indices.size(), b.indices.size());
        for (int i = 0; i < len; i++) {
            int cmp = a.indices.get(i) - b.indices.get(i);
            if (cmp != 0) return cmp < 0;
        }
        return a.indices.size() < b.indices.size();
    }
}