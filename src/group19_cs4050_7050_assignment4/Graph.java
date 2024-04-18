package group19_cs4050_7050_assignment4;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int v;
    List<List<Edge>> adjList;

    public Graph(int v)
    {
        this.v = v;
        adjList = new ArrayList<>(v+1);
        for(int i = 0; i <= v; i++)
        {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int i, int j, int w)
    {
        adjList.get(i).add(new Edge(i,j,w));
        adjList.get(j).add(new Edge(i,j,w));
    }

    public List<Edge> primMST()
    {
        MinHeap heap = new MinHeap();
        heap.heap_ini(new int[v], v);
        List<Edge> mst = new ArrayList<>();

        boolean[] visited = new boolean[v+1];
        heap.decrease_key(1,0);

        while(heap.heapSize() != 0)
        {
            Element minElement = heap.delete_min();

        }
    }
}
