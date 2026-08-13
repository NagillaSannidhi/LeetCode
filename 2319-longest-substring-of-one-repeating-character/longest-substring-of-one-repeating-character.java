class Solution {
    static class Node {
        char prefChar, sufChar;
        int prefLen, sufLen, best, size;

        Node() {}

        Node(char ch, int size) {
            this.prefChar = ch;
            this.sufChar = ch;
            this.prefLen = size;
            this.sufLen = size;
            this.best = size;
            this.size = size;
        }
    }
    private Node[] tree;
    private char[] s;
    private int n;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.n = s.length();
        this.s = s.toCharArray();
        this.tree = new Node[4 * n];
        build(1, 0, n - 1);

        int q = queryIndices.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            this.s[idx] = ch;
            update(1, 0, n - 1, idx, ch);
            ans[i] = tree[1].best;
        }
        return ans;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(s[l], 1);
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int l, int r, int idx, char ch) {
        if (l == r) {
            tree[node] = new Node(ch, 1);
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, r, idx, ch);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.size = left.size + right.size;
        res.best = Math.max(left.best, right.best);
        if (left.prefLen == left.size && left.prefChar == right.prefChar) {
            res.prefChar = left.prefChar;
            res.prefLen = left.size + right.prefLen;
        } else {
            res.prefChar = left.prefChar;
            res.prefLen = left.prefLen;
        }
        if (right.sufLen == right.size && right.sufChar == left.sufChar) {
            res.sufChar = right.sufChar;
            res.sufLen = right.size + left.sufLen;
        } else {
            res.sufChar = right.sufChar;
            res.sufLen = right.sufLen;
        }
        if (left.sufChar == right.prefChar) {
            res.best = Math.max(res.best, left.sufLen + right.prefLen);
        }

        return res;
    }
}