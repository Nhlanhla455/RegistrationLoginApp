package registrationloginapp;

/**
 * Holds one user's registration details and checks them against the
 * Part 1 rules (username, password, cell phone number, login).
 */
public class Login {

    // Messages are kept as constants so the app and the tests use identical text.
    public static final String USERNAME_OK = "Username successfully captured.";
    public static final String USERNAME_BAD = "Username is not correctly formatted; please ensure that "
            + "your username contains an underscore and is no more than five characters in length.";

    public static final String PASSWORD_OK = "Password successfully captured.";
    public static final String PASSWORD_BAD = "Password is not correctly formatted; please ensure that "
            + "the password contains at least eight characters, a capital letter, a number, "
            + "and a special character.";

    public static final String CELL_OK = "Cell number successfully captured.";
    public static final String CELL_BAD = "Cell number is incorrectly formatted or does not contain "
            + "an international code; please correct the number and try again.";

    public static final String REGISTER_OK = "User has been registered successfully.";
    public static final String LOGIN_BAD = "Username or password incorrect, please try again.";

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String cellNumber;

    // Only becomes true once registerUser() has passed every check.
    private boolean registered = false;

    public Login(String firstName, String lastName, String username,
                 String password, String cellNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    /** Username must contain an underscore and be at most five characters long. */
    public boolean checkUserName() {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    /** Password: 8+ characters, one capital letter, one digit, one special character. */
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetter(c)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Cell number must start with "+" and an international country code
     * (1-3 digits), followed by the number itself (no more than ten digits).
     *
     * Reference: the idea of a leading "+" and a 1-3 digit country code is
     * adapted from the regex examples in:
     * Gupta, L. 2024. Regex to Validate International Phone Numbers.
     * HowToDoInJava. [Online]. Available at:
     * https://howtodoinjava.com/java/regex/java-regex-validate-international-phone-numbers/
     * [Accessed 18 September 2026].
     */
    public boolean checkCellPhoneNumber() {
        return cellNumber != null && cellNumber.matches("^\\+[0-9]{1,3}[0-9]{1,10}$");
    }

    // Small helpers so each rule has its own message (this makes testing easy).
    public String getUsernameMessage() {
        return checkUserName() ? USERNAME_OK : USERNAME_BAD;
    }

    public String getPasswordMessage() {
        return checkPasswordComplexity() ? PASSWORD_OK : PASSWORD_BAD;
    }

    public String getCellMessage() {
        return checkCellPhoneNumber() ? CELL_OK : CELL_BAD;
    }

    /** Returns the registration messaging: the first problem found, or success. */
    public String registerUser() {
        if (!checkUserName()) {
            registered = false;
            return USERNAME_BAD;
        }
        if (!checkPasswordComplexity()) {
            registered = false;
            return PASSWORD_BAD;
        }
        if (!checkCellPhoneNumber()) {
            registered = false;
            return CELL_BAD;
        }
        registered = true;
        return REGISTER_OK;
    }

    public boolean isRegistered() {
        return registered;
    }

    /** True only if the user registered and the details entered match. */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return registered
                && username.equals(enteredUsername)
                && password.equals(enteredPassword);
    }

    /** Returns the login message for a successful or failed login. */
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return LOGIN_BAD;
    }
}