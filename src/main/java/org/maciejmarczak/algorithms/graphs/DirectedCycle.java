package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class DirectedCycle {

    private final Digraph G;
    private final int[] edgeTo;
    private final boolean[] marked;
    private final boolean[] onStack;

    private Stack<Integer> cycle;

    public DirectedCycle(Digraph G) {
        this.G = G;
        this.edgeTo = new int[G.V()];
        this.marked = new boolean[G.V()];
        this.onStack = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(i);
            }
        }
    }

    private void dfs(int s) {
        marked[s] = true;
        onStack[s] = true;

        for (int v : G.adj(s)) {
            if (hasCycle()) {
                return;
            }

            if (!marked[v]) {
                edgeTo[v] = s;
                dfs(v);
            } else if (onStack[v]) {
                cycle = new Stack<>();
                for (int x = s; x != v; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(v);
                cycle.push(s);
            }
        }

        onStack[s] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("data/tinyG.txt"));

        Digraph G = new Digraph(sc);
        DirectedCycle directedCycle = new DirectedCycle(G);

        if (directedCycle.hasCycle()) {
            for (int v : directedCycle.cycle()) {
                System.out.print(v + " ");
            }
            System.out.println();
        } else {
            System.out.println("no cycle");
        }
    }

}
