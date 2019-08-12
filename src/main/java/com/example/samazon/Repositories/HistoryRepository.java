package com.example.samazon.Repositories;

import com.example.samazon.Beans.History;
import com.example.samazon.Beans.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface HistoryRepository extends CrudRepository<History, Long> {
    ArrayList<History> findAllByUser(User user);

    ArrayList<History> findAllByProductId(Long id);

    ArrayList<History> findAllByUserOrderByProductId(User user);

    ArrayList<History> findAllByUserAndStatus(User user, long status);
}
