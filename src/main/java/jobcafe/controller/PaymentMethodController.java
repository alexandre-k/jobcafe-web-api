package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import jobcafe.model.PaymentMethod;
import jobcafe.model.User;
import jobcafe.service.PaymentMethodService;
import jobcafe.service.UserService;

@RestController
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private UserService userService;

    @PostMapping("/payment-method")
    public PaymentMethod create(@Valid @RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.save(paymentMethod);
    }

    @GetMapping("/payment-method/{email}")
    public Iterable<PaymentMethod> getAll(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return paymentMethodService.findByUser(user);
    }
}
