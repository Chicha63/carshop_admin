package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Integer> {
    Engine findByNameAndVolume(String name, Double volume);
}
