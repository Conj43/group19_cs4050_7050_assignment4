package group19_cs4050_7050_assignment4;

public class Node {
    private int key;
    private int id;

    public Node(int key, int id) {
        this.key = key;
        this.id = id;
    }

    public int getKey() {
        return key;
    }

    public int getId() {
        return id;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
