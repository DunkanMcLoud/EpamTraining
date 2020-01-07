package ru.epam.javacore.lesson_12_io_nio.homework.common.solutions.utils;

import java.util.Collection;

public final class CollectionUtils {

  private CollectionUtils() {

  }

  public static void printCollection(Collection<?> collection) {
    for (Object obj : collection) {
        if(obj!=null){
      System.out.println(obj.toString());
    }}
  }

  public static boolean isNotEmpty(Collection<?> collection) {
    return collection != null && !collection.isEmpty();
  }

}
