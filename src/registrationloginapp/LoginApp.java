package registrationloginapp;

import java.util.Scanner;

/** Console app: register a user, then log in. No GUI is used. */
public class LoginApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login user;

        // Keep asking until the registration details pass every check.
        do {
            System.out.println("=== Registration ===");
            System.out.print("First name: ");
            String first = input.nextLine().trim();
            System.out.print("Last name: ");
            String last = input.nextLine().trim();
            System.out.print("Username: ");
            String username = input.nextLine().trim();
            System.out.print("Password: ");
            String password = input.nextLine();
            System.out.print("Cell number (e.g. +27831234567): ");
            String cell = input.nextLine().trim();

            user = new Login(first, last, username, password, cell);

            // Show the message for each rule, then the overall result.
            System.out.println(user.getUsernameMessage());
            System.out.println(user.getPasswordMessage());
            System.out.println(user.getCellMessage());
            System.out.println(user.registerUser());
            System.out.println();
        } while (!user.isRegistered());

        System.out.println("=== Login ===");
        System.out.print("Username: ");
        String enteredUser = input.nextLine().trim();
        System.out.print("Password: ");
        String enteredPass = input.nextLine();

        System.out.println(user.returnLoginStatus(enteredUser, enteredPass));
        input.close();
    }
}