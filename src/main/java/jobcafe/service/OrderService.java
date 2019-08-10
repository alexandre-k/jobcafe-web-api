package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.Order;
import jobcafe.model.User;

public interface OrderService extends CrudRepository<Order, Integer> {
    Iterable<Order> findByUser(User user);
}
