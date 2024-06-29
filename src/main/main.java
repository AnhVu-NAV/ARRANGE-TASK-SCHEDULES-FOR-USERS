/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.User;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import manager.ScheduleManagerSystem;
/**
 *
 * @author vungu
 */
public class Main {
    public static void main(String[] args) {
        ScheduleManagerSystem manager = new ScheduleManagerSystem();
        manager.loadUsers();  // Automatically load users from file when the program starts
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add new user");
            System.out.println("2. Select user to manage tasks");
            System.out.println("3. Export user's schedule to file");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    manager.addUser(scanner);
                    break;
                case 2:
                    manager.manageUserTasks(scanner);
                    break;
                case 3:
                    manager.exportUserSchedule(scanner);
                    break;
                case 4:
                    manager.saveUsers();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}