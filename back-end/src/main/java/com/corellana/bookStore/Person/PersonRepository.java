package com.corellana.bookStore.Person;
import java.util.List;

//import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository <Person,Integer> {

    @Query(value ="select * from person p where p.rut = ?1", nativeQuery = true)
    List<Person> findPersonRut(String rut);

    @Query(value ="select * from person p where p.email = ?1", nativeQuery = true)
    List<Person> findPersonEmail(String email);

    //List<Person> findAll(Sort sort);
}
