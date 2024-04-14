package cn.tuyucheng.taketoday.nopropertyfound.repository;

import cn.tuyucheng.taketoday.nopropertyfound.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

   // findByFirsttName will cause Spring Data to throw PropertyReferenceException
   // Person findByFirsttName(String firstName);
   Person findByFirstName(String firstName);
}