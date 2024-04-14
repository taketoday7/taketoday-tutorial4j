package cn.tuyucheng.taketoday.activiti.security.config;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProcessController {

   @Autowired
   private RuntimeService runtimeService;

   @Autowired
   private TaskService taskService;

   @GetMapping("/protected-process")
   public String startProcess() {
      ProcessInstance pi = runtimeService.startProcessInstanceByKey("protected-process");

      List<Task> usertasks = taskService.createTaskQuery()
            .processInstanceId(pi.getId())
            .list();

      taskService.complete(usertasks.iterator()
            .next()
            .getId());

      return STR."Process started. Number of currently running process instances = \{runtimeService.createProcessInstanceQuery().count()}";
   }
}