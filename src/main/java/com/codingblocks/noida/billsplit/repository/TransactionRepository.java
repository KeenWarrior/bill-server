package com.codingblocks.noida.billsplit.repository;

import com.codingblocks.noida.billsplit.model.Tour;
import com.codingblocks.noida.billsplit.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

}
