package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import jobcafe.model.Order;
import jobcafe.model.Plan;
import jobcafe.model.User;
import jobcafe.service.OrderService;
import jobcafe.service.PlanService;
import jobcafe.service.UserService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    @PostMapping("/order")
    public Order create(@Valid @RequestBody String planLabel, String email) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        // Add 3 business days
        calendar.add(Calendar.DATE, 3);
        User user = userService.findByEmail(email);
        Plan plan = planService.findByLabel(planLabel);
        Order order = new Order(calendar.getTime(), user, plan);
        return orderService.save(order);
    }

    @GetMapping("/order")
    public Iterable<Order> getAll() { return orderService.findAll(); }

    @GetMapping("/order/{email}")
    public Iterable<Order> getOrdersByUser(String email) {
        User user = userService.findByEmail(email);
        return orderService.findByUser(user);
    }
}
