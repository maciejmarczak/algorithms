package org.maciejmarczak.algorithms.strings;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class KeyIndexCount {

    public static void sort(List<Item> items, int maxKeyValue) {
        final int[] count = new int[maxKeyValue + 2];
        final Item[] aux = new Item[items.size()];

        // Compute frequency count of each key value
        for (Item item : items) {
            count[item.getSection() + 1]++;
        }

        // Transform counts to indices
        for (int r = 0; r < maxKeyValue; r++) {
            count[r + 1] += count[r];
        }

        // Distribute the data
        for (Item item : items) {
            aux[count[item.getSection()]++] = item;
        }

        // Copy back
        for (int i = 0; i < aux.length; i++) {
            items.set(i, aux[i]);
        }
    }

    private static void printItems(List<Item> items) {
        for (Item item : items) {
            System.out.format("%15s %d\n", item.getName(), item.getSection());
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("data/stringNames.txt"));

        List<Item> items = new LinkedList<>();
        while (sc.hasNextLine()) {
            items.add(new Item(sc.next(), sc.nextInt()));
        }
        sort(items, 4);
        printItems(items);
    }
}

class Item {
    private final String name;
    private final int section;

    Item(String name, int section) {
        this.name = name;
        this.section = section;
    }

    String getName() {
        return name;
    }

    int getSection() {
        return section;
    }
}