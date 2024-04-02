package com.javaspring.javajrinterview.company;

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

import com.javaspring.javajrinterview.company.dtos.CompanyCreateDTO;
import com.javaspring.javajrinterview.company.dtos.CompanyDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable UUID id) {
        CompanyEntity company = companyService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
        
        CompanyDTO companyDTO = modelMapper.map(company, CompanyDTO.class);
        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

   @PostMapping
    public ResponseEntity<CompanyDTO> registerCompany(@Valid @RequestBody CompanyCreateDTO payload) {
        CompanyDTO createdCompany = companyService.createCompany(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }
}
