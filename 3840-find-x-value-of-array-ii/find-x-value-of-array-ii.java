class Solution {
    private int k;
    private long[][] total;
    private long[][][] count;
    private int n;
    private int[] nums;

    public int[] resultArray(int[] numsIn, int k, int[][] queries) {
        this.nums = numsIn.clone();
        this.k = k;
        this.n = nums.length;

        total = new long[4 * n][];
        count = new long[4 * n][][];

        build(1, 0, n - 1);

        int[] result = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int index = queries[qi][0];
            int value = queries[qi][1];
            int start = queries[qi][2];
            int x = queries[qi][3];

            nums[index] = value;
            update(1, 0, n - 1, index);

            long[][][] holder = new long[1][][];
            long[][] holderTotal = new long[1][];
            queryRange(1, 0, n - 1, start, n - 1, holder, holderTotal);

            result[qi] = (int) holder[0][1 % k][x];
        }

        return result;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            int a = nums[l] % k;
            long[] t = new long[k];
            long[][] c = new long[k][k];
            for (int rIn = 0; rIn < k; rIn++) {
                int rOut = (rIn * a) % k;
                t[rIn] = rOut;
                c[rIn][rOut] = 1;
            }
            total[node] = t;
            count[node] = c;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        merge(node, 2 * node, 2 * node + 1);
    }

    private void merge(int parent, int leftNode, int rightNode) {
        long[] lt = total[leftNode], rt = total[rightNode];
        long[][] lc = count[leftNode], rc = count[rightNode];

        long[] t = new long[k];
        long[][] c = new long[k][k];

        for (int rIn = 0; rIn < k; rIn++) {
            int mid = (int) lt[rIn];
            t[rIn] = rt[mid];
            for (int x = 0; x < k; x++) {
                c[rIn][x] = lc[rIn][x] + rc[mid][x];
            }
        }

        total[parent] = t;
        count[parent] = c;
    }

    private void update(int node, int l, int r, int idx) {
        if (l == r) {
            int a = nums[l] % k;
            long[] t = new long[k];
            long[][] c = new long[k][k];
            for (int rIn = 0; rIn < k; rIn++) {
                int rOut = (rIn * a) % k;
                t[rIn] = rOut;
                c[rIn][rOut] = 1;
            }
            total[node] = t;
            count[node] = c;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node, l, mid, idx);
        else update(2 * node + 1, mid + 1, r, idx);
        merge(node, 2 * node, 2 * node + 1);
    }

    
    private void queryRange(int node, int l, int r, int ql, int qr,
                             long[][][] countHolder, long[][] totalHolder) {
        if (qr < l || r < ql) return;
        if (ql <= l && r <= qr) {
            if (totalHolder[0] == null) {
                totalHolder[0] = total[node];
                countHolder[0] = count[node];
            } else {
                
                long[] lt = totalHolder[0];
                long[][] lc = countHolder[0];
                long[] rt = total[node];
                long[][] rc = count[node];

                long[] t = new long[k];
                long[][] c = new long[k][k];
                for (int rIn = 0; rIn < k; rIn++) {
                    int mid = (int) lt[rIn];
                    t[rIn] = rt[mid];
                    for (int x = 0; x < k; x++) {
                        c[rIn][x] = lc[rIn][x] + rc[mid][x];
                    }
                }
                totalHolder[0] = t;
                countHolder[0] = c;
            }
            return;
        }
        int mid = (l + r) / 2;
        queryRange(2 * node, l, mid, ql, qr, countHolder, totalHolder);
        queryRange(2 * node + 1, mid + 1, r, ql, qr, countHolder, totalHolder);
    }
}