package com.corellana.bookStore.Person;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue
    public Integer id;
    @Basic
    @NotNull(message   = "The Rut field cannot be null")
    @NotBlank(message  = "The Rut field cannot be empty")
    @Size(min = 10, max = 10, message = "The Rut field must be 10 characters")
    @Column(unique = true)
    public String rut;
    @NotNull(message   = "The First Name field cannot be null")
    @NotBlank(message  = "The First Name field cannot be empty")
    @Size(min = 4, max = 30, message = "The First Name field must be between 4 and 30 characters")
    public String firstName;
    @NotNull(message   = "The Last Name Father field cannot be null")
    @NotBlank(message  = "The Last Name Father field cannot be empty")
    @Size(min = 4, max = 30, message = "The Last Name Father field must be between 4 and 30 characters")
    public String lastNameFather;
    @NotNull(message   = "The Last Name Mother field cannot be null")
    @NotBlank(message  = "The Last Name Mother field cannot be empty")
    @Size(min = 4, max = 30, message = "The Last Name Mother field must be between 4 and 30 characters")
    public String lastNameMother;
    @NotNull(message   = "The E-mail field cannot be null")
    @NotBlank(message  = "The E-mail field cannot be empty")
    @Size(min = 4, max = 50, message = "The E-mail field must be between 4 and 50 characters")
    @Column(unique = true)
    public String email;
    @NotNull(message   = "The Cellphone field cannot be null")
    @NotBlank(message  = "The Cellphone field cannot be empty")
    public String cellphone;
    @NotNull(message   = "The Status field cannot be null")
    public Boolean status;

}
