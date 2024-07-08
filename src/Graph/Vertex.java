/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 *
 * @author vungu
 */
public class Vertex implements Serializable {
    private static final long serialVersionUID = 1L;
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
    
    // Constructor from String
    public Vertex(String data) {
        String[] parts = data.split(",");
        this.label = parts[0];
        this.startTime = LocalDateTime.parse(parts[1]);
        this.endTime = LocalDateTime.parse(parts[2]);
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

    public int getWeight() {
        return (int) Duration.between(startTime, endTime).toMinutes();
    }
    
    @Override
    public String toString() {
        return label + "," + startTime.toString() + "," + endTime.toString();
    }
}