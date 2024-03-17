package com.embarkx.firstjobapp.company.impl;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyRepository;
import com.embarkx.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> currCompanyOptional =companyRepository.findById(id);
        if(currCompanyOptional.isPresent()){
            Company currCompany =currCompanyOptional.get();
            currCompany.setDescription(company.getDescription());
            currCompany.setName(company.getName());
            currCompany.setJobs(company.getJobs());
            companyRepository.save(currCompany);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        Optional<Company> currCompany =companyRepository.findById(id);
        if(currCompany.isPresent()){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> currCompanyOptional =companyRepository.findById(id);
        return currCompanyOptional.orElse(null);
    }
}
