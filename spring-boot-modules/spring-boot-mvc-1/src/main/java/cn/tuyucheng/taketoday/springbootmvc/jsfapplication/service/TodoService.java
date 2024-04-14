package cn.tuyucheng.taketoday.springbootmvc.jsfapplication.service;

import cn.tuyucheng.taketoday.springbootmvc.jsfapplication.model.Dao;
import cn.tuyucheng.taketoday.springbootmvc.jsfapplication.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;

@Scope(value = "session")
@Component(value = "todoService")
public class TodoService {

   @Autowired
   private Dao<Todo> todoDao;
   private Todo todo = new Todo();

   public void save() {
      todoDao.save(todo);
      todo = new Todo();
   }

   public Collection<Todo> getAllTodo() {
      return todoDao.getAll();
   }

   public Collection<Todo> getAllTodoSortedByPriority() {
      return todoDao.getAll()
            .stream()
            .sorted(Comparator.comparingInt(Todo::getId))
            .toList();
   }

   public int saveTodo(Todo todo) {
      validate(todo);
      return todoDao.save(todo);
   }

   private void validate(Todo todo) {
      // Details omitted
   }

   public Todo getTodo() {
      return todo;
   }
}