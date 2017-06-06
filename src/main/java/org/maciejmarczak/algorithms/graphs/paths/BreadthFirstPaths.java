package org.maciejmarczak.algorithms.graphs.paths;

import org.maciejmarczak.algorithms.graphs.Graph;

import java.util.LinkedList;
import java.util.Queue;

class BreadthFirstPaths extends Paths {

    BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        marked[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int x : G.adj(v)) {
                if (!marked[x]) {
                    edgeTo[x] = v;
                    marked[x] = true;
                    queue.add(x);
                }
            }
        }
    }
}
