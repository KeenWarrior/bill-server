package com.codingblocks.noida.billsplit.repository;

import com.codingblocks.noida.billsplit.model.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends CrudRepository<Tour, String>{

}
