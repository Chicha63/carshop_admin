package com.chicha.carshop_admin.data.controllers;
import com.chicha.carshop_admin.data.enities.AccessRight;
import com.chicha.carshop_admin.data.DTO.EmployeeDTO;
import com.chicha.carshop_admin.data.enities.Employee;
import com.chicha.carshop_admin.data.repos.EmployeeRepository;
import com.chicha.carshop_admin.data.services.EmployeeService;
import com.chicha.carshop_admin.security.AuthResponse;
import com.chicha.carshop_admin.security.JwtProvider;
import com.sun.net.httpserver.Headers;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5000"})
@RequiredArgsConstructor
public class AdminPanelAuthController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody EmployeeDTO employeeDTO) {
        try{
            Employee employee = employeeService.convertToEntity(employeeDTO);
            String email = employee.getEmail();
            String password = employee.getPassword();
            String fullname = employee.getFullName();
            AccessRight accessRight = employee.getAccessRight();
            Employee isExist = employeeRepository.findByEmail(email);
            if (isExist != null) {
                return new ResponseEntity<>("This email is already taken!",HttpStatus.CONFLICT);
            }
            Employee createdEmployee = new Employee();
            createdEmployee.setFullName(fullname);
            createdEmployee.setAccessRight(accessRight);
            createdEmployee.setEmail(email);
            createdEmployee.setPassword(passwordEncoder.encode(password));
            Employee savedEmployee = employeeService.saveEmployee(createdEmployee);
        } catch (Exception e){
            return new ResponseEntity<>("Failed to add employee: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("New employee added!",HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody Employee employee) {
        AuthResponse authResponse = new AuthResponse();
        try {
            String username = employee.getEmail();
            String password = employee.getPassword();

            System.out.println(username+"-------"+password);

            Authentication authentication = authenticate(username,password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = JwtProvider.generateToken(authentication);

            authResponse.setMessage("Login success");
            authResponse.setJwt(token);
            authResponse.setStatus(true);
        } catch (Exception e){
            authResponse.setStatus(false);
            authResponse.setMessage(e.getMessage());
            authResponse.setJwt(null);
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout() {
        AuthResponse authResponse = new AuthResponse();
        try {
            SecurityContextHolder.clearContext();
            authResponse.setMessage("Logout successful");
            authResponse.setStatus(true);
            authResponse.setJwt(null);
        } catch (Exception e){
            authResponse.setStatus(false);
            authResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        System.out.println(username+"-------"+password);

        UserDetails userDetails = employeeService.loadUserByUsername(username);

        System.out.println("Sign in in user details"+ userDetails);

        if(userDetails == null) {
            System.out.println("Sign in details - null" + userDetails);

            throw new BadCredentialsException("Invalid username and password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            System.out.println("Sign in userDetails - password mismatch"+userDetails);

            throw new BadCredentialsException("Invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails, password);

    }

}
