package com.corellana.bookStore.Person.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakerDto {
    //Data Transfer Object
    public Integer id;
    public String rut;
    public String firstName;
    public String lastNameFather;
    public String lastNameMother;
    public String email;
    public String cellphone;
    public Boolean status;
}
