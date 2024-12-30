package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    public List<Model> findByModelNameId(int id);
}
