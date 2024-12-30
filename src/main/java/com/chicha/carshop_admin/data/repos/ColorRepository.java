package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    public Color findByName(String name);
    public Color deleteByName(String name);
    public boolean existsByName(String name);
}
