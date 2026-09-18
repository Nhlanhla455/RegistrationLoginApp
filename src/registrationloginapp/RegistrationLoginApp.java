/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package registrationloginapp;

/**
 *
 * @author Student
 */
public class RegistrationLoginApp {

         public static void main(String[] args) {

        // Create a RegistrationLogin object with test data
        RegistrationLogin user = new RegistrationLogin(
                "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Naidoo");

        // Register and print the result
        System.out.println(user.registerUser());

        // Attempt login, then print the login status
        boolean loginResult = user.loginUser("kyl_1", "Ch&&sec@ke99!");
        System.out.println(user.returnLoginStatus(loginResult));
    }
}