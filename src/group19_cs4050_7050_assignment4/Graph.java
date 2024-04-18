package group19_cs4050_7050_assignment4;

import java.util.*;


public class Graph {
    private int size = 0;

    private Map<Integer, List<Edge>> neighbors;
    public Graph() {
        this.neighbors = new HashMap<>();
    }

    private void addVertex(int v) {
        if(!neighbors.containsKey(v)) {
            neighbors.put(v, new ArrayList<>());
            size++;
        }
    }

    public void addEdge(int current, int destination, double weight) {
        if(!neighbors.containsKey(current)) {
            addVertex(current);
        }
        if(!neighbors.containsKey(destination)) {
            addVertex(destination);
        }
        neighbors.get(current).add(new Edge(current, destination, weight));
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<Edge>> entry : neighbors.entrySet()) {
            int vertex = entry.getKey();
            List<Edge> edges = entry.getValue();

            System.out.print("Vertex " + vertex + " is connected to: ");
            for (Edge edge : edges) {
                System.out.print(edge.j + "(" + edge.w + ") ");
            }
            System.out.println();
        }
    }

    public int getSize(){
        return size;
    }


}
