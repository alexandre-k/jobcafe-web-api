package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.SubscriptionPlan;

public interface SubscriptionPlanService extends CrudRepository<SubscriptionPlan, Integer> {
    SubscriptionPlan findByLabel(String label);
}
