import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    static Reader sc = new Reader();
    static PrintWriter out = new PrintWriter(System.out);
    static Debug dbg = new Debug();
    static int mod = (int)(1000000007);  // 998244353 1000000007;
    static long hash_mod = 92233720368547753L;
    static ArrayList<HashSet<Integer >> adj;

    /*** Code Starts From Here ***/
    public static void main(String[] args) throws IOException {
        READING(); ERROR();
        // int t = 1;
        int t = sc.nextInt();
        while (t-- > 0)
            Attack();
        sc.close();
        out.flush();
    }

    public static void Attack() throws IOException {

    }

    static class Reader {
        private final int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        BufferedReader reader;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64];
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10L + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (c == '.')
                while ((c = read()) >= '0' && c <= '9')
                    ret += (c - '0') / (div *= 10);
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) <= ' ')
                ;
            do {
                sb.append((char) c);
            } while ((c = read()) > ' ');
            return sb.toString();
        }

        public int nextInt2() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }



    static long fast_pow(long a, long p, long mod) {
        long res = 1;
        while (p > 0) {
            if (p % 2 == 0) {
                a = ((a % mod) * (a % mod)) % mod;
                p /= 2;
            } else {
                res = ((res % mod) * (a % mod)) % mod;
                p--;
            }
        }
        return res;
    }

    static long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }


    // Factorials and Inverse Factorials;
    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];
    static boolean[] isPrime;
    static int[] smallestFactorOf;

    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    static long nCk(int n, int k) {
        // use precompFacts first;
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    // Prime Generator;
    static void Generate_Primes(int upto) {
        // Sieve of Eratosthenes:
        isPrime = new boolean[upto + 1];
        smallestFactorOf = new int[upto + 1];
        Arrays.fill(smallestFactorOf, 1);
        Arrays.fill(isPrime, true);
        isPrime[1] = isPrime[0] = false;
        for (long i = 2; i < upto + 1; i++) {
            if (isPrime[(int) i]) {
                smallestFactorOf[(int) i] = (int) i;
                // Mark all the muresiples greater than or equal
                // to the square of i to be false.
                for (long j = i; j * i < upto + 1; j++) {
                    if (isPrime[(int) j * (int) i]) {
                        isPrime[(int) j * (int) i] = false;
                        smallestFactorOf[(int) j * (int) i] = (int) i;
                    }
                }
            }
        }
    }

    static long Div(long x, long y) {
        return mul(x, modinv(y));
    }

    static long LCM(long a, long b) {
        return (a / GCD(a, b)) * b;
    }

    static long modinv(long x) {
        return fast_pow(x, mod - 2, mod);
    }

    static long add(long a, long b) {
        a += b;
        if (a >= mod)
            a -= mod;
        return a;
    }

    static long mod(long a, long b) {
        long r = a % b;
        return r < 0 ? r + b : r;
    }

    static long GCD(long x, long y) {
        if (y == 0)
            return x;
        return GCD(y, x % y);
    }

    static long sub(long x, long y) {
        long z = x - y;
        if (z < 0)
            z += mod;
        return z;
    }

    static long mul(long a, long b) {
        return (long)((long)((a % mod) * 1L * (b % mod)) % mod);
    }

    public static void READING() {
        if (System.getProperty("ONLINE_JUDGE") == null) {
            try {
                sc = new Reader("input.txt");
                out = new PrintWriter("output.txt");
            } catch (Exception e) {
            }
        }
    }

    public static void ERROR() {
        try {
            PrintStream fileOut =
                new PrintStream(new FileOutputStream("dbg.txt", false), true, "UTF-8");
            System.setErr(fileOut);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    static class Debug {
        public static boolean LOCAL = getLocal();

        public static boolean getLocal() {
            try {
                return System.getProperty("LOCAL") == null;
            } catch (SecurityException e) {
                return false;
            }
        }

        public static <T> String ts(T t) {
            if (t == null)
                return "null";
            if (t instanceof Iterable)
                return ts((Iterable<?>) t);
            else if (t instanceof int[]) {
                String s = Arrays.toString((int[]) t);
                return "{" + s.substring(1, s.length() - 1) + "}";
            } else if (t instanceof long[]) {
                String s = Arrays.toString((long[]) t);
                return "{" + s.substring(1, s.length() - 1) + "}";
            } else if (t instanceof char[]) {
                String s = Arrays.toString((char[]) t);
                return "{" + s.substring(1, s.length() - 1) + "}";
            } else if (t instanceof double[]) {
                String s = Arrays.toString((double[]) t);
                return "{" + s.substring(1, s.length() - 1) + "}";
            } else if (t instanceof boolean[]) {
                String s = Arrays.toString((boolean[]) t);
                return "{" + s.substring(1, s.length() - 1) + "}";
            } else if (t instanceof Object[])
                return ts((Object[]) t);
            return t.toString();
        }

        private static <T> String ts(T[] arr) {
            StringBuilder ret = new StringBuilder();
            ret.append("{");
            boolean first = true;
            for (T t : arr) {
                if (!first)
                    ret.append(", ");
                first = false;
                ret.append(ts(t));
            }
            ret.append("}");
            return ret.toString();
        }

        private static <T> String ts(Iterable<T> iter) {
            StringBuilder ret = new StringBuilder();
            ret.append("{");
            boolean first = true;
            for (T t : iter) {
                if (!first)
                    ret.append(", ");
                first = false;
                ret.append(ts(t));
            }
            ret.append("}");
            return ret.toString();
        }

        public static void print(Object... o) {
            if (LOCAL) {
                System.err.print(
                    "Line #"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + ": [");
                for (int i = 0; i < o.length; i++) {
                    if (i != 0)
                        System.err.print(", ");
                    System.err.print(ts(o[i]));
                }
                System.err.println("]");
            }
        }
    }

}
