/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import Graph.Graph;
import Graph.Vertex;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author AnhVu
 */
public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;
    private Graph scheduleGraph;
    private Map<String, Vertex> taskMap;
    private Map<String, Set<String>> userTasks;

    public Schedule() {
        this.scheduleGraph = new Graph();
        this.taskMap = new HashMap<>();
        this.userTasks = new HashMap<>();
    }

    public void addTask(String userId, String taskName, String taskLabel, LocalDateTime startTime, LocalDateTime endTime) {
        Vertex taskVertex = new Vertex(taskLabel, startTime, endTime);
        this.scheduleGraph.addVertex(taskVertex);
        this.taskMap.put(taskName, taskVertex);
        userTasks.computeIfAbsent(userId, k -> new HashSet<>()).add(taskName);
    }

    public void removeTask(String userId, String taskName) {
        Vertex taskVertex = this.taskMap.remove(taskName);
        if (taskVertex != null) {
            this.scheduleGraph.removeVertex(taskVertex);
        }
        Set<String> tasks = userTasks.get(userId);
        if (tasks != null) {
            tasks.remove(taskName);
        }
    }

    public void addConflict(String task1, String task2) {
        Vertex vertex1 = this.taskMap.get(task1);
        Vertex vertex2 = this.taskMap.get(task2);
        if (vertex1 != null && vertex2 != null) {
            int weight = vertex1.getWeight() + vertex2.getWeight();
            this.scheduleGraph.addEdgeBidirectional(vertex1.getLabel(), vertex2.getLabel(), weight);
        }
    }

    public void displaySchedule() {
        Map<Vertex, Integer> schedule = this.scheduleGraph.colorGraph();
        this.scheduleGraph.displaySchedule(schedule);
    }

    public boolean hasConflict(LocalDateTime startTime, LocalDateTime endTime) {
        for (Vertex vertex : this.scheduleGraph.getVertices()) {
            if (vertex.getStartTime().isBefore(endTime) && startTime.isBefore(vertex.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Vertex> getTaskMap() {
        return taskMap;
    }

    public Graph getScheduleGraph() {
        return scheduleGraph;
    }

    public void displayAllTasks() {
        System.out.println("All tasks:");
        Set<Vertex> visited = new HashSet<>();
        for (Vertex vertex : scheduleGraph.getVertices()) {
            if (!visited.contains(vertex)) {
                dfsDisplayAllTasks(vertex, visited);
            }
        }
    }

    private void dfsDisplayAllTasks(Vertex vertex, Set<Vertex> visited) {
        visited.add(vertex);
        System.out.println("Task Name: " + vertex.getLabel() + ", Start: " + vertex.getStartTime() + ", End: " + vertex.getEndTime());
        for (Vertex neighbor : vertex.getAdjList().keySet()) {
            if (!visited.contains(neighbor)) {
                dfsDisplayAllTasks(neighbor, visited);
            }
        }
    }

    public void displayUserTasks(String userId) {
        Set<String> tasks = userTasks.get(userId);
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("No tasks for user " + userId);
            return;
        }
        for (String taskName : tasks) {
            Vertex task = taskMap.get(taskName);
            if (task != null) {
                System.out.println("Task: " + taskName + ", Label: " + task.getLabel() + ", Start: " + task.getStartTime() + ", End: " + task.getEndTime());
            }
        }
    }

    public void bfs(String startLabel) {
        scheduleGraph.bfs(startLabel);
    }

    public void dfs(String startLabel) {
        scheduleGraph.dfs(startLabel);
    }

    public void dijkstra(String start, String destination) {
        scheduleGraph.dijkstra(start, destination);
    }

    public Graph primJanik(String start) {
        return scheduleGraph.primJanik(start);
    }

    public List<String> getEulerianPathStartingVertices() {
        return scheduleGraph.getEulerianPathStartingVertices();
    }
}