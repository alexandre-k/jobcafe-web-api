package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.JUser;
import jobcafe.model.PlanOrder;

public interface PlanOrderService extends CrudRepository<PlanOrder, Integer> {
    Iterable<PlanOrder> findByTransactorEmail(String email);
}
