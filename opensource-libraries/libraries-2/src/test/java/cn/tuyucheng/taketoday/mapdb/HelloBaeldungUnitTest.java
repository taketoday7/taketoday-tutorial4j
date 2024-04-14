package cn.tuyucheng.taketoday.mapdb;

import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import static junit.framework.Assert.assertEquals;

public class HelloBaeldungUnitTest {

   @Test
   public void givenInMemoryDBInstantiateCorrectly_whenDataSavedAndRetrieved_checkRetrievalCorrect() {

      DB db = DBMaker.memoryDB().make();

      String welcomeMessageKey = "Welcome Message";
      String welcomeMessageString = "Hello Tuyucheng!";

      HTreeMap myMap = db.hashMap("myMap").createOrOpen();
      myMap.put(welcomeMessageKey, welcomeMessageString);

      String welcomeMessageFromDB = (String) myMap.get(welcomeMessageKey);

      db.close();

      assertEquals(welcomeMessageString, welcomeMessageFromDB);
   }

   @Test
   public void givenInFileDBInstantiateCorrectly_whenDataSavedAndRetrieved_checkRetrievalCorrect() {

      DB db = DBMaker.fileDB("file.db").make();

      String welcomeMessageKey = "Welcome Message";
      String welcomeMessageString = "Hello Tuyucheng!";

      HTreeMap myMap = db.hashMap("myMap").createOrOpen();
      myMap.put(welcomeMessageKey, welcomeMessageString);

      String welcomeMessageFromDB = (String) myMap.get(welcomeMessageKey);

      db.close();

      assertEquals(welcomeMessageString, welcomeMessageFromDB);
   }
}
