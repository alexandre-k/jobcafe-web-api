package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import jobcafe.model.User;
import jobcafe.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User create(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/user")
    public Iterable<User> read() {
        return userService.findAll();
    }

    @PutMapping("/user")
    public User update(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/user/{email}")
    public void delete(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

    @GetMapping("/user/{email}")
    User findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }
}
