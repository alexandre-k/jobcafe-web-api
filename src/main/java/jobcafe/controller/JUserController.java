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

import jobcafe.model.JUser;
import jobcafe.service.JUserService;

@RestController
public class JUserController {

    @Autowired
    private JUserService userService;

    @PostMapping("/user")
    public JUser create(@Valid @RequestBody JUser user) {
        return userService.save(user);
    }

    @GetMapping("/user")
    public Iterable<JUser> read() {
        return userService.findAll();
    }

    @PutMapping("/user")
    public JUser update(@RequestBody JUser user) {
        return userService.save(user);
    }

    @PutMapping("/user/password")
    public JUser updatePassword(@RequestBody String email, String password) {
        JUser user = userService.findByEmail(email);
        user.setPassword(password);
        return userService.save(user);
    }

    @DeleteMapping("/user/{email}")
    public void delete(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

    @GetMapping("/user/{email}")
    JUser findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }
}
