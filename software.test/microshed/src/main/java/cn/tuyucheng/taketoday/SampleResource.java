package cn.tuyucheng.taketoday;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("sample")
@Produces(MediaType.TEXT_PLAIN)
public class SampleResource {

   @Inject
   @ConfigProperty(name = "message")
   private String message;

   @Inject
   @RestClient
   private QuoteRestClient quoteRestClient;

   @GET
   @Path("/message")
   public String getMessage() {
      return message;
   }

   @GET
   @Path("/quotes")
   public String getQuotes() {
      var quoteOfTheDayPointer = Json.createPointer("/contents/quotes/0/quote");
      return quoteOfTheDayPointer.getValue(quoteRestClient.getQuoteOfTheDay()).toString();
   }
}