package com.javaspring.javajrinterview.client;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.javaspring.javajrinterview.client.dtos.ClientCreateDTO;
import com.javaspring.javajrinterview.client.dtos.ClientDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable UUID id) {
        ClientEntity client = clientService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        
        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

   @PostMapping
    public ResponseEntity<ClientDTO> registerClient(@Valid @RequestBody ClientCreateDTO payload) {
        ClientDTO createdClient = clientService.createClient(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }
}
