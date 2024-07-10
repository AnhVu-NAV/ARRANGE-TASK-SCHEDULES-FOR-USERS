/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Graph.Graph;
import entity.User;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import manager.ScheduleManagerSystem;
/**
 *
 * @author AnhVu
 */
public class Main {
    public static void main(String[] args) {
        ScheduleManagerSystem manager = new ScheduleManagerSystem();
        manager.loadUsers();
        Scanner scanner = new Scanner(System.in);

        // Tạo dữ liệu mẫu
        createSampleData(manager);

        while (true) {
            System.out.println("1. Add new user");
            System.out.println("2. Manage tasks");
            System.out.println("3. Display all tasks");
            System.out.println("4. Display user tasks");
            System.out.println("5. Display schedule");
            System.out.println("6. BFS");
            System.out.println("7. DFS");
            System.out.println("8. Dijkstra");
            System.out.println("9. Prim's MST");
            System.out.println("10. Eulerian Path");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    manager.addUser(scanner);
                    break;
                case 2:
                    manager.manageTasks(scanner);
                    break;
                case 3:
                    manager.getSchedule().displayAllTasks();
                    break;
                case 4:
                    System.out.print("Enter user ID: ");
                    String userId = scanner.nextLine();
                    manager.getSchedule().displayUserTasks(userId);
                    break;
                case 5:
                    manager.getSchedule().displaySchedule();
                    break;
                case 6:
                    System.out.print("Enter start label: ");
                    String startLabel = scanner.nextLine();
                    manager.getSchedule().bfs(startLabel);
                    break;
                case 7:
                    System.out.print("Enter start label: ");
                    startLabel = scanner.nextLine();
                    manager.getSchedule().dfs(startLabel);
                    break;
                case 8:
                    System.out.print("Enter start label: ");
                    String start = scanner.nextLine();
                    System.out.print("Enter destination label: ");
                    String destination = scanner.nextLine();
                    manager.getSchedule().dijkstra(start, destination);
                    break;
                case 9:
                    System.out.print("Enter start label: ");
                    startLabel = scanner.nextLine();
                    Graph mst = manager.getSchedule().primJanik(startLabel);
                    mst.display();
                    break;
                case 10:
                    List<String> eulerianPathVertices = manager.getSchedule().getEulerianPathStartingVertices();
                    System.out.println("Eulerian Path Starting Vertices: " + eulerianPathVertices);
                    break;
                case 11:
                    manager.saveUsers();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void createSampleData(ScheduleManagerSystem manager) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Thêm người dùng
        User user1 = new User("u1", "Alice", "alice@example.com", "1234567890", "password1");
        User user2 = new User("u2", "Bob", "bob@example.com", "0987654321", "password2");

        ScheduleManagerSystem.users.put(user1.getId(), user1);
        ScheduleManagerSystem.users.put(user2.getId(), user2);

        // Thêm nhiệm vụ cho người dùng
        manager.getSchedule().addTask("u1", "task1", "code", LocalDateTime.parse("2023-07-11 09:00", formatter), LocalDateTime.parse("2023-07-11 10:00", formatter));
        manager.getSchedule().addTask("u1", "task2", "meet", LocalDateTime.parse("2023-07-11 10:30", formatter), LocalDateTime.parse("2023-07-11 11:30", formatter));
        manager.getSchedule().addTask("u2", "task3", "review", LocalDateTime.parse("2023-07-11 11:00", formatter), LocalDateTime.parse("2023-07-11 12:00", formatter));
        manager.getSchedule().addTask("u2", "task4", "code", LocalDateTime.parse("2023-07-11 13:00", formatter), LocalDateTime.parse("2023-07-11 14:00", formatter));
        manager.getSchedule().addTask("u1", "task5", "meet", LocalDateTime.parse("2023-07-11 14:30", formatter), LocalDateTime.parse("2023-07-11 15:30", formatter));

        // Thêm xung đột giữa các nhiệm vụ
        manager.getSchedule().addConflict("task1", "task3");
        manager.getSchedule().addConflict("task2", "task4");
    }
}