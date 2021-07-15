package com.ecomerce.ecomerce.repository;

import com.ecomerce.ecomerce.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
}
