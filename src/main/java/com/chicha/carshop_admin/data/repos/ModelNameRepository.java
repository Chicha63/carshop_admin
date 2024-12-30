package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Manufacturer;
import com.chicha.carshop_admin.data.enities.ModelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelNameRepository extends JpaRepository<ModelName, Integer> {
    public List<ModelName> findByManufacturerId(int manufacturer);
    public ModelName findByName(String modelName);

    public boolean existsByNameAndManufacturer(String name, Manufacturer manufacturer);

    ModelName findByNameAndManufacturer(String name, Manufacturer manufacturer);
}
