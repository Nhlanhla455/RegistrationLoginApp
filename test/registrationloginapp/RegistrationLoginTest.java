/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package registrationloginapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class RegistrationLoginTest {
    
    public RegistrationLoginTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkUserName method, of class RegistrationLogin.
     */
    @Test
    public void testCheckUserName() {
                    System.out.println("checkUserName");
        RegistrationLogin instance = new RegistrationLogin(
                "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Naidoo");
        boolean expResult = true;
        boolean result = instance.checkUserName();
        assertEquals(expResult, result);
    }
   

    /**
     * Test of checkPasswordComplexity method, of class RegistrationLogin.
     */
        @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        RegistrationLogin instance = new RegistrationLogin(
                "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Naidoo");
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkCellPhoneNumber method, of class RegistrationLogin.
     */
        @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        RegistrationLogin instance = new RegistrationLogin(
                "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Naidoo");
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of registerUser method, of class RegistrationLogin.
     */
        @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        RegistrationLogin instance = new RegistrationLogin(
                "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Naidoo");
        String expResult = "Username successfully captured. Password successfully captured. Cell phone number successfully captured.";
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUser method, of class RegistrationLogin.
     */
        @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String enteredUsername = "kyl_1";
        String enteredPassword = "Ch&&sec@ke99!";
        RegistrationLogin instance = new RegistrationLogin(
                "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Naidoo");
        boolean expResult = true;
        boolean result = instance.loginUser(enteredUsername, enteredPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnLoginStatus method, of class RegistrationLogin.
     */
        @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        boolean loginSuccessful = true;
        RegistrationLogin instance = new RegistrationLogin(
                "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Naidoo");
        String expResult = "Welcome Kyle, Naidoo it is great to see you again.";
        String result = instance.returnLoginStatus(loginSuccessful);
        assertEquals(expResult, result);
    }
    
}
