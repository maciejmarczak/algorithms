package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DirectedDFS {

    private final Digraph G;
    private final Iterable<Integer> sources;
    private final boolean[] marked;

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        this.G = G;
        this.sources = sources;
        this.marked = new boolean[G.V()];

        for (int s : sources) {
            if (!marked[s]) {
                dfs(s);
            }
        }
    }

    private void dfs(int s) {
        marked[s] = true;
        for (int v : G.adj(s)) {
            if (!marked[v]) {
                dfs(v);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("data/tinyG.txt"));

        Digraph G = new Digraph(sc);
        Iterable<Integer> sources = Arrays.asList(1, 2, 6);

        DirectedDFS directedDFS = new DirectedDFS(G, sources);
        for (int i = 0; i < G.V(); i++) {
            if (directedDFS.marked[i]) {
                System.out.print(i + " ");
            }
        }
    }
}
