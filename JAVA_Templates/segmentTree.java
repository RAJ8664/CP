package JAVA_Templates;
class segmentTree {
    static Node seg[];
    static int arr[];
    public segmentTree(int n, int nums[]) {
        arr = new int[n];
        seg = new Node[4 * n + 1];
        for (int i = 0; i < 4 * n + 1; i++)
            seg[i] = new Node();
        for (int i = 0; i < n; i++)
            arr[i] = nums[i];
        build(1, 0, n - 1);
    }
    static class Node {
        long sum, gcd, mini, maxi;
        public Node() {
            this.sum = 0;
            this.gcd = 0;
            this.mini = Integer.MAX_VALUE;
            this.maxi = Integer.MIN_VALUE;
        }
    }
    static Node Merge(Node a, Node b) {
        Node res = new Node();
        res.sum = a.sum + b.sum;
        res.mini = Math.min(a.mini, b.mini);
        res.maxi = Math.max(a.maxi, b.maxi);
        res.gcd = GCD(a.gcd, b.gcd);
        return res;
    }
    static void build(int ind, int l, int r) {
        if (l == r) {
            seg[ind].sum = arr[l];
            seg[ind].mini = arr[l];
            seg[ind].maxi = arr[l];
            seg[ind].gcd = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * ind, l, mid);
        build(2 * ind + 1, mid + 1, r);
        seg[ind] = Merge(seg[2 * ind], seg[2 * ind + 1]);
    }
    //point update -- > change the value at index pos by value = val;
    static void update(int ind, int l, int r, int pos, int val) {
        if (pos < l || pos > r)
            return;
        if (l == r) {
            seg[ind].sum = val;
            seg[ind].mini = val;
            seg[ind].maxi = val;
            seg[ind].gcd = val;
            return;
        }
        int mid = (l + r) / 2;
        update(2 * ind, l, mid, pos, val);
        update(2 * ind + 1, mid + 1, r, pos, val);
        seg[ind] = Merge(seg[2 * ind], seg[2 * ind + 1]);
    }
    static Node query(int ind, int l, int r, int ql, int qr) {
        if (qr < l || ql > r)
            return new Node();
        if (l >= ql && r <= qr)
            return seg[ind];
        int mid = (l + r) / 2;
        Node left = query(2 * ind, l, mid, ql, qr);
        Node right = query(2 * ind + 1, mid + 1, r, ql, qr);
        return Merge(left, right);
    }
    static long GCD(long x, long y) {
        if (y == 0)
            return x;
        return GCD(y, x % y);
    }
}

