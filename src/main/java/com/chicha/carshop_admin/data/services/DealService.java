package com.chicha.carshop_admin.data.services;

import com.chicha.carshop_admin.data.DTO.StatsDTO;
import com.chicha.carshop_admin.data.enities.Deal;
import com.chicha.carshop_admin.data.enities.Employee;
import com.chicha.carshop_admin.data.enities.Status;
import com.chicha.carshop_admin.data.enities.UpdateHistory;
import com.chicha.carshop_admin.data.repos.DealRepository;
import com.chicha.carshop_admin.data.repos.EmployeeRepository;
import com.chicha.carshop_admin.data.repos.StatusRepository;
import com.chicha.carshop_admin.data.repos.UpdateHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class DealService {
    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private UpdateHistoryRepository updateHistoryRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public List<Deal> getPendingDeals(){
        return dealRepository.findByStatus(statusRepository.findByName("Pending..."));
    }

    public StatsDTO getDealsStats() {
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setCompletedDeals(dealRepository.countByStatus(statusRepository.findByName("Completed")));
        statsDTO.setPendingDeals(dealRepository.countByStatus(statusRepository.findByName("Pending...")));
        statsDTO.setTotalDeals(dealRepository.findAll().size());
        statsDTO.setCancelledDeals(dealRepository.countByStatus(statusRepository.findByName("Cancelled")));
        return statsDTO;
    }

    public List<Deal> getClientDeals(int clientId) {
        return dealRepository.findByClientId(clientId);
    }

    public List<Deal> getEmployeeDeals(int employeeId) {
        return dealRepository.findByEmployeeId(employeeId);
    }

    public void changeStatus(int dealId, Status status) {
        dealRepository.findById(dealId).ifPresent(deal -> {
            deal.setStatus(status);
            dealRepository.save(deal);
        });
    }

    public void changeEmployee(int dealId, Employee employee) {
        Employee authed = employeeRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (authed != employee) {
            if (authed.getAccessRight().getId() < employee.getAccessRight().getId()) {
                dealRepository.findById(dealId).ifPresent(deal -> {
                    deal.setEmployee(employee);
                    dealRepository.save(deal);
                    changeStatus(dealId, statusRepository.findById(2).get());
                });
            }
        } else {
            dealRepository.findById(dealId).ifPresent(deal -> {
                deal.setEmployee(employee);
                dealRepository.save(deal);
                changeStatus(dealId, statusRepository.findById(2).get());
            });
        }
    }

    public Deal getDeal(int id) {
        return dealRepository.findById(id).get();
    }

    public List<Deal> getAcceptedDeals() {
        return dealRepository.findByStatusAndEmployee(statusRepository.findByName("Accepted"),employeeRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }
}
