package jobcafe.controller;

import java.util.Random;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordReminder {

    private String fakeCache;

    @PostMapping("/reminder")
    public String generateCode(@RequestBody String email) {
        String code = String.format("%04d", new Random().nextInt(9999));
        System.out.println("code: " + code);
        // TODO implement cache and add code with expiration of 5 minutes in cache.
        fakeCache = code;
        return "generated";
    }

    @PutMapping("/reminder")
    public String verifyCode(@RequestBody String code) {
        if (code == fakeCache) {
            return "OK";
        } else {
            return "BAD";
        }
    }

}
