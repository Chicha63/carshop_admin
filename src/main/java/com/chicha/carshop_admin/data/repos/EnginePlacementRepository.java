package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.EnginePlacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnginePlacementRepository extends JpaRepository<EnginePlacement, Integer> {
    EnginePlacement findByName(String name);
}
