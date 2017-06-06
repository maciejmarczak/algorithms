package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TwoColors {

    private final boolean[] marked;
    private final boolean[] color;
    private boolean isBipartite = true;

    public TwoColors(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];

        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        for (int v : G.adj(s)) {
            if (!marked[v]) {
                color[v] = !color[s];
                dfs(G, v);
            } else if (color[s] == color[v]) {
                isBipartite = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(
                new File("data/tinyAC.txt"));

        Graph G = new Graph(sc);
        TwoColors tc = new TwoColors(G);

        System.out.println("Graph is bipartite: " + tc.isBipartite());
    }
}
