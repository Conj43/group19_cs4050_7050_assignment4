package group19_cs4050_7050_assignment4;

import java.util.*;
import java.io.*;

public class Main {

    public static final String filePath = "resources/graph";

    public static Graph readFile(){
        Graph graph = new Graph();
        try (Scanner scanner = new Scanner(new File(filePath))){
            int size = scanner.nextInt();
            System.out.println("Number of vertices: " + size);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] nums = line.split("\\s+");
                if (nums.length == 3) {
                    try {
                        int current = Integer.parseInt(nums[0]);
                        int destination = Integer.parseInt(nums[1]);
                        double weight = Double.parseDouble(nums[2]);
                        graph.addEdge(current, destination, weight);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                    }

                }
            }
            return graph;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {


        Graph g = readFile();
        if (g == null) {
            System.out.println("Graph is null");
        }
        else {
            g.printGraph();
            List<Edge> mst = g.primMST(g);
            if (mst == null) {
                System.out.println("MST is null");
            }
            else g.printMST(mst);
        }








    }

}
