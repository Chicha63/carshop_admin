package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.AccessRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRightRepository extends JpaRepository<AccessRight, Integer> {

}
