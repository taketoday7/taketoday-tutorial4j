package cn.tuyucheng.taketoday.persistence;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {

   // read - one

   T findById(final long id);

   // read - all

   List<T> findAll();

   Page<T> findPaginated(int page, int size);

   // write

   T create(final T entity);

   T update(final T entity);

   void delete(final T entity);

   void deleteById(final long entityId);
}
