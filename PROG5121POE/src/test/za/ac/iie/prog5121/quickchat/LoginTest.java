package za.ac.iie.prog5121.quickchat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
    }

    @Test
    void checkUserName_valid() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void checkUserName_invalid() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    @Test
    void checkPasswordComplexity_valid() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void checkPasswordComplexity_invalid() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    void checkCellPhoneNumber_valid() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void checkCellPhoneNumber_invalid() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    void registerUser_invalidUsername_message() {
        String actual = login.registerUser("invalidUser", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String expected = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        assertEquals(expected, actual);
    }

    @Test
    void registerUser_invalidPassword_message() {
        String actual = login.registerUser("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        String expected = "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        assertEquals(expected, actual);
    }

    @Test
    void registerUser_invalidCell_message() {
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "Smith");
        String expected = "Cellphone number is not correctly formatted, please ensure that the cellphone number starts with +27 and is followed by 9 digits.";
        assertEquals(expected, actual);
    }

    @Test
    void registerUser_allValid_containsSuccessParts() {
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(actual.contains("Username successfully captured."));
        assertTrue(actual.contains("Password successfully captured."));
        assertTrue(actual.contains("Cell number successfully captured."));
    }

    @Test
    void loginUser_success_and_statusMessage() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
        String expected = "Welcome Kyle, Smith it is great to see you again.";
        assertEquals(expected, login.returnLoginStatus());
    }

    @Test
    void loginUser_failure_and_statusMessage() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(login.loginUser("kyl_1", "wrongPass"));
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, login.returnLoginStatus());
    }
}