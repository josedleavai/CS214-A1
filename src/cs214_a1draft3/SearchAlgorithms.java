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
public class SearchAlgorithms {
  // option 1
  public static int linearSearch(List<Article> articles, int id) {
    for (int i = 0; i < articles.size(); i++) {
      if (articles.get(i).getId() == id) {
        return i;
      }
    }
    return -1;
  }

  // option 2
  public static int binarySearch(List<Article> articles, int id) {
    int low = 0;
    int high = articles.size() - 1;
    int mid;

    while (low <= high) {
      mid = (low + high) / 2;

      if (articles.get(mid).getId() == id) {
        return mid;
      } else if (articles.get(mid).getId() < id) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return -1;
  }

  // option 3
  public static int sentinelLinearSearch(List<Article> articles, int id) {
    Article sentinel = new Article();
    sentinel.setId(-1); // Use a unique sentinel value

    articles.add(sentinel);

    for (int i = 0; articles.get(i).getId() != -1; i++) {
      if (articles.get(i).getId() == id) {
        return i;
      }
    }

    articles.remove(articles.size() - 1);
    return -1;
  }

  // option 4
  public static int jumpSearch(List<Article> articles, int id) {
    int n = articles.size();
    int step = (int) Math.floor(Math.sqrt(n));
    int prev = 0;

    while (articles.get(Math.min(step, n) - 1).getId() < id) {
      prev = step;
      step += (int) Math.floor(Math.sqrt(n));
      if (prev >= n) {
        return -1;
      }
    }

    while (articles.get(prev).getId() < id) {
      prev++;

      if (prev == Math.min(step, n)) {
        return -1;
      }
    }

    if (articles.get(prev).getId() == id) {
      return prev;
    }

    return -1;
  }

}