package group19_cs4050_7050_assignment4;

import java.util.*;
import java.io.*;

public class Main {

    public static final String filePath = "resources/graph"; //path to input graph

    //method to read input file and create graph using it
    public static Graph readFile(){
        Graph graph = new Graph();
        try (Scanner scanner = new Scanner(new File(filePath))){ //initialize scanner of file path
            int size = scanner.nextInt();
            System.out.println("Number of vertices: " + size); //print top number, and get it out of the way
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); //read each line
                String[] nums = line.split("\\s+"); //split it up by the spaces
                if (nums.length == 3) {
                    try {
                        int current = Integer.parseInt(nums[0]); //first is current vertex
                        int destination = Integer.parseInt(nums[1]); //second is adjacent vertex
                        double weight = Double.parseDouble(nums[2]); //third is weight between vertices
                        graph.addEdge(current, destination, weight); //add edge to graph
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Invalid input"); //just to make sure if we get an error, we know why
                    }

                }
            }
            return graph; //if this worked, we return the graph

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null; //if it didnt work, return null
    }



    public static void main(String[] args) {


        Graph g = readFile(); //initialize a graph using input file
        if (g == null) {
            System.out.println("Graph is null"); //make sure g is not null
        }
        else {
            g.printGraph(); //print the graph and all edges with weights
            List<Edge> mst = g.primMST(g); //find the prim's mst
            if (mst == null) {
                System.out.println("MST is null"); //make sure mst is not null
            }
            else g.printMST(mst); //print out the mst in order
        }

    }

}
