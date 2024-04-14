package cn.tuyucheng.taketoday.json;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class PersonBuilder {
   private String jsonString;

   private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

   public PersonBuilder(String jsonString) {
      this.jsonString = jsonString;
   }

   public Person build() throws IOException, ParseException {
      JsonReader reader = Json.createReader(new StringReader(jsonString));

      JsonObject jsonObject = reader.readObject();

      Person person = new Person();

      person.setFirstName(jsonObject.getString("firstName"));
      person.setLastName(jsonObject.getString("lastName"));
      person.setBirthdate(dateFormat.parse(jsonObject.getString("birthdate")));

      JsonArray emailsJson = jsonObject.getJsonArray("emails");

      List<String> emails = emailsJson.getValuesAs(JsonString.class).stream()
            .map(JsonString::getString)
            .collect(Collectors.toList());

      person.setEmails(emails);

      return person;
   }

}
