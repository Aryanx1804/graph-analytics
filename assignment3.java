import java.util.*;

public class Main {

    static int[] parent;

    // Find the parent of a node
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union two nodes
    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        // If both have same parent, cycle will be formed
        if (rootA == rootB) {
            return false;
        }

        parent[rootB] = rootA;
        return true;
    }

    static class Edge {
        int u;
        int v;
        int weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Number of nodes and edges
        int n = sc.nextInt();
        int m = sc.nextInt();

        Edge[] edges = new Edge[m];

        // Take edge input
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();

            edges[i] = new Edge(u, v, weight);
        }

        // Sort edges by weight
        // If weight is same, use u + v
        Arrays.sort(edges, new Comparator<Edge>() {

            public int compare(Edge a, Edge b) {

                if (a.weight != b.weight) {
                    return a.weight - b.weight;
                }

                return (a.u + a.v) - (b.u + b.v);
            }
        });

        // Initialize parent
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int totalWeight = 0;
        int count = 0;

        // Kruskal's Algorithm
        for (int i = 0; i < m; i++) {

            Edge edge = edges[i];

            // Add edge if it doesn't create a cycle
            if (union(edge.u, edge.v)) {

                totalWeight += edge.weight;
                count++;

                // MST requires n - 1 edges
                if (count == n - 1) {
                    break;
                }
            }
        }

        System.out.println(totalWeight);

        sc.close();
    }
}
