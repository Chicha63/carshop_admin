package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
    Availability findByType(String name);
}
