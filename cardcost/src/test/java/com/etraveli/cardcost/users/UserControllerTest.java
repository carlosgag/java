package com.etraveli.cardcost.users;

import com.etraveli.cardcost.users.entities.User;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest implements WithAssertions {
    public static final String USER = "user";
    @Mock
    UserService userService;

    private UserController userController;

    @BeforeEach
    void setup() {
        userController = new UserController(userService);
    }

    @Test
    void testGet() {
        assertThat(userController.get(USER)).isNull();
    }

    @Test
    void testPost() {
        userController.post(User.builder().build());
        verify(userService).post(any());
    }

    @Test
    void testUpdate() {
        userController.update(User.builder().build());
        verify(userService).update(any());
    }

    @Test
    void testDelete() {
        userController.delete(USER);
        verify(userService).delete(USER);
    }

    @Test
    void testHealth() {
        final var result = userController.health();
        assertThat(result).isEqualTo("OK");
    }
}