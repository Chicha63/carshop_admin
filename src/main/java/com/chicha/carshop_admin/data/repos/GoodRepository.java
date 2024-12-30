package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Integer> {

}
