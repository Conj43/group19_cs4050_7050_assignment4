package group19_cs4050_7050_assignment4;

public class Main {

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();

        // Initialize the heap with keys
        int[] keys = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        minHeap.heap_ini(keys, keys.length);

        // Print the initial heap
        System.out.println("Initial Heap:");
        minHeap.printHeap();
        System.out.println();

        // Test in_heap method
        System.out.println("Is element with ID 5 in heap? " + minHeap.in_heap(5));
        System.out.println();

        // Test min_key and min_id methods
        System.out.println("Minimum key: " + minHeap.min_key());
        System.out.println("ID of minimum key: " + minHeap.min_id());
        System.out.println();

        // Test key method
        System.out.println("Key of element with ID 4: " + minHeap.key(4));
        System.out.println();

        // Test decrease_key method
        minHeap.decrease_key(8, 1);
        System.out.println("Heap after decreasing key of element with ID 8 to 1:");
        minHeap.printHeap();
        System.out.println();

        // Test delete_min method
        minHeap.delete_min();
        System.out.println("Heap after deleting minimum key:");
        minHeap.printHeap();

    }

}
