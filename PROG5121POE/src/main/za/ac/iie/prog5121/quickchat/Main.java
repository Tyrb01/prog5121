// Main.java
package za.ac.iie.prog5121.quickchat;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        
        System.out.println("=== User Registration System ===");
        
        // Get user info
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Username (max 5 chars, must have underscore): ");
        String username = scanner.nextLine();
        
        System.out.print("Password (min 8 chars, 1 uppercase, 1 number, 1 special char): ");
        String password = scanner.nextLine();
        
        System.out.print("Cell number (+27 followed by 9 digits): ");
        String cellPhone = scanner.nextLine();
        
        // Register user directly with the Login class
        String result = login.registerUser(username, password, cellPhone, firstName, lastName);
        System.out.println(result);
        
        // Login if registration was successful
        if (result.contains("successfully captured")) {
            System.out.println("\n=== Login ===");
            
            System.out.print("Username: ");
            String loginUsername = scanner.nextLine();
            
            System.out.print("Password: ");
            String loginPassword = scanner.nextLine();
            
            // Login user
            boolean loginSuccess = login.loginUser(loginUsername, loginPassword);
            
            // Get and display login status
            String loginStatus = login.returnLoginStatus();
            System.out.println(loginStatus);
        }
        
        scanner.close();
    }
}