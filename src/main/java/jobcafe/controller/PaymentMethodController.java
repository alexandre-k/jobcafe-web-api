package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.validation.Valid;

import jobcafe.model.PaymentMethod;
import jobcafe.model.JUser;
import jobcafe.service.PaymentMethodService;
import jobcafe.service.JUserService;

@RestController
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private JUserService userService;

    @PostMapping("/payment-method")
    public PaymentMethod create(@Valid @RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.save(paymentMethod);
    }

    @GetMapping("/payment-method")
    public ResponseEntity getByEmail(@RequestParam(required = false) String email) {
        if (email == null) return new ResponseEntity<>(paymentMethodService.findAll(), HttpStatus.OK);
        Optional<JUser> user = userService.findByEmail(email);
        if (user.isPresent()) {
            return new ResponseEntity<>(paymentMethodService.findByPayer(user.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "No payment method found for user with email: " + email,
                    HttpStatus.NOT_FOUND);
        }
    }
}
