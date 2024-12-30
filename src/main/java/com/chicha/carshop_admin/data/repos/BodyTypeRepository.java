package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyTypeRepository extends JpaRepository<BodyType, Integer> {
    BodyType findByName(String name);
}