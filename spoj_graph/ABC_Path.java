    /**
     *
     * Author=RAJ ROY;
     * scholar id = 2212002;
     *
     **/
     
    /*
                $$$$$$__ __$$$___ $$$$$$$_ __$$$$$$__ __$$$___ $$____$$_
                $$___$$_ _$$_$$__ $$___$$_ __$$___$$_ _$$_$$__ _$$__$$__
                $$___$$_ $$___$$_ _____$$_ __$$___$$_ $$___$$_ __$$$$___
                $$$$$$__ $$$$$$$_ _____$$_ __$$$$$$__ $$___$$_ ___$$____
                $$___$$_ $$___$$_ $$___$$_ __$$___$$_ _$$_$$__ ___$$____
                $$___$$_ $$___$$_ _$$$$$__ __$$___$$_ __$$$___ ___$$____
    */
     
    //scholar id = 2212002
     
    import java.io.*;
    import java.util.*;
    class Main {
     
        static class FastReader {
            BufferedReader br;
            StringTokenizer st;
     
            public FastReader() {
                br = new BufferedReader(
                        new InputStreamReader(System.in));
            }
     
            String next() {
                while (st == null || !st.hasMoreElements()) {
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return st.nextToken();
            }
     
            int nextInt() {
                return Integer.parseInt(next());
            }
     
            long nextLong() {
                return Long.parseLong(next());
            }
     
            double nextDouble() {
                return Double.parseDouble(next());
            }
     
            String nextLine() {
                String str = "";
                try {
                    if (st.hasMoreTokens()) {
                        str = st.nextToken("\n");
                    } else {
                        str = br.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return str;
            }
        }
     
        public static void main(String[] args) {
           FastReader sc = new FastReader();
           int counter = 1;
           while(true) {
                
     
                int row = sc.nextInt();
                int col = sc.nextInt();
                if(row == 0 && col == 0) break;
                char mat[][] = new char[row][col];
                int dp[][] = new int[100][100];
                for(int temp[] : dp){
                    Arrays.fill(temp, -1);
                }
                for(int i = 0; i < row; i++) {
                    String s = sc.next();
                    for(int j = 0; j < s.length(); j++ ) {
                        mat[i][j] = s.charAt(j);
                    }
                }
                int ans = 0;
                for(int i = 0; i < row; i++) {
                    for(int j = 0; j < col; j++) {
                        if(mat[i][j] == 'A') {
                            ans = Math.max(ans,dfs(i,j,mat,dp));
                        }
                    }
                }
     
                System.out.println("Case " + counter + ": " + ans);
                counter++;
           }
       }
     
           public static int dfs(int row, int col, char mat[][], int dp[][]) {
                int n = mat.length;
                int m = mat[0].length;
                if(dp[row][col] != -1) return dp[row][col];
                int dir[][] = {{-1, 0} , {1, 0} , {0, -1} , {0, 1} , {-1, -1} , {-1, 1} , {1, -1} , {1, 1}};
                int total = 0;
                int temp = 0;
                for(int dire[] : dir) {
                    int nrow = row + dire[0];
                    int ncol = col + dire[1];
                    if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && (mat[nrow][ncol] == mat[row][col] + 1)) {
                        temp = 1 + dfs(nrow, ncol, mat,dp);
                    }
                    else temp = 1;
                    total = Math.max(total, temp);
                }
                return dp[row][col] = total;
           }
    } 