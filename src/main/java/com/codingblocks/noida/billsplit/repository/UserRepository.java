package com.codingblocks.noida.billsplit.repository;

import com.codingblocks.noida.billsplit.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

}