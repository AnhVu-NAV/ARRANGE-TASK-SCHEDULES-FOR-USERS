/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import Graph.Vertex;
import entity.User;
import entity.Schedule;
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
import java.util.Set;

/**
 *
 * @author AnhVu
 */
public class ScheduleManagerSystem {

    public static final String USERS_FILE = "src/data/users.txt";
    public static Map<String, User> users = new HashMap<>();
    private static Schedule schedule = new Schedule();

    public ScheduleManagerSystem() {
    }

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

    public static void loadSchedules() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/schedules.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String userId = parts[0];
                String taskName = parts[1];
                String taskLabel = parts[2];
                LocalDateTime startTime = LocalDateTime.parse(parts[3]);
                LocalDateTime endTime = LocalDateTime.parse(parts[4]);
                schedule.addTask(userId, taskName, taskLabel, startTime, endTime);
            }
            System.out.println("Schedules loaded from file.");
        } catch (IOException e) {
            System.out.println("No schedules file found, starting with an empty schedule list.");
        }
    }

    public static void saveSchedules() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/schedules.txt"))) {
        for (Map.Entry<String, Set<String>> userTasksEntry : schedule.getUserTasks().entrySet()) {
            String userId = userTasksEntry.getKey();
            for (String taskName : userTasksEntry.getValue()) {
                Vertex task = schedule.getTaskMap().get(taskName);
                if (task != null) {
                    writer.write(userId + ";" + taskName + ";" + task.getLabel() + ";" + task.getStartTime() + ";" + task.getEndTime() + "\n");
                }
            }
        }
        System.out.println("Schedules saved to file.");
    } catch (IOException e) {
        System.err.println("Error saving schedules: " + e.getMessage());
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

    public static User getUser(String userId) {
        return users.get(userId);
    }

    public static Schedule getSchedule() {
        return schedule;
    }

    public void manageTasks(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = getUser(userId);
        if (user == null || !user.verifyPassword(password)) {
            System.out.println("User not found or incorrect password.");
            return;
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
                    addTask(scanner, userId);
                    break;
                case 2:
                    removeTask(scanner, userId);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void addTask(Scanner scanner, String userId) {
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();
        if (getSchedule().getTaskMap().containsKey(taskName)) {
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

        if (getSchedule().hasConflict(startTime, endTime)) {
            System.out.println("This task conflicts with an existing task. Please choose a different time.");
            return;
        }

        getSchedule().addTask(userId, taskName, taskLabel, startTime, endTime);
        System.out.println("Task added successfully.");
    }

    private void removeTask(Scanner scanner, String userId) {
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();
        getSchedule().removeTask(userId, taskName);
        System.out.println("Task removed successfully.");
    }
}
