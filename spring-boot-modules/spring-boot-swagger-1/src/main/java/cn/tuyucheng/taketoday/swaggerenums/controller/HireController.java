package cn.tuyucheng.taketoday.swaggerenums.controller;

import cn.tuyucheng.taketoday.swaggerenums.model.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Api
@Path(value = "/hire")
@Produces({"application/json"})
public class HireController {

   @POST
   @ApiOperation(value = "This method is used to hire employee with a specific role")
   public String hireEmployee(@ApiParam(value = "role", required = true) Employee employee) {
      return STR."Hired for role: \{employee.role.name()}";
   }
}