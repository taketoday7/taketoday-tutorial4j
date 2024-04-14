package cn.tuyucheng.taketoday.core.packages;

import cn.tuyucheng.taketoday.core.packages.domain.TodoItem;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackagesUnitTest {

   @Test
   public void whenTodoItemAdded_ThenSizeIncreases() {
      TodoItem todoItem = new TodoItem();
      todoItem.setId(1L);
      todoItem.setDescription("Test the Todo List");
      todoItem.setDueDate(LocalDate.now());
      TodoList todoList = new TodoList();
      todoList.addTodoItem(todoItem);
      assertEquals(1, todoList.getTodoItems().size());
   }
}
