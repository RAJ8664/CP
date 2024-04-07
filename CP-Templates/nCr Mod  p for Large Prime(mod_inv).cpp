// C++ program for nCr Mod  p forLarge Prime
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

#define ll long long
const int maxn = 100001, p=1000000007;
ll fact[maxn], invf[maxn];


class RAJ{
public:
    //Iterative Function to calculate (x^y)%p in O(log y)
    long long p;
    unsigned long long power(unsigned long long x, int y, int p){
        unsigned long long res = 1; // Initialize result
     
        x = x % p; // Update x if it is more than or
        // equal to p
     
        while (y > 0)
        {
         
            // If y is odd, multiply x with result
            if (y & 1)
                res = (res * x) % p;
     
            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        return res;
    }

    // Returns n^(-1) mod p
    unsigned long long modInv(unsigned long long n, int p){
        return power(n, p - 2, p);
    }
    void premcompute(){
        fact[0]=1;
        fact[1]=1;
        invf[0]=1;
        invf[0]=1;
        
        for(int i = 2; i < maxn; i++)
        {
            fact[i] = (fact[i-1] * i) % p;
            invf[i] = modInv(fact[i] , p);
        }
    }
    ll query(int n, int r){
        return ((fact[n]*invf[r] % p) * invf[n-r]) % p;
    }
};


void printNcR(int n, int r)
{
 
    // p holds the value of n*(n-1)*(n-2)...,
    // k holds the value of r*(r-1)...
    long long p = 1, k = 1;
 
    // C(n, r) == C(n, n-r),
    // choosing the smaller value
    if (n - r < r)
        r = n - r;
 
    if (r != 0) {
        while (r) {
            p *= n;
            k *= r;
 
            // gcd of p, k
            long long m = __gcd(p, k);
 
            // dividing by gcd, to simplify
            // product division by their gcd 
            // saves from the overflow
            p /= m;
            k /= m;
 
            n--;
            r--;
        }
 
        // k should be simplified to 1
        // as C(n, r) is a natural number
        // (denominator should be 1 ) .
    }
 
    else
        p = 1;
 
    // if our approach is correct p = ans and k =1
    cout << p << endl;
}
 


// Driver program
int main()
{

    #ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output2.txt", "w", stdout);
    #endif

	// p must be a prime greater than n.
	int n = 10, r = 2, p = 13;
	class RAJ obj;
	obj.p = p;
	obj.premcompute();
	cout << "Value of nCr % p is " << obj.query(n, r);
	return 0;
}
