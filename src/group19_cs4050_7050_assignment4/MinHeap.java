package group19_cs4050_7050_assignment4;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private List<Edge> heap;

    public MinHeap() {
        this.heap = new ArrayList<>(); //initializes heap as array list
    }

    public void insert(Edge edge) { //method to add an edge to the heap
        heap.add(edge);
        heapifyUp(heap.size() - 1); //make sure to restore property of heap
    }

    public Edge extractMin() { //returns edge with min weight, and removes it
        if (heap.isEmpty()) { //if empty return null
            return null;
        }

        if (heap.size() == 1) { //if size is one, min is the only element
            return heap.remove(0);
        }

        Edge root = heap.get(0); //get min element
        heap.set(0, heap.remove(heap.size() - 1)); //replace with last edge
        heapifyDown(0); //restore heap properties

        return root; //return min edge
    }


    private int parent(int i) { //find parent edge
        return (i - 1) / 2;
    }

    private int leftChild(int i) { //find left child in heap
        return 2 * i + 1;
    }

    private int rightChild(int i) { //find right child in heap
        return 2 * i + 2;
    }

    private void swap(int i, int j) { //method to swap two edges in heap
        Edge temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void heapifyUp(int i) { //method used to restore heap properties
        while (i > 0 && heap.get(parent(i)).weight > heap.get(i).weight) { //loop and swap up until edge is in correct position
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int minIndex = i; //min index is given
        int l = leftChild(i); //find left child index
        int r = rightChild(i); //right child index
        //checks left and right, and swaps down if child is less than current
        if (l < heap.size() && heap.get(l).weight < heap.get(minIndex).weight) {
            minIndex = l;
        }

        if (r < heap.size() && heap.get(r).weight < heap.get(minIndex).weight) {
            minIndex = r;
        }

        if (i != minIndex) { //if we found a new min
            swap(i, minIndex); //swap
            heapifyDown(minIndex); //call again, until we find no more edges to swap
        }
    }



    public boolean isEmpty() { //check if heap is empty, return true if so, false if not
        return heap.isEmpty();
    }




}

