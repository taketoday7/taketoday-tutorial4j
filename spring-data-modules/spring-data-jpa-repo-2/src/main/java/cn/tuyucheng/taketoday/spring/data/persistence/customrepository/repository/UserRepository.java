package cn.tuyucheng.taketoday.spring.data.persistence.customrepository.repository;

import cn.tuyucheng.taketoday.spring.data.persistence.customrepository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
}