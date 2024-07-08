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
import java.util.Map;
/**
 *
 * @author AnhVu
 */
public class UserSchedule implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user;
    private Graph scheduleGraph;
    private Map<String, Vertex> taskMap;

    public UserSchedule() {
    }

    public UserSchedule(User user) {
        this.user = user;
        this.scheduleGraph = new Graph();
        this.taskMap = new HashMap<>();
    }
    
    // Constructor from String
    public UserSchedule(String data) {
        String[] parts = data.split(";");
        this.user = new User(parts[0]); // Assuming User has a constructor from String
        this.scheduleGraph = new Graph(); // Initialize graph, you might need to add vertices/edges
        this.taskMap = new HashMap<>();
        // Additional initialization as needed
    }
    
    @Override
    public String toString() {
        return user.getId() + ";" + taskMap.toString() + ";" + scheduleGraph.toString();
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

    public void addConflict(String task1, String task2) {
        Vertex vertex1 = this.taskMap.get(task1);
        Vertex vertex2 = this.taskMap.get(task2);
        if (vertex1 != null && vertex2 != null) {
            int weight = vertex1.getWeight() + vertex2.getWeight(); // Example weight calculation
            this.scheduleGraph.addEdgeBidirectional(vertex1.getLabel(), vertex2.getLabel(), weight);
        }
        saveTasksToFile();
    }

    public void displaySchedule() {
        Map<Vertex, Integer> schedule = this.scheduleGraph.colorGraph();
        System.out.println("Schedule for " + user.getName() + ":");
        this.scheduleGraph.displaySchedule(schedule);
    }
    
    public void shortestPath(String task1, String task2) {
        scheduleGraph.dijkstra(taskMap.get(task1).getLabel(), taskMap.get(task2).getLabel());
    }
    
    public void saveToFile() throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/dataUser/" + user.getId() + "_schedule.txt"))) {
        // Giả sử `this` có phương thức `toString` trả về chuỗi dữ liệu cần lưu
        writer.write(this.toString());
    }
}

public void saveTasksToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/dataUser/taskUser" + user.getId() + ".txt"))) {
        for (Map.Entry<String, Vertex> entry : taskMap.entrySet()) {
            writer.write(entry.getKey() + "=" + entry.getValue().toString());
            writer.newLine();
        }
    } catch (IOException e) {
        System.err.println("Error writing tasks to file: " + e.getMessage());
    }
}

    public static UserSchedule loadFromFile(String userId) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/dataUser/" + userId + "_schedule.txt"))) {
        // Giả sử có một constructor hoặc phương thức từ chuỗi để tạo đối tượng UserSchedule
        String line = reader.readLine();
        return new UserSchedule(line);
    }
}

public void loadTasksFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/dataUser/taskUser" + user.getId() + ".txt"))) {
        taskMap = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("=");
            String key = parts[0];
            Vertex task = new Vertex(parts[1]); // Giả sử Vertex có constructor từ chuỗi
            taskMap.put(key, task);
            scheduleGraph.addVertex(task);
        }
    } catch (IOException e) {
        System.err.println("Error reading tasks from file: " + e.getMessage());
    }
}

//    public void saveToFile() throws IOException {
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/data/" + user.getId() + "_schedule.dat"))) {
//            out.writeObject(this);
//        }
//    }
//
//    public static UserSchedule loadFromFile(String userId) throws IOException, ClassNotFoundException {
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/data/" + userId + "_schedule.dat"))) {
//            return (UserSchedule) in.readObject();
//        }
//    }
//
//    public void saveTasksToFile() {
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/data/taskUser" + user.getId() + ".dat"))) {
//            out.writeObject(taskMap);
//        } catch (IOException e) {
//            System.err.println("Error writing tasks to file: " + e.getMessage());
//        }
//    }
//
//    public void loadTasksFromFile() {
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/data/taskUser" + user.getId() + ".dat"))) {
//            taskMap = (Map<String, Vertex>) in.readObject();
//            taskMap.values().forEach(task -> scheduleGraph.addVertex(task));
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Error reading tasks from file: " + e.getMessage());
//        }
//    }
}