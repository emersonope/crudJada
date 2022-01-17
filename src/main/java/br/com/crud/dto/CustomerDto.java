package br.com.crud.dto;

import br.com.crud.model.CustomerModel;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CustomerDto {

    @NotBlank
    private Long id;

    @CPF
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    private Long telefone;

    public CustomerDto(CustomerModel customerModel) {

        this.id = customerModel.getId();
        this.cpf = customerModel.getCpf();
        this.nome = customerModel.getNome();
        this.telefone = customerModel.getTelefone();
    }
}
