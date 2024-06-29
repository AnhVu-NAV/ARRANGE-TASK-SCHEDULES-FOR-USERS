/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImportData;

import entity.User;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vungu
 */
public class CreateUsers {
    public static void main(String[] args) {
        Map<String, User> users = new HashMap<>();
        users.put("1", new User("1", "Alice", "alice@example.com", "1234567890", "password1"));
        users.put("2", new User("2", "Bob", "bob@example.com", "0987654321", "password2"));
        users.put("3", new User("3", "Charlie", "charlie@example.com", "1122334455", "password3"));

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("users.txt"))) {
            out.writeObject(users);
            System.out.println("Users saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }
}
