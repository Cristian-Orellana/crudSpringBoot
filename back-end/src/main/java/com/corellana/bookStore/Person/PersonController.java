package com.corellana.bookStore.Person;
//import org.hibernate.mapping.List;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corellana.bookStore.Person.Dto.MakerDto;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    
    private final PersonService personService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPerson(@Valid @RequestBody Person person){

        List<Person> personRut  =  personService.findPersonSearchRut(person.rut);
        
        Map<String, Object> response = new HashMap<>();
        boolean success              = false;
        String  message              = "Error saving data";
        
        if(personRut.isEmpty()){
            List<Person> personEmail =  personService.findPersonSearchEmail(person.email);
            if(personEmail.isEmpty()){
                success  =   personService.createPerson(person);
            }else{
                message  =   "There is already a record with the E-mail entered";
            }
          
        }else{
            message  =   "There is already a record with the RUT entered";
        }


         if (success) {
            response.put("message", "Data saved successfully");
            response.put("success", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", message);
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePerson(@Valid @RequestBody Person person){

        List<Person> personEmail     =  personService.findPersonSearchEmail(person.email);
        boolean success              = false;
        Map<String, Object> response = new HashMap<>();
        success  =   personService.createPerson(person);
        
        if(personEmail.isEmpty()){
            success  =   personService.createPerson(person);
        }else{
            Person firstPersonWithEmail = personEmail.get(0);

            if (firstPersonWithEmail.getId().equals(person.getId())) {
                success = personService.createPerson(person);
            } else {

                response.put("message", "There is already a record with the same email but different RUT");
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
        }

         if (success) {
            response.put("message", "Data saved successfully");
            response.put("success", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Error saving data");
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllPerson(){
        Map<String, Object> response = new HashMap<>();
        List<MakerDto> makerList = personService.findAllPerson()
            .stream()
            .map(Person -> MakerDto.builder()
            .id(Person.getId())
            .rut(Person.getRut())
            .firstName(Person.getFirstName())
            .lastNameFather(Person.getLastNameFather())
            .lastNameMother(Person.getLastNameMother())
            .email(Person.getEmail())
            .cellphone(Person.getCellphone())
            .status(Person.getStatus())
            .build())
            .toList();
            
        response.put("data", makerList);
        response.put("success", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search-id/{id}")
    public ResponseEntity<?> searchPersonId(@PathVariable Integer id){
       Optional<Person> personOptional =  personService.searchPersonId(id);
       Map<String, Object> response = new HashMap<>();
       if(personOptional.isPresent()){
        Person person = personOptional.get();
        MakerDto makerDto = MakerDto.builder()
              .id(person.getId())
              .rut(person.getRut())
              .firstName(person.getFirstName())
              .lastNameFather(person.getLastNameFather())
              .lastNameMother(person.getLastNameMother())
              .email(person.getEmail())
              .cellphone(person.getCellphone())
              .status(person.getStatus())
              .build();
        response.put("data", makerDto);
        response.put("success", true);
        return ResponseEntity.ok(response);
       }else{
       
        response.put("message", "No se han encontrado datos en la tabla persona para el id ingresado");
        response.put("success", false);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
       }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Integer id){
        boolean success  = false;
        success  =   personService.deletePerson(id);
        Map<String, Object> response = new HashMap<>();
        if(success){
            response.put("message", "The Client was successfully removed from the Database");
            response.put("success", true);
        }else{
            response.put("message", "An error occurred while trying to delete the Client from the Database");
            response.put("success", false);
        }
        return ResponseEntity.ok(response);
    }
    


}
