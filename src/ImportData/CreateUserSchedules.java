/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImportData;

import entity.User;
import entity.UserSchedule;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author vungu
 */
public class CreateUserSchedules {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            // Alice's Schedule
            User alice = new User("1", "Alice", "alice@example.com", "1234567890", "password1");
            UserSchedule aliceSchedule = new UserSchedule(alice);
            aliceSchedule.addTask("Meeting", "Team Meeting", LocalDateTime.parse("2024-07-01 09:00", formatter), LocalDateTime.parse("2024-07-01 10:00", formatter));
            aliceSchedule.addTask("Work", "Project Work", LocalDateTime.parse("2024-07-01 10:30", formatter), LocalDateTime.parse("2024-07-01 12:00", formatter));
            saveSchedule(aliceSchedule);

            // Bob's Schedule
            User bob = new User("2", "Bob", "bob@example.com", "0987654321", "password2");
            UserSchedule bobSchedule = new UserSchedule(bob);
            bobSchedule.addTask("Workout", "Gym", LocalDateTime.parse("2024-07-01 07:00", formatter), LocalDateTime.parse("2024-07-01 08:00", formatter));
            bobSchedule.addTask("Study", "Read Book", LocalDateTime.parse("2024-07-01 08:30", formatter), LocalDateTime.parse("2024-07-01 10:00", formatter));
            saveSchedule(bobSchedule);

            // Charlie's Schedule
            User charlie = new User("3", "Charlie", "charlie@example.com", "1122334455", "password3");
            UserSchedule charlieSchedule = new UserSchedule(charlie);
            charlieSchedule.addTask("Breakfast", "Eat Breakfast", LocalDateTime.parse("2024-07-01 08:00", formatter), LocalDateTime.parse("2024-07-01 08:30", formatter));
            charlieSchedule.addTask("Exercise", "Morning Run", LocalDateTime.parse("2024-07-01 09:00", formatter), LocalDateTime.parse("2024-07-01 10:00", formatter));
            saveSchedule(charlieSchedule);

            System.out.println("User schedules saved to file.");
        } catch (Exception e) {
            System.err.println("Error creating user schedules: " + e.getMessage());
        }
    }

    private static void saveSchedule(UserSchedule schedule) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(schedule.getUser().getId() + ".txt"))) {
            out.writeObject(schedule);
        }
    }
}
