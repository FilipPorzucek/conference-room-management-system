package com.booking.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UserTest {

    @Test
    void testGettersAndSetters() {

        User user = new User();
        Long id = 1L;
        String email = "jan@kowalski.pl";
        String password = "bezpieczneHaslo";
        String role = "USER";


        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);


        assertEquals(id, user.getId(), "ID powinno się zgadzać");
        assertEquals(email, user.getEmail(), "Email powinien się zgadzać");
        assertEquals(password, user.getPassword(), "Hasło powinno się zgadzać");
        assertEquals(role, user.getRole(), "Rola powinna się zgadzać");
    }

    @Test
    void testUserObjectNotNull() {
        // Szybki test czy konstruktor działa
        User user = new User();
        assertNotNull(user, "Obiekt User nie powinien być nullem po utworzeniu");
    }

}