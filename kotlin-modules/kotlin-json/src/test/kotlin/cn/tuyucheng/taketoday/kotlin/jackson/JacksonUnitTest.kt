package cn.tuyucheng.taketoday.kotlin.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JacksonUnitTest {
   // val mapper = jacksonObjectMapper()
   private val mapper = ObjectMapper().registerModule(KotlinModule())

   @Test
   fun whenSerializeMovie_thenSuccess() {
      val movie = cn.tuyucheng.taketoday.kotlin.jackson.Movie("Endgame", "Marvel", 9.2f)
      val serialized = mapper.writeValueAsString(movie)

      val json = """{"name":"Endgame","studio":"Marvel","rating":9.2}"""
      assertEquals(serialized, json)
   }

   @Test
   fun whenDeserializeMovie_thenSuccess() {
      val json = """{"name":"Endgame","studio":"Marvel","rating":9.2}"""
      // val movie: Movie =  mapper.readValue(json)
      val movie = mapper.readValue<cn.tuyucheng.taketoday.kotlin.jackson.Movie>(json)

      assertEquals(movie.name, "Endgame")
      assertEquals(movie.studio, "Marvel")
      assertEquals(movie.rating, 9.2f)
   }

   @Test
   fun whenMissingRequiredParameterOnDeserialize_thenFails() {
      val json = """{"studio":"Marvel","rating":9.2}"""
      val exception = assertThrows<MissingKotlinParameterException> { mapper.readValue<cn.tuyucheng.taketoday.kotlin.jackson.Movie>(json) }
      assertEquals("name", exception.parameter.name)
      assertEquals(String::class, exception.parameter.type.classifier)
   }

   @Test
   fun whenDeserializeMovieWithMissingValue_thenUseDefaultValue() {
      val json = """{"name":"Endgame","studio":"Marvel"}"""
      val movie: cn.tuyucheng.taketoday.kotlin.jackson.Movie = mapper.readValue(json)

      assertEquals(movie.name, "Endgame")
      assertEquals(movie.studio, "Marvel")
      assertEquals(movie.rating, 1f)
   }

   @Test
   fun whenSerializeMap_thenSuccess() {
      val map = mapOf(1 to "one", 2 to "two")
      val serialized = mapper.writeValueAsString(map)

      val json = """{"1":"one","2":"two"}"""
      assertEquals(serialized, json)
   }

   @Test
   fun whenDeserializeMap_thenSuccess() {
      val json = """{"1":"one","2":"two"}"""
      val aMap: Map<Int, String> = mapper.readValue(json)

      assertEquals(aMap[1], "one")
      assertEquals(aMap[2], "two")

      val sameMap = mapper.readValue<Map<Int, String>>(json)
      assertEquals(sameMap[1], "one")
      assertEquals(sameMap[2], "two")
   }

   @Test
   fun whenSerializeList_thenSuccess() {
      val movie1 = cn.tuyucheng.taketoday.kotlin.jackson.Movie("Endgame", "Marvel", 9.2f)
      val movie2 = cn.tuyucheng.taketoday.kotlin.jackson.Movie("Shazam", "Warner Bros", 7.6f)
      val movieList = listOf(movie1, movie2)
      val serialized = mapper.writeValueAsString(movieList)

      val json = """[{"name":"Endgame","studio":"Marvel","rating":9.2},{"name":"Shazam","studio":"Warner Bros","rating":7.6}]"""
      assertEquals(serialized, json)
   }

   @Test
   fun whenDeserializeList_thenSuccess() {
      val json = """[{"name":"Endgame","studio":"Marvel","rating":9.2},{"name":"Shazam","studio":"Warner Bros","rating":7.6}]"""
      val movieList: List<cn.tuyucheng.taketoday.kotlin.jackson.Movie> = mapper.readValue(json)

      val movie1 = cn.tuyucheng.taketoday.kotlin.jackson.Movie("Endgame", "Marvel", 9.2f)
      val movie2 = cn.tuyucheng.taketoday.kotlin.jackson.Movie("Shazam", "Warner Bros", 7.6f)
      assertTrue(movieList.contains(movie1))
      assertTrue(movieList.contains(movie2))

      val sameList = mapper.readValue<List<cn.tuyucheng.taketoday.kotlin.jackson.Movie>>(json)
      assertTrue(sameList.contains(movie1))
      assertTrue(sameList.contains(movie2))
   }

   @Test
   fun whenSerializeBook_thenSuccess() {
      val book = cn.tuyucheng.taketoday.kotlin.jackson.Book("Oliver Twist", "Charles Dickens")
      val serialized = mapper.writeValueAsString(book)

      val json = """{"title":"Oliver Twist","author":"Charles Dickens"}"""
      assertEquals(serialized, json)
   }

   @Test
   fun whenDeserializeBook_thenSuccess() {
      val json = """{"title":"Oliver Twist","author":"Charles Dickens"}"""
      val book: cn.tuyucheng.taketoday.kotlin.jackson.Book = mapper.readValue(json)

      assertEquals(book.title, "Oliver Twist")
      assertEquals(book.authorName, "Charles Dickens")
   }

   @Test
   fun givenJsonInclude_whenSerializeBook_thenEmptyFieldExcluded() {
      val book = cn.tuyucheng.taketoday.kotlin.jackson.Book("Oliver Twist", "Charles Dickens")
      val serialized = mapper.writeValueAsString(book)

      val json = """{"title":"Oliver Twist","author":"Charles Dickens"}"""
      assertEquals(serialized, json)
   }

}