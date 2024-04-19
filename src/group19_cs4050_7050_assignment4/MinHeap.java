package group19_cs4050_7050_assignment4;

public class MinHeap {
    private Node[] heap;
    private int size;

    public MinHeap(){

    }


    //initializes a heap with the array keys of n elements indexed from 1 to n, where
    //key[i] is the key of the element whose id is i.
    public void heap_ini(int[] keys, int n){
        this.heap = new Node[n+1];
        this.size = 0;
        for(int i = 0; i < n; i++){
            insert(i+1, keys[i]);
        }
    }

    public void insert(int id, int key){
        size++;
        heap[size] = new Node(id, key);
        heapUp(size);
    }

    private void heapUp(int index){
        while (index > 1 && heap[index].getKey() < heap[index/2].getKey() ){
            swap(index, index/2);
            index = index/2;
        }
    }

    private void heapDown(int index){
        int smallest = index;
        int left = 2 * index;
        int right = 2 * index + 1;

        if (left <= size && heap[left].getKey() < heap[smallest].getKey()) {
            smallest = left;
        }

        if (right <= size && heap[right].getKey() < heap[smallest].getKey()) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapDown(smallest);
        }
    }

    private void swap(int i, int j){
        Node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    //returns true if the element whose id is id is in the heap
    public boolean in_heap(int id){
        for(int i = 1; i <= size; i++){
            if(heap[i].getId() == id){
                return true;
            }
        }
        return false;
    }

    //returns the minimum key of the heap
    public int min_key(){
        if(size > 0){
            return heap[1].getKey();
        }
        else throw new IllegalStateException("Heap is empty");
    }


    //returns the id of the element with minimum key in the heap
    public int min_id(){
        if(size > 0){
            return heap[1].getId();
        }
        else throw new IllegalStateException("Heap is empty");
    }


    //returns the key of the element whose id is id in the heap
    public int key(int id){
        for(int i = 1; i <= size; i++) {
            if(heap[i].getId() == id){
                return heap[i].getKey(); //retruns key if node found with given id
            }
        }
        return -1; //if not found
    }


    //deletes the element with minimum key from the heap
    public void delete_min(){
        if (size == 0){
            throw new IllegalStateException("Heap is empty");
        }
        else {
            swap(1, size);
            size--;
            heapDown(1);
        }
    }

    private int findIndex(int id){
        for(int i = 1; i <= size; i++) {
            if(heap[i].getId() == id){
                return i; //return index of element with given id
            }
        }
        return -1; //if not found
    }


    // sets the key of the element whose id is id to new_key if its current key
    // is greater than new_key.
    public void decrease_key(int id, int new_key){
        int i = findIndex(id);
        if(i == -1){
            throw new IllegalArgumentException("Node with given id not found.");
        }
        else if (new_key >= heap[i].getKey()) {
            throw new IllegalArgumentException("New key is not smaller than the current key");
        }
        else {
            heap[i].setKey(new_key);
            heapUp(i);
        }
    }

    public void printHeap() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return;
        }

        System.out.println("Heap:");
        for (int i = 1; i <= size; i++) {
            System.out.println("ID: " + heap[i].getId() + ", Key: " + heap[i].getKey());
        }

    }

    /*
    returns the size of the heap
     */
    public int heapSize()
    {
        return size;
    }

    /*
    returns the element stored in the heap by its id
     */
    public Element getElementByID(int id)
    {
        return new Element(heap[id].getId(), heap[id].getKey());
    }
}
