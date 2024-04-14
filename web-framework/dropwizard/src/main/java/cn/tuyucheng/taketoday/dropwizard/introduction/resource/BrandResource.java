package cn.tuyucheng.taketoday.dropwizard.introduction.resource;

import cn.tuyucheng.taketoday.dropwizard.introduction.domain.Brand;
import cn.tuyucheng.taketoday.dropwizard.introduction.repository.BrandRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/brands")
@Produces(MediaType.APPLICATION_JSON)
public class BrandResource {
   private final int defaultSize;
   private final BrandRepository brandRepository;

   public BrandResource(final int defaultSize, final BrandRepository brandRepository) {
      this.defaultSize = defaultSize;
      this.brandRepository = brandRepository;

   }

   @GET
   public List<Brand> getBrands(@QueryParam("size") final Optional<Integer> size) {
      return brandRepository.findAll(size.orElse(defaultSize));
   }

   @GET
   @Path("/{id}")
   public Brand getById(@PathParam("id") final Long id) {
      return brandRepository
            .findById(id)
            .orElseThrow(RuntimeException::new);
   }
}
