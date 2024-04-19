package group19_cs4050_7050_assignment4;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int v; //# of verticies
    List<List<Edge>> adjList; //adjacency list of the graph

    public Graph(int v)
    {
        this.v = v;
        adjList = new ArrayList<>(v+1);
        for(int i = 0; i <= v; i++)
        {
            adjList.add(new ArrayList<>()); //inits the adjacency list
        }
    }

    /*
    adds an edge to the graph
     */
    public void addEdge(int i, int j, int w)
    {
        adjList.get(i).add(new Edge(i,j,w)); // adds an edge to the adjacency list
        adjList.get(j).add(new Edge(j,i,w)); // adds the reverse edge
    }

    /*
    method for Prims minimum spanning tree graph
     */
    public List<Edge> primMST()
    {
        MinHeap heap = new MinHeap(); //creates the heap
        heap.heap_ini(new int[v], v); //inits it with keys
        List<Edge> mst = new ArrayList<>(); //inits the MST

        boolean[] visited = new boolean[v+1]; //array to track vertices that have been visited
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
            for(Edge neighbor : adjList.get(u))
            {
                if (!visited[neighbor.j] && neighbor.w < heap.key(neighbor.j))
                {
                    heap.decrease_key(neighbor.j, neighbor.w);
                }
            }

        }
        return mst;
    }
}
