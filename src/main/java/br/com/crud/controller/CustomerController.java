package br.com.crud.controller;

import br.com.crud.controller.atualizar.AtualizarDatabase;
import br.com.crud.dto.CustomerDto;
import br.com.crud.model.CustomerModel;
import br.com.crud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/buscar-customer")
    public List<CustomerModel> buscarCustomer() {
        return customerRepository.findAll();
    }

    @PostMapping("cadastrar")
    public CustomerModel salvar(@RequestBody @Valid CustomerModel customerModel) {
       return customerRepository.save(customerModel);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable @Valid Long id) {
        Optional<CustomerModel> optional = customerRepository.findById(id);
        if(optional.isPresent()) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    @CacheEvict(value = "buscar-customer", allEntries = true)
    public ResponseEntity<CustomerDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarDatabase atualizarDatabase) {
        Optional<CustomerModel> optional = customerRepository.findById(id);
        if(optional.isPresent()) {
            CustomerModel customerModel = atualizarDatabase.atualizar(id, customerRepository);
            return ResponseEntity.ok(new CustomerDto(customerModel));
        }
        return ResponseEntity.notFound().build();
    }
}
