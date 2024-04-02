package com.javaspring.javajrinterview.company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaspring.javajrinterview.company.dtos.CompanyCreateDTO;
import com.javaspring.javajrinterview.company.dtos.CompanyDTO;

@Service
public class CompanyService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyEntity> findAllClients() {
        return companyRepository.findAll();
    }

    public Optional<CompanyEntity> findById(UUID id) {
        return companyRepository.findById(id);
    }

    public CompanyDTO createCompany(CompanyCreateDTO payload) {
        CompanyEntity companyEntity = modelMapper.map(payload, CompanyEntity.class);
        CompanyEntity savedClient = companyRepository.save(companyEntity);
        return modelMapper.map(savedClient, CompanyDTO.class);
    }
    

}
