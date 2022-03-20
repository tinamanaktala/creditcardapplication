package com.restapp.ccprocess.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapp.ccprocess.model.CreditCard;

@Repository
public interface CardProcessingRepository extends JpaRepository<CreditCard,Long> {

}
