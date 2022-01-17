package br.com.crud.controller.atualizar;

import br.com.crud.model.CustomerModel;
import br.com.crud.repository.CustomerRepository;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AtualizarDatabase {


    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    private Long telefone;

    public CustomerModel atualizar(Long id, CustomerRepository customerRepository) {
        CustomerModel customerModel = customerRepository.getById(id);

        customerModel.setNome(this.nome);
        customerModel.setTelefone(this.telefone);

        return customerModel;
    }
}
