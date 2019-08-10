package jobcafe.service;

import org.springframework.data.repository.CrudRepository;
import jobcafe.model.PaymentMethod;
import jobcafe.model.User;

public interface PaymentMethodService extends CrudRepository<PaymentMethod, Integer> {
    Iterable<PaymentMethod> findByUser(User user);
}
