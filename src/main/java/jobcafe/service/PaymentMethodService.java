package jobcafe.service;

import org.springframework.data.repository.CrudRepository;
import jobcafe.model.PaymentMethod;
import jobcafe.model.JUser;

public interface PaymentMethodService extends CrudRepository<PaymentMethod, Integer> {
    PaymentMethod findByPayer(JUser payer);
}
