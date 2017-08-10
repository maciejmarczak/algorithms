package org.maciejmarczak.algorithms.graphs;

import java.io.IOException;
import java.util.Scanner;

public class Digraph extends Graph {

    public Digraph(int V) {
        super(V);
    }

    public Digraph(Scanner sc) throws IOException {
        super(sc);
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Digraph reversed() {
        Digraph digraph = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj[v]) {
                digraph.addEdge(w, v);
            }
        }
        return digraph;
    }
}
