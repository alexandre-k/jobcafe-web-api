package jobcafe.controller;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.AddressException;
import javax.validation.constraints.Email;

import jobcafe.EmailService;
import jobcafe.model.NewCode;

@RestController
public class PasswordReminder {

    private HashMap<String, Date> cache = new HashMap<>();
    private Integer cacheEntriesExpirationTimeSec = 120;

    @Autowired
    private EmailService emailService;

    @PostMapping("/reminder")
    public ResponseEntity generateCode(@RequestBody NewCode newCode) throws AddressException {
        String code = String.format("%04d", new Random().nextInt(9999));
        emailService.send(
                newCode.getEmail(),
                "Job Cafe: Code to create a new password",
                "A code to create a new password has been requested. Your code is:\n\t" + code);
        cache.put(newCode.getEmail()+ ":" + code, new Date());
        return new ResponseEntity<>("Code generated.", HttpStatus.ACCEPTED);
    }

    @PutMapping("/reminder")
    public ResponseEntity verifyCode(@RequestBody NewCode receivedCode) {
        String key = receivedCode.getEmail() + ":" + receivedCode.getCode();
        this.cleanUp(cache);
        if (!cache.containsKey(key)) {
            return new ResponseEntity<>(
                    "Code expired or wrong.",
                    HttpStatus.NOT_ACCEPTABLE);
        } else {
            cache.remove(key);
            return new ResponseEntity<>("OK.", HttpStatus.ACCEPTED);
        }
    }

    private void cleanUp(HashMap<String, Date> cache) {
        Date now = new Date();
        ArrayList<String> removedKeys = new ArrayList<>();
        cache.keySet().forEach( key -> {
            Date storedDate = cache.get(key);
            if ((now.getTime() - storedDate.getTime()) / 1000 > cacheEntriesExpirationTimeSec) {
                removedKeys.add(key);
            }
        });
        removedKeys.forEach(cache::remove);
    }
}
