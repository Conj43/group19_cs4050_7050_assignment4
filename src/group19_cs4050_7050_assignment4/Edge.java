package group19_cs4050_7050_assignment4;

public class Edge implements Comparable<Edge> {
    int i, j; //i is vertex, j is vertex destination, w is weight between them
    double w;

    public Edge(int i, int j, double w) {
        this.i = i;
        this.j = j;
        this.w = w;
    }



    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.w, o.w);
    }
}
