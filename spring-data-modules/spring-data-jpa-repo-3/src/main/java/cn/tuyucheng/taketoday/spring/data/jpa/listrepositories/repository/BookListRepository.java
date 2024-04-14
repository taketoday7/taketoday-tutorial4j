package cn.tuyucheng.taketoday.spring.data.jpa.listrepositories.repository;

import cn.tuyucheng.taketoday.spring.data.jpa.listrepositories.entity.Book;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookListRepository extends ListCrudRepository<Book, Long> {

   List<Book> findBooksByAuthor(String author);
}