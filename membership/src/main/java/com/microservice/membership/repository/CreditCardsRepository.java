package com.microservice.membership.repository;

import com.microservice.membership.model.CreditCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardsRepository extends JpaRepository<CreditCards, Long> {

}
