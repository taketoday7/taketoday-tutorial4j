package cn.tuyucheng.taketoday.persistence.service.impl;

import cn.tuyucheng.taketoday.persistence.dao.IFooDao;
import cn.tuyucheng.taketoday.persistence.model.Foo;
import cn.tuyucheng.taketoday.persistence.service.IFooService;
import cn.tuyucheng.taketoday.persistence.service.common.AbstractService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FooService extends AbstractService<Foo> implements IFooService {

   @Autowired
   private IFooDao dao;

   public FooService() {
      super();
   }

   @Override
   protected JpaRepository<Foo, Long> getDao() {
      return dao;
   }

   @Override
   public Page<Foo> findPaginated(Pageable pageable) {
      return dao.findAll(pageable);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Foo> findAll() {
      return Lists.newArrayList(getDao().findAll());
   }
}