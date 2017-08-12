package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class KosarajuSCC {
    private final Digraph G;
    private final boolean[] marked;
    private final int id[];
    private int count;

    public KosarajuSCC(Digraph G) {
        this.G = G;
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder dfo = new DepthFirstOrder(G.reversed());
        for (int s : dfo.reversePost()) {
            if (!marked[s]) {
                dfs(s);
                count++;
            }
        }
    }

    private void dfs(int s) {
        marked[s] = true;
        id[s] = count;
        for (int v : G.adj(s)) {
            if (!marked[v]) {
                dfs(v);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("data/tinyDG.txt"));

        Digraph G = new Digraph(sc);
        KosarajuSCC scc = new KosarajuSCC(G);

        System.out.println(scc.count() + " components");
        for (int component = 0; component < scc.count(); component++) {
            for (int v = 0; v < G.V(); v++) {
                if (scc.id(v) == component) {
                    System.out.print(v + " ");
                }
            }
            System.out.println();
        }
    }
}
