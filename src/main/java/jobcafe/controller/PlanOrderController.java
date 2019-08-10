package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;
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

    @PostMapping("/plan-order")
    public PlanOrder create(@Valid @RequestBody String planLabel, String email) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        // Add 3 business days
        calendar.add(Calendar.DATE, 3);
        SubscriptionPlan plan = subscriptionPlanService.findByLabel(planLabel);
        PlanOrder order = new PlanOrder();
        order.setDeliveryEstimate(calendar.getTime());
        order.setTransactorEmail(email);
        order.setPlan(plan);
        return planOrderService.save(order);
    }

    @GetMapping("/order")
    public Iterable<PlanOrder> getAll() { return planOrderService.findAll(); }

    @GetMapping("/order/{email}")
    public Iterable<PlanOrder> getOrdersByUser(String email) {
        return planOrderService.findByTransactorEmail(email);
    }
}
