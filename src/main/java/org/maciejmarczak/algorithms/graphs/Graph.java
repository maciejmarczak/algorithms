package org.maciejmarczak.algorithms.graphs;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Graph {

    private final int V;
    private int E;
    private List<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int V) {
        this.V = V; this.E = 0;
        adj = (List<Integer>[]) new List[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    public Graph(Scanner sc) throws IOException {
        this(sc.nextInt());
        int E = sc.nextInt();

        for (int i = 0; i < E; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                V + " vertices, " + E + " edges\n");

        for (int v = 0; v < V; v++) {
            sb.append(v).append(": ");
            for (int w : adj(v)) {
                sb.append(w).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
