/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs214_a1draft3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Josed D Leavai - S11176243
 * @author Taefalaula Brown - S11188253
 * @author David Palavipaongo - S11130156
 * 
 */
public class EmpiricalTesting {
  public static void runTest(List<Article> articles) {
    // Sort the list of articles first
    Collections.sort(articles, Comparator.comparingInt(Article::getId));
    // Create a Random object
    Random random = new Random();

    // Generate a random key between 1 and 20972
    int min = 1;
    int max = 20972;
    int numberOfRuns = 30;
    int[] searchTerm = new int[numberOfRuns];

    // Initialize result tracking variables
    long[][] algorithmTimes = new long[numberOfRuns][4]; // One row per search term, one column per algorithm

    for (int i = 0; i < numberOfRuns; i++) {
      searchTerm[i] = random.nextInt(max - min + 1) + min;

      // Linear Search
      long linearSearchStartTime = System.nanoTime();
      SearchAlgorithms.linearSearch(articles, searchTerm[i]);
      long linearSearchEndTime = System.nanoTime();
      algorithmTimes[i][0] = linearSearchEndTime - linearSearchStartTime;

      // Binary Search
      long binarySearchStartTime = System.nanoTime();
      SearchAlgorithms.binarySearch(articles, searchTerm[i]);
      long binarySearchEndTime = System.nanoTime();
      algorithmTimes[i][1] = binarySearchEndTime - binarySearchStartTime;

      // Sentinel Linear Search
      long sentinelLinearSearchStartTime = System.nanoTime();
      SearchAlgorithms.sentinelLinearSearch(articles, searchTerm[i]);
      long sentinelLinearSearchEndTime = System.nanoTime();
      algorithmTimes[i][2] = sentinelLinearSearchEndTime - sentinelLinearSearchStartTime;

      // Jump Search
      long jumpSearchStartTime = System.nanoTime();
      SearchAlgorithms.jumpSearch(articles, searchTerm[i]);
      long jumpSearchEndTime = System.nanoTime();
      algorithmTimes[i][3] = jumpSearchEndTime - jumpSearchStartTime;
    }

    // Analyze results (e.g., find best, mean, worst, fastest and slowest algorithm)
    long[] bestTimes = new long[4];
    long[] worstTimes = new long[4];
    long[] totalExecutionTimes = new long[4];

    for (int i = 0; i < numberOfRuns; i++) {
      for (int j = 0; j < 4; j++) {
        bestTimes[j] = (i == 0) ? algorithmTimes[i][j] : Math.min(bestTimes[j], algorithmTimes[i][j]);
        worstTimes[j] = (i == 0) ? algorithmTimes[i][j] : Math.max(worstTimes[j], algorithmTimes[i][j]);
        totalExecutionTimes[j] += algorithmTimes[i][j];
      }
    }

    double[] meanTimes = new double[4];
    for (int j = 0; j < 4; j++) {
      meanTimes[j] = (double) totalExecutionTimes[j] / numberOfRuns;
    }

    System.out.println("// ST -> Search Time, Unit = nanoseconds");
    System.out.println("Iteration \tID \t\tLinear ST \tBinary ST \tSentinel ST \tJump ST");
    System.out.println("------------------------------------------------------------------------------------------");

    // Print the execution times for each algorithm for each search term
    for (int i = 0; i < numberOfRuns; i++) {
      System.out.println((i + 1) + "\t\t" + searchTerm[i] + "\t\t" + algorithmTimes[i][0] + "\t\t"
          + algorithmTimes[i][1] + "\t\t" + algorithmTimes[i][2] + "\t\t" + algorithmTimes[i][3]);

      System.out.println();
    }

    System.out.println("// Overall : ");
    System.out.println("< --------------------------------------------------------------------------- >");
    System.out.println("// NOTE: THE LIST IS ORDERED!");
    System.out.println();
    // Print or return analysis results
    for (int j = 0; j < 4; j++) {
      System.out.println("Best time for        : " + determineAlgorithmName(j) + " : " + bestTimes[j] + " nanoseconds");
      System.out
          .println("Worst time for       : " + determineAlgorithmName(j) + " : " + worstTimes[j] + " nanoseconds");
      System.out.println("Mean time for        : " + determineAlgorithmName(j) + " : " + meanTimes[j] + " nanoseconds");
      System.out.println();
    }

    int fastestAlgorithmIndex = 0;
    for (int j = 1; j < 4; j++) {
      if (bestTimes[j] < bestTimes[fastestAlgorithmIndex]) {
        fastestAlgorithmIndex = j;
      }
    }

    System.out.println("-> Fastest algorithm : " + determineAlgorithmName(fastestAlgorithmIndex));
    System.out.println();
    System.out.println("< --------------------------------------------------------------------------- >");

    /*
     * // Estimate worst-time complexities
     * String linearComplexity = estimateComplexity(worstTimes[0]);
     * String binaryComplexity = estimateComplexity(worstTimes[1]);
     * String sentinelComplexity = estimateComplexity(worstTimes[2]);
     * String jumpComplexity = estimateComplexity(worstTimes[3]);
     * 
     * // Print estimated worst-time complexities
     * System.out.println("Estimated Worst-Time Complexities:");
     * System.out.println("Linear Search: " + linearComplexity);
     * System.out.println("Binary Search: " + binaryComplexity);
     * System.out.println("Sentinel Linear Search: " + sentinelComplexity);
     * System.out.println("Jump Search: " + jumpComplexity);
     */
  }

  // Method to determine the algorithm name based on index
  private static String determineAlgorithmName(int algorithmIndex) {
    switch (algorithmIndex) {
      case 0:
        return "Linear Search --------";
      case 1:
        return "Binary Search --------";
      case 2:
        return "Sentinel Linear Search";
      case 3:
        return "Jump Search ----------";
      default:
        return "-- N/A --";
    }
  }

  private static String estimateComplexity(long executionTime) {
    if (executionTime <= 0) {
      return "Unknown";
    }

    if (executionTime <= 100) {
      return "O(1)"; // Constant time
    } else if (executionTime <= 1000) {
      return "O(log n)"; // Logarithmic time
    } else if (executionTime <= 10000) {
      return "O(n)"; // Linear time
    } else if (executionTime <= 1000000) {
      return "O(n log n)"; // Linearithmic time
    } else {
      return "O(n^2) or worse"; // Quadratic time or worse
    }
  }
}
