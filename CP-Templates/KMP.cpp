#include <bits/stdc++.h>

using namespace std;

#define cin_2d(vec, n, m) for(int i = 0; i < n; i++) for(int j = 0; j < m && cin >> vec[i][j]; j++);
#define cout_2d(vec, n, m) for(int i = 0; i < n; i++, cout << "\n") for(int j = 0; j < m && cout << vec[i][j] << " "; j++);
#define cout_map(mp) for(auto& [f, s] : mp) cout << f << "  " << s << "\n";
#define Time cerr << "Time Taken: " << (float)clock() / CLOCKS_PER_SEC << " Secs" << "\n";
#define fixed(n) fixed << setprecision(n)
#define ceil(n, m) (((n) / (m)) + ((n) % (m) ? 1 : 0))
#define fill(vec, value) memset(vec, value, sizeof(vec));
#define Num_of_Digits(n) ((int)log10(n) + 1)
#define mod_combine(a, b, m) (((a % m) * (b % m)) % m)
#define all(vec) vec.begin(), vec.end()
#define rall(vec) vec.rbegin(), vec.rend()
#define sz(x) int(x.size())
#define debug(x) cout << #x << ": " << (x) << "\n";
#define fi first
#define se second
#define Pair pair < int, int >
#define ll long long
#define ull unsigned long long
#define Mod  1'000'000'007
#define OO 2'000'000'000
#define EPS 1e-9
#define PI acos(-1)

template < typename T = int > istream& operator >> (istream &in, vector < T > &v) {
    for (auto &x: v) in >> x;
    return in;
}

template < typename T = int > ostream& operator << (ostream &out, const vector < T > &v) { 
    for (const T &x: v) out << x << ' '; 
    return out;
}


vector < int > Compute_Prefix(string pat){
    vector < int > longest_prefix(sz(pat));
    for(int i = 0, k; i < sz(pat); i++){
        while(k > 0 && pat[k] != pat[i])
            k = longest_prefix[k - 1];
        if(pat[k] == pat[i]) k++;
        else k = longest_prefix[k - 1];
    }
    return longest_prefix;
}

void KMP(string str, string pat){
    vector < int > longest_prefix = Compute_Prefix(pat);
    for(int i = 0, k; i < sz(str); i++){
        while(k > 0 && pat[k] != str[i])
            k = longest_prefix[k - 1];
        if(pat[k] == str[i]) k++;
        if(k == sz(pat))
            k = longest_prefix[k - 1];
    }
}

void Solve(){
    
}

int main(){
    int t = 1;
    //cin >> t;
    while(t--)
        Solve();
    return 0;
} 
