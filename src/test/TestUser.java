package test;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestUser {
    User user;

    @BeforeEach
    void setUp(){
        user = new User();
    }

    @Test
    void testConstructor(){
        assertEquals(0000, user.getPasswords());
    }

    @Test
    void testsetPasswords() {
        assertEquals(0000, user.getPasswords());
        user.setPasswords(1234);
        assertEquals(1234, user.getPasswords());
    }
}
