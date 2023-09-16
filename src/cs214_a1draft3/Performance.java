/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs214_a1draft3;

import java.util.List;

/**
 *
 * @author Josed D Leavai - S11176243
 * @author Taefalaula Brown - S11188253
 * @author David Palavipaongo - S11130156
 * 
 */
public class Performance {
  public static void raceTheAlgorithms(List<Article> selectedList, int id) {
    // Linear Search Thread
    Thread linearThread = new Thread(() -> {
      long startTime = System.nanoTime();
      int articleIndex = SearchAlgorithms.linearSearch(selectedList, id);
      long endTime = System.nanoTime();
      long linearSearchTime = endTime - startTime;

      if (articleIndex != -1) {
        Article foundArticle = selectedList.get(articleIndex);
        System.out
            .println(
                "Linear Search \t\t\t: at index \'" + articleIndex + "\' : Title \' " + foundArticle.getTITLE()
                    + " \'");
      } else {
        System.out.println("Linear Search \t\t\t: Article not found!");
      }

      System.out.println("Linear search time \t\t: " + linearSearchTime + " nanoseconds");
    });

    // Binary Search Thread
    Thread binaryThread = new Thread(() -> {
      long startTime = System.nanoTime();
      int articleIndex = SearchAlgorithms.binarySearch(selectedList, id);
      long endTime = System.nanoTime();
      long binarySearchTime = endTime - startTime;

      if (articleIndex != -1) {
        Article foundArticle = selectedList.get(articleIndex);
        System.out
            .println(
                "Binary Search \t\t\t: at index \'" + articleIndex + "\' : Title \' " + foundArticle.getTITLE()
                    + " \'");
      } else {
        System.out.println("Binary Search \t\t\t: Article not found!");
      }

      System.out.println("Binary search time \t\t: " + binarySearchTime + " nanoseconds");
    });

    // Sentinel Linear Search Thread
    Thread sentinelLinearThread = new Thread(() -> {
      long startTime = System.nanoTime();
      int articleIndex = SearchAlgorithms.sentinelLinearSearch(selectedList, id);
      long endTime = System.nanoTime();
      long sentinelSearchTime = endTime - startTime;

      if (articleIndex != -1) {
        Article foundArticle = selectedList.get(articleIndex);
        System.out
            .println(
                "Sentinel Linear Search \t\t: at index \'" + articleIndex + "\' : Title \' " + foundArticle.getTITLE()
                    + " \'");
      } else {
        System.out.println("Sentinel Linear Search \t: Article not found!");
      }

      System.out.println("Sentinel linear search time \t: " + sentinelSearchTime + " nanoseconds");
    });

    // Jump Search Thread
    Thread jumpThread = new Thread(() -> {
      long startTime = System.nanoTime();
      int articleIndex = SearchAlgorithms.jumpSearch(selectedList, id);
      long endTime = System.nanoTime();
      long jumpSearchTime = endTime - startTime;

      if (articleIndex != -1) {
        Article foundArticle = selectedList.get(articleIndex);
        System.out
            .println(
                "Jump Search \t\t\t: at index \'" + articleIndex + "\' : Title \' " + foundArticle.getTITLE() + " \'");
      } else {
        System.out.println("Jump Search \t\t\t: Article not found!");
      }

      System.out.println("Jump search time \t\t: " + jumpSearchTime + " nanoseconds");
    });

    linearThread.start();
    binaryThread.start();
    sentinelLinearThread.start();
    jumpThread.start();

    try {
      linearThread.join();
      binaryThread.join();
      sentinelLinearThread.join();
      jumpThread.join();

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
