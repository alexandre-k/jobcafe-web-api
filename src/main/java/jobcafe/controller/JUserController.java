package jobcafe.controller;

import com.google.common.hash.Hashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

import jobcafe.EmailService;
import jobcafe.model.JUser;
import jobcafe.model.NewPassword;
import jobcafe.service.JUserService;

@RestController
public class JUserController {

    @Autowired
    private JUserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/user")
    ResponseEntity create(@Valid @RequestBody JUser user) {
        Optional<JUser> userFound = userService.findByEmail(user.getEmail());
        if (userFound.isPresent()) {
            return new ResponseEntity<String>("Email already registered.", HttpStatus.BAD_REQUEST);
        }
        user.setPasswordLength(user.getPassword().length());
        user.setPassword(Hashing
                .sha512()
                .hashString(user.getPassword(), StandardCharsets.UTF_8)
                .toString());
        user.setProfilePicture("https://jobcafe-bucket.s3-ap-northeast-1.amazonaws.com/profile_picture.png");
        emailService.send(
                user.getEmail(),
                "Welcome to JobCafe",
                "We hope you will enjoy our application!");
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    Iterable<JUser> read() {
        return userService.findAll();
    }

    @PutMapping("/user")
    ResponseEntity update(@RequestBody NewPassword newPassword) {
        Optional<JUser> maybeUser = userService.findByEmail(newPassword.getEmail());
        if (maybeUser.isPresent()) {
            JUser user = maybeUser.get();
            user.setPasswordLength(newPassword.getPassword().length());
            user.setPassword(Hashing
                    .sha512()
                    .hashString(newPassword.getPassword(), StandardCharsets.UTF_8)
                    .toString());
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "Unable to update the user " + newPassword.getEmail(),
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/user/{email}")
    public void delete(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

    @PostMapping("/user/{email}")
    ResponseEntity login(@PathVariable String email, @RequestBody NewPassword password) {
        Optional<JUser> maybeUser = userService.findByEmail(email);
        if (maybeUser.isPresent()) {
            JUser loginUser = maybeUser.get();
            String hashedPassword = Hashing
                    .sha512()
                    .hashString(password.getPassword(), StandardCharsets.UTF_8)
                    .toString();
            if (!loginUser.getPassword().equals(hashedPassword)) {
                return new ResponseEntity<>("Password incorrect.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(loginUser, HttpStatus.OK);
        }
        return new ResponseEntity<>("No user found", HttpStatus.BAD_REQUEST);
    }
}
