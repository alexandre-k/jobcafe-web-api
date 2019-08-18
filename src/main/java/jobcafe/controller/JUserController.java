package jobcafe.controller;

import com.google.common.hash.Hashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import javax.validation.Valid;

import jobcafe.model.JUser;
import jobcafe.service.JUserService;

@RestController
public class JUserController {

    @Autowired
    private JUserService userService;

    @PostMapping("/user")
    ResponseEntity create(@Valid @RequestBody JUser user) {
        Optional<JUser> userFound = userService.findByEmail(user.getEmail());
        if (userFound.isPresent()) {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.BAD_REQUEST);
        }
        user.setPasswordLength(user.getPassword().length());
        user.setPassword(Hashing.sha512().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    Iterable<JUser> read() {
        return userService.findAll();
    }

    @PutMapping("/user")
    ResponseEntity update(@RequestBody String email, String password) {
        Optional<JUser> maybeUser = userService.findByEmail(email);
        if (maybeUser.isPresent()) {
            JUser user = maybeUser.get();
            user.setPasswordLength(password.length());
            user.setPassword(Hashing
                    .sha512()
                    .hashString(user.getPassword(), StandardCharsets.UTF_8)
                    .toString());
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to update the user " + email, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/user/{email}")
    public void delete(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

    @GetMapping("/user/{email}")
    ResponseEntity findByEmail(@PathVariable String email) {
        Optional<JUser> user = userService.findByEmail(email);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No user found", HttpStatus.BAD_REQUEST);
    }
}
