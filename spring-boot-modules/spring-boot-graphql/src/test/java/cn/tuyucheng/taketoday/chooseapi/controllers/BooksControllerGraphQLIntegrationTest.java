package cn.tuyucheng.taketoday.chooseapi.controllers;

import cn.tuyucheng.taketoday.chooseapi.ChooseApiApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.ActiveProfiles;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest(properties = {"grpc.server.port=-1"}, // Disable gRPC external server
      classes = ChooseApiApp.class)
@ActiveProfiles("chooseapi")
@AutoConfigureHttpGraphQlTester
class BooksControllerGraphQLIntegrationTest {

   @Autowired
   private HttpGraphQlTester graphQlTester;

   @Test
   void givenBooksServiceThatReturnThreeBooks_whenCallingGraphQLEndpoint_thenThreeBooksAreReturned() throws Exception {
      String document = "query { books { title year author { firstName lastName }}}";
      Path expectedResponse = Paths.get("src/test/resources/graphql-test/books_expected_response.json");
      String expectedJson = new String(Files.readAllBytes(expectedResponse));

      this.graphQlTester.document(document)
            .execute()
            .path("books")
            .matchesJson(expectedJson);
   }
}