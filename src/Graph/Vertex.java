/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 *
 * @author vungu
 */
public class Vertex implements Serializable {
    private static final long serialVersionUID = 1L; // Add serialVersionUID
    String label;
    LocalDateTime startTime;
    LocalDateTime endTime;
    HashMap<Vertex, Integer> adjList;

    public Vertex(String label, LocalDateTime startTime, LocalDateTime endTime) {
        this.label = label;
        this.startTime = startTime;
        this.endTime = endTime;
        this.adjList = new HashMap<>();
    }

    public String getLabel() {
        return label;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public HashMap<Vertex, Integer> getAdjList() {
        return adjList;
    }
}
