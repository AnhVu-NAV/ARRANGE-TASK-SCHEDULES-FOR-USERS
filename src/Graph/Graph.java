/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Tuan
 */
public class Graph implements Serializable {
    private static final long serialVersionUID = 1L;
    Set<Vertex> vertices;

    public Graph() {
        this.vertices = new HashSet<>();
    }

    public Vertex addVertex(String label) {
        Vertex newV = new Vertex(label, null, null);
        this.vertices.add(newV);
        return newV;
    }

    public void addVertex(Vertex v) {
        this.vertices.add(v);
    }

    public void removeVertex(Vertex v) {
        this.vertices.remove(v);
    }

    public Vertex getVertex(String label) {
        for (Vertex vertex : vertices) {
            if (vertex.label.equals(label)) {
                return vertex;
            }
        }
        return null;
    }

    public void addEdge(String u, String v, int weight) {
        Vertex vertexU = this.getVertex(u);
        Vertex vertexV = this.getVertex(v);
        if (vertexU == null) {
            vertexU = this.addVertex(u);
        }
        if (vertexV == null) {
            vertexV = this.addVertex(v);
        }
        vertexU.adjList.put(vertexV, weight);
    }

    public void addEdgeDirection(String u, String v, int weight) {
        addEdge(u, v, weight);
        addEdge(v, u, weight);
    }
    
    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void display() {
        this.vertices.forEach(vertex -> {
            System.out.print(vertex.label + " -> ");
            vertex.adjList.forEach((adjVertex, weight) -> System.out.print("(" + adjVertex.label + ", " + weight + ") "));
            System.out.println();
        });
    }

    public Map<Vertex, Integer> colorGraph() {
        Map<Vertex, Integer> result = new HashMap<>();
        for (Vertex v : this.vertices) {
            Set<Integer> usedColors = new HashSet<>();
            for (Vertex neighbor : v.adjList.keySet()) {
                if (result.containsKey(neighbor)) {
                    usedColors.add(result.get(neighbor));
                }
            }
            int color = 1;
            while (usedColors.contains(color)) {
                color++;
            }
            result.put(v, color);
        }
        return result;
    }

    public void displaySchedule(Map<Vertex, Integer> schedule) {
        for (Map.Entry<Vertex, Integer> entry : schedule.entrySet()) {
            System.out.println("Task: " + entry.getKey().label + " - Time Slot: " + entry.getValue() +
                               " (Start: " + entry.getKey().getStartTime() + ", End: " + entry.getKey().getEndTime() + ")");
        }
    }

    
}