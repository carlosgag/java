package com.etraveli.cardcost.users;

import com.etraveli.cardcost.persistence.UserRepository;
import com.etraveli.cardcost.persistence.data.UserData;
import com.etraveli.cardcost.users.entities.User;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest implements WithAssertions {
    public static final String USER = "user";
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository);
    }

    @Test
    void testGet() {
        UserData userData = Mockito.mock(UserData.class);
        when(userData.getUsername())
                .thenReturn(USER);
        when(userRepository.findById(USER))
                .thenReturn(Optional.of(userData));
        final var result = userService.get(USER);
        assertThat(result.getUser()).isEqualTo(USER);
    }

    @Test
    void testPost() {
        User user = Mockito.mock(User.class);
        userService.post(user);
        verify(userRepository).save(any());
    }

    @Test
    void testDelete() {
        userService.delete(USER);
        verify(userRepository).deleteById(USER);
    }

    @Test
    void testUpdate() {
        User user = Mockito.mock(User.class);
        userService.update(user);
        verify(userRepository).save(any());
    }

}