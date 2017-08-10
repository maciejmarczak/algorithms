package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TopologicalSort {

    private Iterable<Integer> order;

    public TopologicalSort(Digraph G) {
        DirectedCycle dc = new DirectedCycle(G);

        if (!dc.hasCycle()) {
            DepthFirstOrder dfo = new DepthFirstOrder(G);
            order = dfo.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("data/DFO.txt"));

        Digraph G = new Digraph(sc);
        TopologicalSort sort = new TopologicalSort(G);

        if (sort.isDAG()) {
            for (int v : sort.order()) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
