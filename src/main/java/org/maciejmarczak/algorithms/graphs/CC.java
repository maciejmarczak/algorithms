package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CC {

    private final boolean[] marked;
    private final int[] id;
    private int count = 0;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        id[s] = count;
        for (int v : G.adj(s)) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(
                new File("data/tinyG.txt"));

        Graph G = new Graph(sc);
        CC cc = new CC(G);

        int M = cc.count();
        System.out.format("%d components\n", M);

        ArrayList<List<Integer>> components =
                new ArrayList<>(M);

        for (int i = 0; i < M; i++) {
            components.add(new LinkedList<>());
        }

        for (int v = 0; v < G.V(); v++) {
            components.get(cc.id(v)).add(v);
        }

        for (int i = 0; i < M; i++) {
            System.out.format("Component %d :", i);
            for (int v : components.get(i)) {
                System.out.print(" " + v);
            }
            System.out.println();
        }
    }
}
