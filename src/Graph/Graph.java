/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author AnhVu
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

    public void addEdgeBidirectional(String u, String v, int weight) {
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

    public void bfs(String startLabel) {
        Vertex startNode = this.getVertex(startLabel);
        if (startNode == null) {
            System.out.println("Start vertex not found");
            return;
        }

        ArrayQueue<Vertex> queue = new ArrayQueue<>(1000);
        Set<Vertex> visited = new HashSet<>();

        queue.enqueue(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            Vertex current = queue.dequeue();
            System.out.print(current.label + " ");

            current.adjList.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.comparing(Vertex::getLabel)))
                    .forEach(entry -> {
                        Vertex neighbor = entry.getKey();
                        if (!visited.contains(neighbor)) {
                            queue.enqueue(neighbor);
                            visited.add(neighbor);
                        }
                    });
        }
        System.out.println();
    }

    public void dfs(String startLabel) {
        Vertex startNode = this.getVertex(startLabel);
        if (startNode == null) {
            System.out.println("Start vertex not found");
            return;
        }

        ArrayStack<Vertex> stack = new ArrayStack<>(1000);
        Set<Vertex> visited = new HashSet<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            if (!visited.contains(current)) {
                System.out.print(current.label + " ");
                visited.add(current);

                current.adjList.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey(Comparator.comparing(Vertex::getLabel).reversed()))
                        .forEach(entry -> stack.push(entry.getKey()));
            }
        }
        System.out.println();
    }

    public void dijkstra(String start, String destination) {
        Map<Vertex, Integer> distance = new HashMap<>();
        this.vertices.forEach(vertex -> distance.put(vertex, Integer.MAX_VALUE));
        Map<Vertex, Vertex> previous = new HashMap<>();
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));
        Vertex startVertex = this.getVertex(start);
        if (startVertex == null) {
            System.out.println("Start vertex not found");
            return;
        }
        distance.put(startVertex, 0);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            for (Map.Entry<Vertex, Integer> entry : current.adjList.entrySet()) {
                Vertex neighbor = entry.getKey();
                int newDist = distance.get(current) + entry.getValue();
                if (newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        Vertex destinationVertex = this.getVertex(destination);
        if (destinationVertex == null) {
            System.out.println("Destination vertex not found");
            return;
        }
        List<Vertex> path = getPath(previous, startVertex, destinationVertex);
        String pathStr = path.stream().map(Vertex::getLabel).collect(Collectors.joining(" -> "));
        System.out.println(pathStr);
    }

    private List<Vertex> getPath(Map<Vertex, Vertex> previous, Vertex startVertex, Vertex destinationVertex) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex at = destinationVertex; at != null; at = previous.get(at)) {
            path.add(at);
            if (at.equals(startVertex)) {
                Collections.reverse(path);
                return path;
            }
        }
        return Collections.emptyList();
    }

    public Graph primJanik(Graph g, String start) {
        Graph mst = new Graph();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        Set<Vertex> visited = new HashSet<>();
        Vertex startVertex = g.getVertex(start);
        if (startVertex == null) {
            System.out.println("Start vertex not found");
            return mst;
        }
        visited.add(startVertex);
        startVertex.adjList.forEach((adjVertex, weight) -> priorityQueue.add(new Edge(startVertex, adjVertex, weight)));

        while (!priorityQueue.isEmpty()) {
            Edge minEdge = priorityQueue.poll();
            if (!visited.contains(minEdge.to)) {
                mst.addEdgeBidirectional(minEdge.from.label, minEdge.to.label, minEdge.weight);
                visited.add(minEdge.to);
                minEdge.to.adjList.forEach((adjVertex, weight) -> {
                    if (!visited.contains(adjVertex)) {
                        priorityQueue.add(new Edge(minEdge.to, adjVertex, weight));
                    }
                });
            }
        }
        return mst;
    }

    public List<String> getEulerianPathStartingVertices(Graph graph) {
        List<String> startingVertices = new ArrayList<>();
        List<String> startVerticePath = new ArrayList<>();
        int oddDegreeCount = 0;

        for (Vertex vertex : graph.vertices) {
            startingVertices.add(vertex.label);
            if (vertex.adjList.size() % 2 != 0) {
                oddDegreeCount++;
                startVerticePath.add(vertex.label);
            }
        }

        if (oddDegreeCount == 0) {
            System.out.println("CIRCLE");
            return startingVertices;
        } else if (oddDegreeCount == 2) {
            System.out.println("PATH");
            return startVerticePath;
        } else {
            return new ArrayList<>();
        }
    }
}