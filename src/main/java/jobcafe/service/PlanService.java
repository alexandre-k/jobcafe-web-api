package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.Plan;

public interface PlanService extends CrudRepository<Plan, Integer> {
    Plan findByLabel(String label);
}
