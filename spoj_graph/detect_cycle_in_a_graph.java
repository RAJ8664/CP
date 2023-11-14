
import java.util.*;
public class detect_cycle_in_a_graph {
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


		boolean ans = false;
		for(int i = 1; i <= n; i++) {
			if(vis[i] == 1) continue;
			if(dfs(i,-1,vis,adj)) ans = true;
		}

		System.out.println(ans);
	}

	public static boolean dfs(int u, int parent,int vis[] , ArrayList<ArrayList<Integer>> adj) {
		vis[u] = 1;
		boolean temp = false;
		for(int child : adj.get(u)) {
			if(vis[child] == 1 && child == parent) continue;
			else if(vis[child] == 1) return true;
			temp |= dfs(child,u,vis,adj);
		}
		return temp;
	}
}