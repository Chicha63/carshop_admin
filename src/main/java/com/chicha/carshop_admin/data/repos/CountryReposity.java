package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryReposity extends JpaRepository<Country, Integer> {
    Country findByName(String name);
}
