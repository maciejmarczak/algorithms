package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Cycle {

    private boolean[] marked;
    private boolean hasCycle = false;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s, s);
            }
        }
    }

    private void dfs(Graph G, int curr, int prev) {
        marked[curr] = true;
        for (int v : G.adj(curr)) {
            if (!marked[v]) {
                dfs(G, v, curr);
            } else if (v != prev) {
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(
                new File("data/tinyAC.txt"));

        Graph G = new Graph(sc);
        Cycle cycle = new Cycle(G);

        System.out.println("Graph has cycle: " + cycle.hasCycle());
    }

}
