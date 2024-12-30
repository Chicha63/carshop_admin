package com.chicha.carshop_admin.data.services;

import com.chicha.carshop_admin.data.enities.AccessRight;
import com.chicha.carshop_admin.data.DTO.EmployeeDTO;
import com.chicha.carshop_admin.data.enities.Employee;
import com.chicha.carshop_admin.data.repos.AccessRightRepository;
import com.chicha.carshop_admin.data.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AccessRightRepository accessRightRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee convertToEntity(EmployeeDTO dto) {
        if(dto.getId() != null) {
            if(employeeRepository.findById(dto.getId()).isPresent()) {
                return employeeRepository.findById(dto.getId()).get();
            }
        }
        AccessRight accessRight = accessRightRepository.findById(dto.getAccessright()).orElse(null);
        return Employee.builder()
                .fullName(dto.getFullname())
                .accessRight(accessRight)
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(EmployeeDTO employee) {
        Employee authed = employeeRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Employee toUpdate = getEmployeeById(employee.getId()).orElseGet(()->{
            if (employee.getEmail() != null) {
                return employeeRepository.findByEmail(employee.getEmail());
            }
            return null;
        });
        if (toUpdate != null) {
            if (employee.getEmail() != null) {
                toUpdate.setEmail(employee.getEmail());
            }
            if (employee.getPassword() != null && authed.getEmail().equals(employee.getEmail())) {
                toUpdate.setPassword(passwordEncoder.encode(employee.getPassword()));
            }
            toUpdate.setFullName(
                    employee.getFullname() != null
                    ? employee.getFullname() : toUpdate.getFullName()
            );
            toUpdate.setAccessRight(
                    employee.getAccessright() != null
                    && authed.getAccessRight().getId() < employee.getAccessright()
                    ? toUpdate.getAccessRight() : accessRightRepository.findById(employee.getAccessright()).orElse(toUpdate.getAccessRight())
            );
            return employeeRepository.save(toUpdate);
        }
        return employeeRepository.save(convertToEntity(employee));
    }

    public void deleteEmployee(EmployeeDTO employee) {
        Employee authed = employeeRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (employeeRepository.findById(employee.getId()).isPresent()) {
            if (employee.getAccessright() > authed.getAccessRight().getId() || authed.getAccessRight().getId() == 1) {
                employeeRepository.deleteById(employee.getId());
            } else {
                throw new AuthenticationException("Not enough rights") {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                };
            }
        } else if (employeeRepository.findByEmail(employee.getEmail()) != null) {
            employeeRepository.delete(employeeRepository.findByEmail(employee.getEmail()));
        } else {
            throw new UsernameNotFoundException("Employee not found");
        }
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(username);
        System.out.println(employee);
        if (employee == null) {
            throw new UsernameNotFoundException("Email not found");
        }

        System.out.println("Loaded employee: " + employee.getEmail());
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(
                employee.getEmail(),
                employee.getPassword(),
                authorities
        );
    }
}
