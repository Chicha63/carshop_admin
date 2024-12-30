package com.chicha.carshop_admin.data.services;

import com.chicha.carshop_admin.data.enities.Client;
import com.chicha.carshop_admin.data.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(int id) {
        return clientRepository.findById(id).get();
    }

    public List<Client> findByName(String name) {
        return clientRepository.findByFullName(name);
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Client findByPassport(String passport) {
        return clientRepository.findByPassportNumber(passport);
    }

    public Client findByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }

    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }
}
