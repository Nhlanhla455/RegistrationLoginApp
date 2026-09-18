package registrationloginapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
public class RegistrationLogin {
        public String username;
    public String password;
    public String cellPhoneNumber;
    public String firstName;
    public String lastName;

    public RegistrationLogin(String username, String password,
            String cellPhoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public boolean checkUserName() {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }
    
    public boolean checkPasswordComplexity() {
        String pattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";
        return password != null && java.util.regex.Pattern.matches(pattern, password);
    }
    
    public boolean checkCellPhoneNumber() {
        String pattern = "^\\+27\\d{1,10}$";
        return cellPhoneNumber != null && java.util.regex.Pattern.matches(pattern, cellPhoneNumber);
    }
    
    public String registerUser() {
        StringBuilder result = new StringBuilder();

        if (checkUserName()) {
            result.append("Username successfully captured.");
        } else {
            result.append("Username is not correctly formatted; please "
                    + "ensure that your username contains an underscore "
                    + "and is no more than five characters in length.");
        }

        if (checkPasswordComplexity()) {
            result.append(" Password successfully captured.");
        } else {
            result.append(" Password is not correctly formatted; please "
                    + "ensure that the password contains at least eight "
                    + "characters, a capital letter, a number, and a "
                    + "special character.");
        }

        if (checkCellPhoneNumber()) {
            result.append(" Cell phone number successfully captured.");
        } else {
            result.append(" Cell phone number incorrectly formatted or "
                    + "does not contain international code.");
        }

        return result.toString();
    }
    
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return username != null && password != null
                && username.equals(enteredUsername)
                && password.equals(enteredPassword);
    }
    
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}
