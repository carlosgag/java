package com.cardcost.users;

import com.cardcost.users.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{user}")
    public User get(@PathVariable @NotEmpty String user) {
        return userService.get(user);
    }

    @PostMapping
    public void post(@Valid @RequestBody @NotNull User user) {
        userService.post(user);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/{user}")
    public void delete(@PathVariable @NotEmpty String user) {
        userService.delete(user);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}