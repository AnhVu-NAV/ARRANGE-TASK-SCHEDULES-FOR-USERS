/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import Graph.Vertex;
import entity.User;
import entity.UserSchedule;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author AnhVu
 */
public class ScheduleManagerSystem {

    public ScheduleManagerSystem() {
    }

    public static final String USERS_FILE = "src/data/users.txt";
    public static Map<String, User> users = new HashMap<>();

    public static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = new User(line);
                users.put(user.getId(), user);
            }
            System.out.println("Users loaded from file.");
        } catch (IOException e) {
            System.out.println("No users file found, starting with an empty user list.");
        }
    }

    public static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users.values()) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    public static void addUser(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(id, name, email, phoneNumber, password);
        users.put(id, newUser);
        saveUsers();

        System.out.println("User added successfully.");
    }

    public static void manageUserTasks(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(userId);

        if (user == null || !user.verifyPassword(password)) {
            System.out.println("User not found or incorrect password.");
            System.out.print("Do you want to add a new user? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                addUser(scanner);
                user = users.get(userId);
            } else {
                System.out.println("Returning to main menu.");
                return;
            }
        }

        UserSchedule userSchedule;
        try {
            userSchedule = UserSchedule.loadFromFile(userId);
            userSchedule.loadTasksFromFile();
            System.out.println("Tasks for user " + userId + ":");
            userSchedule.displaySchedule();
        } catch (IOException e) {
            userSchedule = new UserSchedule(user);
            System.out.println("No tasks found for user " + userId + ".");
        }

        while (true) {
            System.out.println("1. Add new task");
            System.out.println("2. Remove task");
            System.out.println("3. Return to main menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addTask(scanner, userSchedule);
                    break;
                case 2:
                    removeTask(scanner, userSchedule);
                    break;
                case 3:
                    try {
                        userSchedule.saveToFile();
                        userSchedule.saveTasksToFile();
                    } catch (IOException e) {
                        System.err.println("Error saving schedule: " + e.getMessage());
                    }
                    System.out.println("Returning to main menu.");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void addTask(Scanner scanner, UserSchedule userSchedule) {
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();
        if (userSchedule.getTaskMap().containsKey(taskName)) {
            System.out.println("Task with this name already exists. Please choose a different name.");
            return;
        }

        System.out.print("Enter task label: ");
        String taskLabel = scanner.nextLine();
        System.out.print("Enter start date and time (yyyy-MM-dd HH:mm): ");
        String startDateTimeStr = scanner.nextLine();
        System.out.print("Enter end date and time (yyyy-MM-dd HH:mm): ");
        String endDateTimeStr = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime;
        LocalDateTime endTime;

        try {
            startTime = LocalDateTime.parse(startDateTimeStr, formatter);
            endTime = LocalDateTime.parse(endDateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please try again.");
            return;
        }

        if (endTime.isBefore(startTime)) {
            System.out.println("End time cannot be before start time. Please try again.");
            return;
        }

        if (hasConflict(userSchedule, startTime, endTime)) {
            System.out.println("This task conflicts with an existing task. Please choose a different time.");
            return;
        }

        userSchedule.addTask(taskName, taskLabel, startTime, endTime);
        System.out.println("Task added successfully.");
    }

    private static void removeTask(Scanner scanner, UserSchedule userSchedule) {
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();
        userSchedule.removeTask(taskName);
        System.out.println("Task removed successfully.");
    }

    private static boolean hasConflict(UserSchedule userSchedule, LocalDateTime startTime, LocalDateTime endTime) {
        for (Vertex vertex : userSchedule.getScheduleGraph().getVertices()) {
            if (vertex.getStartTime().isBefore(endTime) && startTime.isBefore(vertex.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    public static void exportUserSchedule(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        User user = users.get(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        UserSchedule userSchedule;
        try {
            userSchedule = UserSchedule.loadFromFile(userId);
            userSchedule.saveTasksToFile();
            System.out.println("User schedule exported successfully.");
        } catch (IOException e) {
            System.out.println("No schedule found for user.");
        }
    }
}