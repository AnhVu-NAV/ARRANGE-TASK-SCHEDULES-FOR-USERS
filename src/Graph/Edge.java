
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import java.io.Serializable;

/**
 *
 * @author Tuan
 */
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
