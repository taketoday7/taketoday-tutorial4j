package cn.tuyucheng.taketoday.jsonpath.introduction;

import com.jayway.jsonpath.*;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceIntegrationTest {

   private final InputStream jsonInputStream = this.getClass()
         .getClassLoader()
         .getResourceAsStream("intro_service.json");

   private final String jsonString = new Scanner(jsonInputStream, StandardCharsets.UTF_8).useDelimiter("\\Z")
         .next();

   @Test
   void givenId_whenRequestingRecordData_thenSucceed() {
      Object dataObject = JsonPath.parse(jsonString)
            .read("$[?(@.id == 2)]");
      String dataString = dataObject.toString();

      assertThat(dataString, containsString("2"));
      assertThat(dataString, containsString("Quantum of Solace"));
      assertThat(dataString, containsString("Twenty-second James Bond movie"));
   }

   @Test
   void givenStarring_whenRequestingMovieTitle_thenSucceed() {
      List<Map<String, Object>> dataList = JsonPath.parse(jsonString)
            .read("$[?('Eva Green' in @['starring'])]");
      String title = (String) dataList.get(0)
            .get("title");

      assertEquals("Casino Royale", title);
   }

   @Test
   void givenCompleteStructure_whenCalculatingTotalRevenue_thenSucceed() {
      DocumentContext context = JsonPath.parse(jsonString);
      int length = context.read("$.length()");
      long revenue = 0;
      for (int i = 0; i < length; i++) {
         revenue += context.read("$[" + i + "]['box office']", Long.class);
      }

      assertEquals(594275385L + 591692078L + 1110526981L + 879376275L, revenue);
   }

   @Test
   void givenStructure_whenRequestingHighestRevenueMovieTitle_thenSucceed() {
      DocumentContext context = JsonPath.parse(jsonString);
      List<Object> revenueList = context.read("$[*]['box office']");
      Integer[] revenueArray = revenueList.toArray(new Integer[0]);
      Arrays.sort(revenueArray);

      int highestRevenue = revenueArray[revenueArray.length - 1];
      Configuration pathConfiguration = Configuration.builder()
            .options(Option.AS_PATH_LIST)
            .build();
      List<String> pathList = JsonPath.using(pathConfiguration)
            .parse(jsonString)
            .read("$[?(@['box office'] == " + highestRevenue + ")]");

      Map<String, String> dataRecord = context.read(pathList.get(0));
      String title = dataRecord.get("title");

      assertEquals("Skyfall", title);
   }

   @Test
   void givenDirector_whenRequestingLatestMovieTitle_thenSucceed() {
      DocumentContext context = JsonPath.parse(jsonString);
      List<Map<String, Object>> dataList = context.read("$[?(@.director == 'Sam Mendes')]");

      List<Object> dateList = new ArrayList<>();
      for (Map<String, Object> item : dataList) {
         Object date = item.get("release date");
         dateList.add(date);
      }
      Long[] dateArray = dateList.toArray(new Long[0]);
      Arrays.sort(dateArray);

      long latestTime = dateArray[dateArray.length - 1];
      List<Map<String, Object>> finalDataList = context.read("$[?(@['director'] == 'Sam Mendes' && @['release date'] == " + latestTime + ")]");
      String title = (String) finalDataList.get(0)
            .get("title");

      assertEquals("Spectre", title);
   }

   @Test
   public void givenJsonPathWithFilterPredicate_whenReadingRootNode_thenCorrect() {
      Filter directorSamMendesFilter = Filter.filter(Criteria.where("director")
            .contains("Sam Mendes"));
      List<Map<String, Object>> samMendesMovies = JsonPath.parse(jsonString)
            .read("$[?]['director']", directorSamMendesFilter);
      assertEquals(samMendesMovies.size(), 2);
   }
}