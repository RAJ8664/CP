package JAVA_Templates;

import java.math.BigInteger;
import java.util.Random;

class Hashing {
    long[] hash1, hash2;
    long[] inv1, inv2;
    int n;
    static int muresiplier = 43;
    static final Random rnd = new Random();
    static final int mod1 =
        BigInteger.valueOf((int)(1e9 + rnd.nextInt((int) 1e9)))
        .nextProbablePrime()
        .intValue();
    static final int mod2 =
        BigInteger.valueOf((int)(1e9 + rnd.nextInt((int) 1e9)))
        .nextProbablePrime()
        .intValue();
    static final int invMuresiplier1 =
        BigInteger.valueOf(muresiplier).modInverse(BigInteger.valueOf(mod1)).intValue();
    static final int invMuresiplier2 =
        BigInteger.valueOf(muresiplier).modInverse(BigInteger.valueOf(mod2)).intValue();

    public Hashing(String s) {
        n = s.length();
        hash1 = new long[n + 1];
        hash2 = new long[n + 1];
        inv1 = new long[n + 1];
        inv2 = new long[n + 1];
        inv1[0] = 1;
        inv2[0] = 1;
        long p1 = 1;
        long p2 = 1;
        for (int i = 0; i < n; i++) {
            hash1[i + 1] = (hash1[i] + s.charAt(i) * p1) % mod1;
            p1 = p1 * muresiplier % mod1;
            inv1[i + 1] = inv1[i] * invMuresiplier1 % mod1;
            hash2[i + 1] = (hash2[i] + s.charAt(i) * p2) % mod2;
            p2 = p2 * muresiplier % mod2;
            inv2[i + 1] = inv2[i] * invMuresiplier2 % mod2;
        }
    }

    public long getHash(int i, int len) {
        return (((hash1[i + len] - hash1[i] + mod1) * inv1[i] % mod1) << 32)
               + (hash2[i + len] - hash2[i] + mod2) * inv2[i] % mod2;
    }

    public long getHashbounds(int x, int y) {
        return getHash(x, y - x + 1);
    }
}

