
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import Graph.Vertex;
import entity.User;
import entity.UserSchedule;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
 * @author Tuan
 */
public class ScheduleManagerSystem {

    public ScheduleManagerSystem() {
    }
    
    public static final String USERS_FILE = "users.txt";
    public static Map<String, User> users = new HashMap<>();
    
    public static void loadUsers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (Map<String, User>) in.readObject();
            System.out.println("Users loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No users file found, starting with an empty user list.");
        }
    }

    public static void saveUsers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            out.writeObject(users);
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
        saveUserToFile(newUser);

        System.out.println("User added successfully.");
    }

    public static void saveUserToFile(User user) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error saving user to file: " + e.getMessage());
        }
    }

    public static void manageUserTasks(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(userId);

        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("User not found or incorrect password.");
            System.out.print("Do you want to add a new user? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                addUser(scanner);
                user = users.get(userId); // Reload the newly added user
            } else {
                System.out.println("Returning to main menu.");
                return;
            }
        }

        UserSchedule userSchedule;
        try {
            userSchedule = UserSchedule.loadFromFile(userId);
            System.out.println("Tasks for user " + userId + ":");
            userSchedule.displaySchedule();
        } catch (IOException | ClassNotFoundException e) {
            userSchedule = new UserSchedule(user);
            System.out.println("No tasks found for user " + userId + ".");
        }

        while (true) {
            System.out.print("Do you want to add a new task? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                addTask(scanner, userSchedule);
            } else {
                try {
                    userSchedule.saveToFile();
                } catch (IOException e) {
                    System.err.println("Error saving schedule: " + e.getMessage());
                }
                System.out.println("Returning to main menu.");
                return;
            }
        }
    }

    public static void addTask(Scanner scanner, UserSchedule userSchedule) {
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

    public static boolean hasConflict(UserSchedule userSchedule, LocalDateTime startTime, LocalDateTime endTime) {
        for (Vertex vertex : userSchedule.getScheduleGraph().getVertices()) {
            if (vertex.getStartTime().isBefore(endTime) && startTime.isBefore(vertex.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    public static void removeTask(Scanner scanner, UserSchedule userSchedule) {
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();
        userSchedule.removeTask(taskName);
        System.out.println("Task removed successfully.");
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
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No schedule found for user.");
            return;
        }

        userSchedule.saveTasksToFile();
        System.out.println("User schedule exported successfully.");
    }
}
