package jobcafe.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import jobcafe.model.NewOrder;
import jobcafe.model.PlanOrder;
import jobcafe.model.SubscriptionPlan;
import jobcafe.model.JUser;
import jobcafe.service.JUserService;
import jobcafe.service.PlanOrderService;
import jobcafe.service.SubscriptionPlanService;

@RestController
public class PlanOrderController {

    @Autowired
    private PlanOrderService planOrderService;

    @Autowired
    private JUserService juserService;

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @PostMapping("/order")
    public ResponseEntity create(@Valid @RequestBody NewOrder newOrder) {
        SubscriptionPlan plan = subscriptionPlanService.findByLabel(newOrder.getLabel());
        Optional<JUser> maybeTransactor = juserService.findByEmail(newOrder.getOrderer());
        if (maybeTransactor.isPresent()) {
            PlanOrder order = new PlanOrder(maybeTransactor.get(), plan);
            return new ResponseEntity<>(planOrderService.save(order), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(
                    "No transactor registered with email: " + newOrder.getOrderer(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/order")
    public Iterable<PlanOrder> getOrders(@RequestParam(required = false) String email) {
        if (email == null) return planOrderService.findAll();
        return planOrderService.findByTransactorEmail(email);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity getOrderById(@PathVariable String id) {
        Optional maybeOrder = planOrderService.findById(id);
        if (maybeOrder.isPresent()) {
            return new ResponseEntity<>(maybeOrder.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No order found", HttpStatus.NOT_FOUND);
    }
}
