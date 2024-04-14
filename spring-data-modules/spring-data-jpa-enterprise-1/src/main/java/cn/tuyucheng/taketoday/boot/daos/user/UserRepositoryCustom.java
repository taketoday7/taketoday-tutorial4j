package cn.tuyucheng.taketoday.boot.daos.user;

import cn.tuyucheng.taketoday.boot.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public interface UserRepositoryCustom {
   List<User> findUserByEmails(Set<String> emails);

   List<User> findAllUsersByPredicates(Collection<Predicate<User>> predicates);
}