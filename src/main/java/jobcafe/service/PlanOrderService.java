package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import jobcafe.model.PlanOrder;

public interface PlanOrderService extends CrudRepository<PlanOrder, Integer> {
    Iterable<PlanOrder> findByTransactorEmail(String email);
    Optional<PlanOrder> findById(String id);
}
