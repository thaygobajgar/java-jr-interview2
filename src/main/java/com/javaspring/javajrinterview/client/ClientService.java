package com.javaspring.javajrinterview.client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaspring.javajrinterview.client.dtos.ClientCreateDTO;
import com.javaspring.javajrinterview.client.dtos.ClientDTO;
import com.javaspring.javajrinterview.exceptions.customExceptions.EmailAlreadyRegistered;
import com.javaspring.javajrinterview.exceptions.customExceptions.PhoneAlreadyRegistered;

@Service
public class ClientService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientEntity> findAllClients() {
        return clientRepository.findAll();
    }

    public Optional<ClientEntity> findById(UUID id) {
        return clientRepository.findById(id);
    }

    public ClientDTO createClient(ClientCreateDTO payload) {
        if (clientRepository.findByEmail(payload.getEmail()).isPresent()) {
            throw new EmailAlreadyRegistered(payload.getEmail());
        }
        if (clientRepository.findByPhone(payload.getPhone()).isPresent()) {
            throw new PhoneAlreadyRegistered(payload.getPhone());
        }


        ClientEntity clientEntity = modelMapper.map(payload, ClientEntity.class);
        ClientEntity savedClient = clientRepository.save(clientEntity);
        return modelMapper.map(savedClient, ClientDTO.class);
    }
    

}
