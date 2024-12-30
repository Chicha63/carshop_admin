package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public List<Client> findByFullName(String fullName);
    public Client findByEmail(String email);
    public Client findByPhone(String phone);
    public Client findByPassportNumber(String passportNumber);
}
