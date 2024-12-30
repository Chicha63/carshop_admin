package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Deal;
import com.chicha.carshop_admin.data.enities.Employee;
import com.chicha.carshop_admin.data.enities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Integer> {
    public List<Deal> findByClientId(int clientId);
    public List<Deal> findByEmployeeId(int employeeId);
    public List<Deal> findByStatus(Status status);
    public int countByStatus(Status status);

    List<Deal> findByStatusAndEmployee(Status byName, Employee byEmployee);
}
