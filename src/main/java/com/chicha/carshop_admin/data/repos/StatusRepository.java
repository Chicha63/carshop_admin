package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    public Status findByName(String name);
}
