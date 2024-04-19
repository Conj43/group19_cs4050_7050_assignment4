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
    public List<Edge> primMST()
    {
        MinHeap heap = new MinHeap(); //creates the heap
        heap.heap_ini(new int[getSize()],getSize()); //inits it with keys
        List<Edge> mst = new ArrayList<>(); //inits the MST

        boolean[] visited = new boolean[getSize()+1]; //array to track vertices that have been visited
        heap.decrease_key(1,0); //inits the key of source vertex

        //performs prims algorithm until the heap is empty
        while(heap.heapSize() != 0)
        {
            Element minElement = heap.getElementByID(heap.min_id()); //get min element from the heap
            heap.delete_min(); //deletes the min
            int u = minElement.id; //id of current vertex

            if(visited[u]) //if the vertex has already been visited, skip further processing
            {
                continue;
            }

            visited[u] = true; //mark vertex as visited
            //adds the edge to the MST if its not the source
            if(minElement.key != 0)
            {
                mst.add(new Edge(minElement.id, minElement.key, heap.key(u)));
            }

            //updates the keys of adjacent neighbors
            for(Edge neighbor : neighbors.get(u))
            {
                if (!visited[neighbor.j] && neighbor.w < heap.key(neighbor.j))
                {
                    heap.decrease_key(neighbor.j, (int) neighbor.w);
                }
            }

        }
        return mst;
    }

}
