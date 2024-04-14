package cn.tuyucheng.taketoday.persistence.service.common;

import cn.tuyucheng.taketoday.persistence.IOperations;
import com.google.common.collect.Lists;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

   // read - one

   @Override
   @Transactional(readOnly = true)
   public T findOne(final long id) {
      return getDao().findById(id).orElse(null);
   }

   // read - all

   @Override
   @Transactional(readOnly = true)
   public List<T> findAll() {
      return Lists.newArrayList(getDao().findAll());
   }

   // write

   @Override
   public T create(final T entity) {
      return getDao().save(entity);
   }

   @Override
   public T update(final T entity) {
      return getDao().save(entity);
   }

   protected abstract PagingAndSortingRepository<T, Long> getDao();
}