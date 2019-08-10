package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jobcafe.model.SubscriptionPlan;
import jobcafe.service.SubscriptionPlanService;

@RestController
public class SubscriptionPlanController {

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @GetMapping("/subscription-plan")
    public Iterable<SubscriptionPlan> getAll() {
        return subscriptionPlanService.findAll();
    }
}
