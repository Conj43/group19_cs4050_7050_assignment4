package group19_cs4050_7050_assignment4;

public class Node {
    private double key;
    private int id;

    public Node(double key, int id) {
        this.key = key;
        this.id = id;
    }

    public double getKey() {
        return key;
    }

    public int getId() {
        return id;
    }

    public void setKey(double key) {
        this.key = key;
    }
}
