package group19_cs4050_7050_assignment4;

import java.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>(); //initialize as a new has map
    }

    public void addVertex(int vertex) { //adds vertex to the graph
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<>()); //make sure not already added, and if not create it with a new array list, which holds edges adjacent
        }
    }
    //adds edge to graph
    public void addEdge(int i, int j, double weight) {
        if (!adjacencyList.containsKey(i)) { //check if vertex is already in the graph
            addVertex(i); //if not, add it
        }
        if (!adjacencyList.containsKey(j)) { //check if vertex is already in the graph
            addVertex(j); //if not, add it
        }

        Edge edgeIJ = new Edge(i, j, weight); //create edge, going both ways because it is not directed
        Edge edgeJI = new Edge(j, i, weight);

        adjacencyList.get(i).add(edgeIJ); //add both to the adjacent list of their respective vertices
        adjacencyList.get(j).add(edgeJI);
    }

    public List<Edge> getAdjacentEdges(int vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>()); //returns edges adjacent to given vertex
    }



    //method to nicely print graph
    public void printGraph() {
        for (Map.Entry<Integer, List<Edge>> entry : adjacencyList.entrySet()) { //get adjacency list for each vertex
            int vertex = entry.getKey();
            List<Edge> edges = entry.getValue();

            System.out.print("Vertex " + vertex + " is connected to: "); //print out the vertex, what is connected to and the corresponding weight
            for (Edge edge : edges) {
                System.out.print(edge.j + "(" + edge.weight + ") "); //looks like 'Vertex 1 is connected to: 2(weight) 10(weight)' this is just an example where weight would be a double
            }
            System.out.println();
        }
    }

    //method uses prims algorithm to build mst (we used a list of edges)
    public List<Edge> primMST(Graph graph) {
        List<Edge> mstEdges = new ArrayList<>(); // list to keep track of order
        MinHeap heap = new MinHeap(); // initialize empty heap

        Set<Integer> visited = new HashSet<>(); // create set to keep track of visited vertices

        visited.add(1); //start from vertex 1

        List<Edge> adjacentEdges = graph.getAdjacentEdges(1); //get all adjacent to first vertex
        for (Edge edge : adjacentEdges) { //create heap of adjacent
            heap.insert(edge);
        }

        while (!heap.isEmpty()) {
            Edge minEdge = heap.extractMin(); //extract min edge

            int u = minEdge.i; //current vertex
            int v = minEdge.j; //vertex associated with edge

            if (visited.contains(u) && visited.contains(v)) { //check if both have been visited
                continue;
            }

            mstEdges.add(minEdge); //add to mst

            int newVertex = visited.contains(u) ? v : u;
            visited.add(newVertex); //mark the vertices in the set

            adjacentEdges = graph.getAdjacentEdges(newVertex); //find adjacent for new vertex
            for (Edge edge : adjacentEdges) {
                if (!visited.contains(edge.j)) {
                    heap.insert(edge); //loop through, and if edge has not been added to the heap, add it
                }
            }
        }

        return mstEdges; //return list of edges, which is mst
    }


    //method to print the mst nicely
    public void printMST(List<Edge> mst) {
        System.out.println("Minimum Spanning Tree (MST):");
        double totalWeight = 0; //keep track of total weight

        for (Edge edge : mst) {
            System.out.println("Vertex " + edge.i + " -- Vertex " + edge.j + " : Weight = " + edge.weight); //print current edge in mst
            totalWeight += edge.weight;
        }

        System.out.println("Total Weight of MST: " + totalWeight); //print total weight
    }


}
