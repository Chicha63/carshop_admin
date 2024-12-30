package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.UpdateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateHistoryRepository extends JpaRepository<UpdateHistory, Integer> {

}
