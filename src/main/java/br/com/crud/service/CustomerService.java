package br.com.crud.service;

import br.com.crud.model.CustomerModel;
import br.com.crud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Page<CustomerModel> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public CustomerModel saveCustomer(CustomerModel model) {
        return customerRepository.save(model);
    }
}
