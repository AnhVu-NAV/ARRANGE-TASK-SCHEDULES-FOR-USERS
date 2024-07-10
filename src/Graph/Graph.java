
package Graph;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


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
    
    public void removeEdge(String u, String v) {
        Vertex vertexU = this.getVertex(u);
        Vertex vertexV = this.getVertex(v);
        if (vertexU == null || vertexV == null) {
            return;
        }
        vertexU.adjList.remove(vertexV);
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
    
   // Depth First Search (DFS)
    public void DFS(String startLabel) {
        Vertex startVertex = getVertex(startLabel);
        if (startVertex == null) return;
        Set<Vertex> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(Vertex vertex, Set<Vertex> visited) {
        visited.add(vertex);
        System.out.print(vertex.label + " ");
        for (Vertex neighbor : vertex.adjList.keySet()) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }

    // Breadth First Search (BFS)
    public void BFS(String startLabel) {
        Vertex startVertex = getVertex(startLabel);
        if (startVertex == null) return;
        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.add(startVertex);
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            System.out.print(vertex.label + " ");
            for (Vertex neighbor : vertex.adjList.keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    // Dijkstra's Algorithm for Shortest Path
    public Map<Vertex, Integer> dijkstra(String startLabel) {
        Vertex startVertex = getVertex(startLabel);
        if (startVertex == null) return null;

        Map<Vertex, Integer> distances = new HashMap<>();
        for (Vertex v : vertices) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            for (Map.Entry<Vertex, Integer> neighborEntry : current.adjList.entrySet()) {
                Vertex neighbor = neighborEntry.getKey();
                int edgeWeight = neighborEntry.getValue();
                int newDist = distances.get(current) + edgeWeight;
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(neighbor);
                }
            }
        }
        return distances;
    }

    // Cycle Detection using DFS
    public boolean hasCycle() {
        Set<Vertex> visited = new HashSet<>();
        Set<Vertex> recStack = new HashSet<>();
        for (Vertex vertex : vertices) {
            if (hasCycleUtil(vertex, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleUtil(Vertex vertex, Set<Vertex> visited, Set<Vertex> recStack) {
        if (recStack.contains(vertex)) {
            return true;
        }
        if (visited.contains(vertex)) {
            return false;
        }
        visited.add(vertex);
        recStack.add(vertex);
        for (Vertex neighbor : vertex.adjList.keySet()) {
            if (hasCycleUtil(neighbor, visited, recStack)) {
                return true;
            }
        }
        recStack.remove(vertex);
        return false;
    } 
}