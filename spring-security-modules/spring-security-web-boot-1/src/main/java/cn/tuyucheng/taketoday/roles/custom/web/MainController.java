package cn.tuyucheng.taketoday.roles.custom.web;

import cn.tuyucheng.taketoday.roles.custom.persistence.dao.OrganizationRepository;
import cn.tuyucheng.taketoday.roles.custom.persistence.model.Foo;
import cn.tuyucheng.taketoday.roles.custom.persistence.model.Organization;
import cn.tuyucheng.taketoday.roles.custom.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

   @Autowired
   private OrganizationRepository organizationRepository;

   // @PostAuthorize("hasPermission(returnObject, 'read')")
   @PreAuthorize("hasPermission(#id, 'Foo', 'read')")
   @GetMapping("/foos/{id}")
   @ResponseBody
   public Foo findById(@PathVariable final long id) {
      return new Foo("Sample");
   }

   @PreAuthorize("hasPermission(#foo, 'write')")
   @PostMapping("/foos")
   @ResponseStatus(HttpStatus.CREATED)
   @ResponseBody
   public Foo create(@RequestBody final Foo foo) {
      return foo;
   }

   @PreAuthorize("hasAuthority('FOO_READ_PRIVILEGE')")
   @GetMapping("/foos")
   @ResponseBody
   public Foo findFooByName(@RequestParam final String name) {
      return new Foo(name);
   }

   @PreAuthorize("isMember(#id)")
   @GetMapping("/organizations/{id}")
   @ResponseBody
   public Organization findOrgById(@PathVariable final long id) {
      return organizationRepository.findById(id)
            .orElse(null);
   }

   @PreAuthorize("hasPermission(#id, 'Foo', 'read')")
   @GetMapping("/user")
   @ResponseBody
   public MyUserPrincipal retrieveUserDetails(@AuthenticationPrincipal MyUserPrincipal principal) {
      return principal;
   }
}
