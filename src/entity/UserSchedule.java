
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import Graph.Graph;
import Graph.Vertex;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tuan
 */
public class UserSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    private User user;
    private Graph scheduleGraph;
    private Map<String, Vertex> taskMap;

    public UserSchedule(User user) {
        this.user = user;
        this.scheduleGraph = new Graph();
        this.taskMap = new HashMap<>();
    }

    public User getUser() {
        return user;
    }

    public Graph getScheduleGraph() {
        return scheduleGraph;
    }

    public Map<String, Vertex> getTaskMap() {
        return taskMap;
    }

    public void addTask(String taskName, String taskLabel, LocalDateTime startTime, LocalDateTime endTime) {
        Vertex taskVertex = new Vertex(taskLabel, startTime, endTime);
        this.scheduleGraph.addVertex(taskVertex);
        this.taskMap.put(taskName, taskVertex);
        saveTasksToFile();
    }

    public void removeTask(String taskName) {
        Vertex taskVertex = this.taskMap.remove(taskName);
        if (taskVertex != null) {
            this.scheduleGraph.removeVertex(taskVertex);
        }
        saveTasksToFile();
    }

    public void addConflict(String task1, String task2, int weight) {
        Vertex vertex1 = this.taskMap.get(task1);
        Vertex vertex2 = this.taskMap.get(task2);
        if (vertex1 != null && vertex2 != null) {
            this.scheduleGraph.addEdgeDirection(vertex1.getLabel(), vertex2.getLabel(), weight);
        }
        saveTasksToFile();
    }

    public void displaySchedule() {
        Map<Vertex, Integer> schedule = this.scheduleGraph.colorGraph();
        System.out.println("Schedule for " + user.getName() + ":");
        this.scheduleGraph.displaySchedule(schedule);
    }

    public void updateTask(String taskName, String newLabel, LocalDateTime newStartTime, LocalDateTime newEndTime) {
        Vertex taskVertex = this.taskMap.get(taskName);
        if (taskVertex != null) {
            taskVertex.setLabel(newLabel);
            taskVertex.setStartTime(newStartTime);
            taskVertex.setEndTime(newEndTime);
            if (!taskName.equals(newLabel)) {
                this.taskMap.remove(taskName);
                this.taskMap.put(newLabel, taskVertex);
            }
            saveTasksToFile();
        } else {
            System.out.println("Task '" + taskName + "' not found.");
        }
    }

    public void saveToFile() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(user.getId() + ".txt"))) {
            out.writeObject(this);
        }
    }

    public static UserSchedule loadFromFile(String userId) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(userId + ".txt"))) {
            return (UserSchedule) in.readObject();
        }
    }

    public void saveTasksToFile() {
        String fileName = "taskbyuser" + user.getId() + ".txt";
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            scheduleGraph.getVertices().forEach(vertex -> {
                out.println("Task: " + vertex.getLabel()
                        + " (Start: " + vertex.getStartTime()
                        + ", End: " + vertex.getEndTime() + ")");
            });
        } catch (IOException e) {
            System.err.println("Error writing tasks to file: " + e.getMessage());
        }
    }

    public void readTasksFromFile() {
        String fileName = "taskbyuser" + user.getId() + ".txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try (BufferedReader read = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Tasks from file:");
            while ((line = read.readLine()) != null) {
                System.out.println(line);
                String label = line.substring(line.indexOf("Task: "), line.indexOf(" (Start:"));
                String startTimeStr = line.substring(line.indexOf("Start: "), line.indexOf(", End:"));
                String endTimeStr = line.substring(line.indexOf("End: "), line.length() - 1);

                LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
                LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);

                Vertex taskVertex = new Vertex(label, startTime, endTime);
                this.scheduleGraph.addVertex(taskVertex);
                this.taskMap.put(label, taskVertex);
            }

        } catch (IOException e) {
            System.err.println("Error reading tasks from file: " + e.getMessage());
        }
    }
}
