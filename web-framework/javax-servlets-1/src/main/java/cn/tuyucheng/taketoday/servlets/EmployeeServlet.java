package cn.tuyucheng.taketoday.servlets;

import cn.tuyucheng.taketoday.model.Employee;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "EmployeeServlet", urlPatterns = "/employeeServlet")
public class EmployeeServlet extends HttpServlet {

   private Gson gson = new Gson();

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException {

      Employee employee = new Employee(1, "Karan", "IT", 5000);
      String employeeJsonString = this.gson.toJson(employee);

      PrintWriter out = response.getWriter();
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      out.print(employeeJsonString);
      out.flush();
   }

}
