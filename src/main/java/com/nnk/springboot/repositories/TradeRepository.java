package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TradeRepository extends JpaRepository<Trade, Integer> {
    Trade save(Trade trade);

    List<Trade> findAll();

    void deleteById(Integer id);

}
