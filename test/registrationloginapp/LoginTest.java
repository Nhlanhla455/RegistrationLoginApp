package registrationloginapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoginTest {

    // Builds a Login with valid defaults, so each test only changes the field it is testing.
    private Login make(String username, String password, String cell) {
        return new Login("Kyle", "Smith", username, password, cell);
    }

    private Login validUser() {
        return make("kyl_1", "Ch&&sec@ke99!", "+27838968976");
    }

    // ---------- assertEquals ----------

    @Test
    public void usernameCorrectlyFormatted_message() {
        assertEquals(Login.USERNAME_OK, make("kyl_1", "x", "x").getUsernameMessage());
    }

    @Test
    public void usernameIncorrectlyFormatted_message() {
        assertEquals(Login.USERNAME_BAD, make("kyle!!!!!!!", "x", "x").getUsernameMessage());
    }

    @Test
    public void passwordMeetsComplexity_message() {
        assertEquals(Login.PASSWORD_OK, make("x", "Ch&&sec@ke99!", "x").getPasswordMessage());
    }

    @Test
    public void passwordFailsComplexity_message() {
        assertEquals(Login.PASSWORD_BAD, make("x", "password", "x").getPasswordMessage());
    }

    @Test
    public void cellCorrectlyFormatted_message() {
        assertEquals(Login.CELL_OK, make("x", "x", "+27838968976").getCellMessage());
    }

    @Test
    public void cellIncorrectlyFormatted_message() {
        assertEquals(Login.CELL_BAD, make("x", "x", "08966553").getCellMessage());
    }

    @Test
    public void registerUser_success() {
        assertEquals(Login.REGISTER_OK, validUser().registerUser());
    }

    @Test
    public void loginStatus_successMessage() {
        Login user = validUser();
        user.registerUser();
        assertEquals("Welcome Kyle, Smith it is great to see you again.",
                user.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }

    // ---------- assertTrue / assertFalse ----------

    @Test
    public void loginSuccessful() {
        Login user = validUser();
        user.registerUser();
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void loginFailed() {
        Login user = validUser();
        user.registerUser();
        assertFalse(user.loginUser("kyl_1", "wrongPass1!"));
    }

    @Test
    public void usernameCorrectlyFormatted() {
        assertTrue(make("kyl_1", "x", "x").checkUserName());
    }

    @Test
    public void usernameIncorrectlyFormatted() {
        assertFalse(make("kyle!!!!!!!", "x", "x").checkUserName());
    }

    @Test
    public void passwordMeetsComplexityRequirements() {
        assertTrue(make("x", "Ch&&sec@ke99!", "x").checkPasswordComplexity());
    }

    @Test
    public void passwordDoesNotMeetComplexityRequirements() {
        assertFalse(make("x", "password", "x").checkPasswordComplexity());
    }

    @Test
    public void cellCorrectlyFormatted() {
        assertTrue(make("x", "x", "+27838968976").checkCellPhoneNumber());
    }

    @Test
    public void cellIncorrectlyFormatted() {
        assertFalse(make("x", "x", "08966553").checkCellPhoneNumber());
    }
}