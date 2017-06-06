package org.maciejmarczak.algorithms.graphs.paths;

import org.maciejmarczak.algorithms.graphs.Graph;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PathsClient {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(
                new File("data/tinyCG.txt"));

        Graph G = new Graph(sc);
        int s = 0;

        Paths paths = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            System.out.print(s + " to " + v + ": ");
            if (paths.hasPathTo(v)) {
                for (int x : paths.pathTo(v)) {
                    if (x == s) System.out.print(x);
                    else System.out.print(x + "-");
                }
            }
            System.out.println();
        }
    }
}
