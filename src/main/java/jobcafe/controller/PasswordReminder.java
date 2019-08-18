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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jobcafe.model.NewCode;

@RestController
public class PasswordReminder {

    private HashMap<String, Date> cache = new HashMap<>();
    private Integer cacheEntriesExpirationTimeSec = 30;

    @PostMapping("/reminder")
    public String generateCode(@RequestBody NewCode newCode) {

        String code = String.format("%04d", new Random().nextInt(9999));
        System.out.println("code: " + code);
        cache.put(newCode.getEmail()+ ":" + code, new Date());
        return "generated";
    }

    @PutMapping("/reminder")
    public ResponseEntity verifyCode(@RequestBody NewCode receivedCode) {
        String key = receivedCode.getEmail() + ":" + receivedCode.getCode();
        this.cleanUp(cache);
        if (!cache.containsKey(key)) {
            return new ResponseEntity<>("Code expired or wrong.", HttpStatus.NOT_ACCEPTABLE);
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
