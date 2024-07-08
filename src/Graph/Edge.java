
package Graph;

import java.io.Serializable;

public class Edge implements Serializable {
    private static final long serialVersionUID = 1L; // Add serialVersionUID
    Vertex from;
    Vertex to;
    int weight;

    public Edge(Vertex from, Vertex to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
