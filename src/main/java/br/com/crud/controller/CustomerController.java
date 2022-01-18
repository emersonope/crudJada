package br.com.crud.controller;

import br.com.crud.dto.CustomerDto;
import br.com.crud.model.CustomerModel;
import br.com.crud.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<Page<CustomerModel>> findAll(@PageableDefault(page = 0, size = 5, sort = "cpf", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(customerService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerModel> salvar(@RequestBody @Valid CustomerDto dto) {
      CustomerModel model = new CustomerModel();
        BeanUtils.copyProperties(dto, model);
        customerService.saveCustomer(model);
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

//    @DeleteMapping("/deletar/{id}")
//    public ResponseEntity<?> deletar(@PathVariable @Valid Long id) {
//        Optional<CustomerModel> optional = customerRepository.findById(id);
//        if(optional.isPresent()) {
//            customerRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @PutMapping("/atualizar/{id}")
//    @Transactional
//    @CacheEvict(value = "buscar-customer", allEntries = true)
//    public ResponseEntity<CustomerDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarDatabase atualizarDatabase) {
//        Optional<CustomerModel> optional = customerRepository.findById(id);
//        if(optional.isPresent()) {
//            CustomerModel customerModel = atualizarDatabase.atualizar(id, customerRepository);
//            return ResponseEntity.ok(new CustomerDto(customerModel));
//        }
//        return ResponseEntity.notFound().build();
//    }
}
