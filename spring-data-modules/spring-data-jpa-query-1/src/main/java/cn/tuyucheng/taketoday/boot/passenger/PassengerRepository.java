package cn.tuyucheng.taketoday.boot.passenger;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PassengerRepository extends JpaRepository<Passenger, Long>, CustomPassengerRepository {

   Passenger findFirstByOrderBySeatNumberAsc();

   Passenger findTopByOrderBySeatNumberAsc();

   List<Passenger> findByOrderBySeatNumberAsc();

   // The Limit type is a new feature in Spring Data JPA version 3.2
   // List<Passenger> findByOrderBySeatNumberAsc(Limit limit);

   List<Passenger> findByFirstNameIgnoreCase(String firstName);

   List<Passenger> findByLastNameOrderBySeatNumberAsc(String lastName);

   List<Passenger> findByLastName(String lastName, Sort sort);
}