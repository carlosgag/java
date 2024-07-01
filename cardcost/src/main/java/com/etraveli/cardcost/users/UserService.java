package com.etraveli.cardcost.users;

import com.etraveli.cardcost.persistence.UserRepository;
import com.etraveli.cardcost.persistence.data.UserData;
import com.etraveli.cardcost.users.entities.User;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(String user) {
        try {
            Optional<UserData> userDataOpt = userRepository.findById(user);
            if (userDataOpt.isPresent()) {
                UserData userData = userDataOpt.get();
                return User.builder()
                        .user(userData.getUsername())
                        .password(userData.getPassword())
                        .build();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new PersistenceException(String.format("Error getting User: %s", user));
        }

    }

    public void post(User user) {
        try {
            userRepository.save(new UserData(user.getUser(), user.getPassword(), user.getRole()));
        } catch (Exception e) {
            throw new PersistenceException(String.format("Error saving User: %s", user.getUser()));
        }
    }

    public void update(User user) {
        post(user);
    }

    public void delete(String user) {
        try {
            userRepository.deleteById(user);
        } catch (Exception e) {
            throw new PersistenceException(String.format("Error deleting User: %s", user));
        }
    }
}
