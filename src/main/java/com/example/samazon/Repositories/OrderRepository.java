package com.example.samazon.Repositories;

import com.example.samazon.Beans.Order;
import com.example.samazon.Beans.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByUser(User user);

    Order findByUserAndOrdered(User user, int ordered);
}
