package com.corellana.bookStore.Person;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRep;
    @SuppressWarnings("unused")
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public boolean createPerson(Person person) {
        if(person != null){
            try {
                personRep.save(person);
                return true; 
            } catch (Exception e) {
                return false; 
            }
        }else{
            return false;
        }
    }

    public boolean deletePerson(Integer id) {
        if(id != null){
            try {
                personRep.deleteById(id);
                return true; 
            } catch (Exception e) {
                return false; 
            }
        }else{
            return false;
        }
    }
    
    //@Override
    public Optional<Person> searchPersonId(Integer Id){
        return personRep.findById(Id);
    }

    public List<Person> findAllPerson(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return (List<Person>) personRep.findAll(sort);
        //return (List<Person>) personRep.findAll();
    }

    public List<Person> findPersonSearchRut(String rut){
        return  personRep.findPersonRut(rut);
    }

    public List<Person> findPersonSearchEmail(String email){
        return  personRep.findPersonEmail(email);
    }



}



