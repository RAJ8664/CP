
import java.util.*;

public class connected_component_in_a_graph{
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		} 

		for(int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		int vis[] = new int[n + 1];
		Arrays.fill(vis, 0);
		int count = 0;
		for(int i = 1; i <= n; i++) {
			if(vis[i] == 0) {
				dfs(i , vis, adj);
				count++;
			}
		}

		System.out.println(count);
	}

	public static void dfs(int u , int vis[] , ArrayList<ArrayList<Integer>>  adj) {
		vis[u] = 1;
		for(int child : adj.get(u)) {
			if(vis[child] == 0) dfs(child,vis,adj);
		}
	}
}