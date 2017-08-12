package org.maciejmarczak.algorithms.graphs;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class TransitiveClosure {

    private final DirectedDFS[] all;

    public TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];
        for (int s = 0; s < G.V(); s++) {
            all[s] = new DirectedDFS(G, Collections.singletonList(s));
        }
    }

    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("data/tinyDG.txt"));

        Digraph G = new Digraph(sc);
        TransitiveClosure tc = new TransitiveClosure(G);

        System.out.println(tc.reachable(11, 0));
    }
}
