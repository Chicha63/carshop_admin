package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
