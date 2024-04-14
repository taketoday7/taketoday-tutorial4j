package cn.tuyucheng.taketoday;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/persons")
@ApplicationScoped
@Transactional(TxType.REQUIRED)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

   @PersistenceContext
   private EntityManager entityManager;

   @GET
   public List<Person> getAllPersons() {
      return entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
   }

   @GET
   @Path("/{id}")
   public Person getPersonById(@PathParam("id") Long id) {
      var personById = entityManager.find(Person.class, id);
      if (personById == null) {
         throw new NotFoundException();
      }
      return personById;
   }

   @POST
   public Response createNewPerson(@Context UriInfo uriInfo, @RequestBody Person personToStore) {
      entityManager.persist(personToStore);
      var headerLocation = uriInfo.getAbsolutePathBuilder()
            .path(personToStore.getId().toString())
            .build();
      return Response.created(headerLocation).build();
   }
}