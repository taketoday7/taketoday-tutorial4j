package cn.tuyucheng.taketoday.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddElementsUnitTest {

   List<Flower> flowers;

   @BeforeEach
   public void init() {
      this.flowers = new ArrayList<>(Arrays.asList(
            new Flower("Poppy", 12),
            new Flower("Anemone", 8),
            new Flower("Catmint", 12)));
   }

   @Test
   public void givenAList_whenTargetListIsEmpty_thenReturnTargetListWithNewItems() {
      List<Flower> anotherList = new ArrayList<>();
      anotherList.addAll(flowers);

      assertEquals(anotherList.size(), flowers.size());
      Assertions.assertTrue(anotherList.containsAll(flowers));
   }

   @Test
   public void givenAList_whenTargetListIsEmpty_thenReturnTargetListWithOneModifiedElementByConstructor() {
      List<Flower> anotherList = new ArrayList<>();
      anotherList.addAll(flowers);

      Flower flower = anotherList.get(0);
      flower.setPetals(flowers.get(0).getPetals() * 3);

      assertEquals(anotherList.size(), flowers.size());
      Assertions.assertTrue(anotherList.containsAll(flowers));
   }

   @Test
   public void givenAListAndElements_whenUseCollectionsAddAll_thenAddElementsToTargetList() {
      List<Flower> target = new ArrayList<>();

      Collections.addAll(target, flowers.get(0), flowers.get(1), flowers.get(2), flowers.get(0));

      assertEquals(target.size(), 4);
   }

   @Test
   public void givenTwoList_whenSourceListDoesNotHaveNullElements_thenAddElementsToTargetListSkipFirstElementByStreamProcess() {
      List<Flower> flowerVase = new ArrayList<>();

      flowers.stream()
            .skip(1)
            .forEachOrdered(flowerVase::add);

      assertEquals(flowerVase.size() + 1, flowers.size());
      assertFalse(flowerVase.containsAll(flowers));
   }

   @Test
   public void givenTwoList_whenSourceListDoesNotHaveNullElements_thenAddElementsToTargetListFilteringElementsByStreamProcess() {
      List<Flower> flowerVase = new ArrayList<>();

      flowers.stream()
            .filter(f -> f.getPetals() > 10)
            .forEachOrdered(flowerVase::add);

      assertEquals(flowerVase.size() + 1, flowers.size());
      assertFalse(flowerVase.containsAll(flowers));
   }

   @Test
   public void givenAList_whenListIsNotNull_thenAddElementsToListByStreamProcessWihtOptional() {
      List<Flower> target = new ArrayList<>();

      Optional.ofNullable(flowers)
            .ifPresent(target::addAll);

      assertNotNull(target);
      assertEquals(target.size(), 3);
   }
}
