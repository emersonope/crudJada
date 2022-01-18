package br.com.crud.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "customer")
public class CustomerModel {

    @Id
    @CPF
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    private String telefone;

}
