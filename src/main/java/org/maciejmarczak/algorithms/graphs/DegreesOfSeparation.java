package org.maciejmarczak.algorithms.graphs;

import org.maciejmarczak.algorithms.graphs.paths.BreadthFirstPaths;

import java.io.IOException;
import java.util.Scanner;

public class DegreesOfSeparation {
    public static void main(String[] args) throws IOException {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        String source = args[2];

        Graph G = sg.G();
        if (!sg.contains(source)) {
            System.out.println(source + " not in a graph!");
            System.exit(-1);
        }

        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        Scanner sc = new Scanner(System.in);

        String key = sc.nextLine();
        while (!"q".equals(key)) {
            if (sg.contains(key)) {
                if (bfs.hasPathTo(sg.index(key))) {
                    for (int v : bfs.pathTo(sg.index(key))) {
                        System.out.println("\t" + sg.name(v));
                    }
                } else {
                    System.out.println("Not connected!");
                }
            } else {
                System.out.println("Not in a graph!");
            }
            key = sc.nextLine();
        }
    }
}
