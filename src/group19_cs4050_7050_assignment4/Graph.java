package group19_cs4050_7050_assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        neighbors.get(destination).add(new Edge(destination, current, weight));
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

    public List<Edge> primMST(Graph graph)
    {

        MinHeap heap = new MinHeap(); //creates the heap

        double[] keys = new double[graph.getSize()];
        int[] ids = new int[graph.getSize()];

        int i = 0;
        for (Map.Entry<Integer, List<Edge>> entry : neighbors.entrySet()) {
            int vertex = entry.getKey();
            ids[i] = vertex;
            keys[i] = Double.MAX_VALUE;
            i++;
        }


        heap.heap_ini(keys, ids, graph.getSize()); //inits it with keys
//        heap.printHeap();

        List<Edge> mst = new ArrayList<>(); //inits the MST

        boolean[] visited = new boolean[graph.getSize()+1]; //array to track vertices that have been visited
        heap.decrease_key(1,0); //inits the key of source vertex

        //performs prims algorithm until the heap is empty
        while(heap.heapSize() != 0)
        {

            Node minNode = heap.getNodeById(heap.min_id()); //get min element from the heap
            heap.delete_min(); //deletes the min
            int u = minNode.getId(); //id of current vertex

            if(visited[u]) //if the vertex has already been visited, skip further processing
            {
                continue;
            }

            visited[u] = true; //mark vertex as visited
            double smallest = Double.MAX_VALUE;
            int ver = 0;
            int to = 0;

            for (Edge edge : graph.neighbors.get(u)) {
                int v = edge.j;
                if (!visited[v] && edge.w < heap.key(v)) {

                    mst.add(new Edge(u, v, edge.w));
                    heap.decrease_key(v,  edge.w);
                }
            }



        }
        return mst;
    }



    public void printMST(List<Edge> mst) {
        System.out.println("Minimum Spanning Tree (MST):");
        double totalWeight = 0;

        for (Edge edge : mst) {
            System.out.println("Vertex " + edge.i + " -- Vertex " + edge.j + " : Weight = " + edge.w);
            totalWeight += edge.w;
        }

        System.out.println("Total Weight of MST: " + totalWeight);
    }


}
