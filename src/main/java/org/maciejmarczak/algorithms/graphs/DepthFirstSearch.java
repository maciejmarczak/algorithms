package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DepthFirstSearch {

    private boolean[] marked;
    private int count = 0;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        count++;
        for (Integer v : G.adj(s)) {
            if (!marked(v)) {
                dfs(G, v);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(
                new File("data/tinyCG.txt"));

        Graph G = new Graph(sc);
        DepthFirstSearch search = new DepthFirstSearch(G, 9);

        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();

        if (search.count() != G.V()) {
            System.out.print("NOT ");
        }
        System.out.println("connected");
    }
}
