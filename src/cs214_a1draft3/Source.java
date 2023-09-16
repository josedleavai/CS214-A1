/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs214_a1draft3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Josed D Leavai - S11176243
 * @author Taefalaula Brown - S11188253
 * @author David Palavipaongo - S11130156
 * 
 */
public class Source {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        List<Article> articleArrayList = new ArrayList<>();
        List<Article> articleLinkedList = new LinkedList<>();

        String fileName = "Article.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName));
                CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                int id = Integer.parseInt(csvRecord.get("ID"));
                String TITLE = csvRecord.get("TITLE");
                String ABSTRACT = csvRecord.get("ABSTRACT");
                int cs = Integer.parseInt(csvRecord.get("Computer Science"));
                int physics = Integer.parseInt(csvRecord.get("Physics"));
                int maths = Integer.parseInt(csvRecord.get("Mathematics"));
                int stats = Integer.parseInt(csvRecord.get("Statistics"));
                int quantBio = Integer.parseInt(csvRecord.get("Quantitative Biology"));
                int quantFinance = Integer.parseInt(csvRecord.get("Quantitative Finance"));

                Article article = new Article(id, TITLE, ABSTRACT, cs, physics, maths, stats,
                        quantBio, quantFinance);

                articleArrayList.add(article);
                articleLinkedList.add(article);
            }

            boolean exit = false;

            do {

                System.out.println("Select an option:");
                System.out.println("--------------------------------------");
                System.out.println("1. Search ArrayList");
                System.out.println("2. Search LinkedList");
                System.out.println("3. Print ArrayList contents");
                System.out.println("4. Print LinkedList contents");
                System.out.println("5. Exit ");
                System.out.println("--------------------------------------");
                System.out.println("6. (\'Question 2\') -> Race The Search Algorithms");
                System.out.println("// Linear, Binary, Sentinel Linear, Jump - Search Algorithms");
                System.out.println("7. (\'Question 3\') -> Empirical Testing");
                System.out.println("8. (\'Question 4\') -> Worst-Time Complexity");
                System.out.println("--------------------------------------");

                int choice = readIntInput(scanner, 1, 8);
                int searchAlgorithm;
                int searchTerm;
                int listType;

                // Terminate the program
                // ----------------------------------------------------------------
                if (choice == 5) {
                    System.out.println();
                    System.out.println("Exiting ...");
                    exit = true;
                }

                // Race the Algorithms
                // ----------------------------------------------------------------
                if (choice == 6) {
                    System.out.println();
                    System.out.println("Selected: \'Racing The Search Algorithms\'");
                    System.out.println("------------------------------------------");
                    System.out.println();

                    listType = getListType();

                    // Race arraylist
                    if (listType == 1) {
                        // Search ArrayList
                        System.out.println();
                        System.out.println("Selected ArrayList:");
                        System.out.println("-------------------");
                        System.out.println();

                        searchTerm = getSearchTerm(articleArrayList);

                        System.out.println();
                        Performance.raceTheAlgorithms(articleArrayList, searchTerm);
                    }

                    // Race linkedlist
                    else {
                        if (listType == 2) {
                            System.out.println();
                            System.out.println("Selected LinkedList:");
                            System.out.println("-------------------");
                            System.out.println();

                            searchTerm = getSearchTerm(articleLinkedList);

                            System.out.println();
                            Performance.raceTheAlgorithms(articleLinkedList, searchTerm);
                        }
                    }

                    System.out.println();
                }

                // Empirical Testing
                // ----------------------------------------------------------------
                if (choice == 7) {
                    System.out.println();
                    System.out.println("Selected \'Empirical Testing\'");
                    System.out.println("------------------------------");
                    System.out.println("// The key is randomly picked in every iteration");
                    System.out.println("// Each Algorithm is run 30 times & the results are collected to find:");
                    System.out.println("// the best, mean and worst solutions.");
                    System.out.println("// Using ArrayList due to its advantage in random access.");
                    System.out.println();

                    EmpiricalTesting.runTest(articleArrayList);
                    System.out.println();
                }

                if (choice == 8) {
                    System.out.println();

                    System.out.println("Selected \'Worst-Time Complexity Representation\'");
                    System.out.println("-------------------------------------------------");
                    System.out.println();

                    WorstTimeComplexity.launch(WorstTimeComplexity.class);

                    exit = true;
                }

                // ------------------------------------------------------------------------------------------------
                switch (choice) {
                    case 1:
                        // Search ArrayList
                        System.out.println();
                        System.out.println("Searching ArrayList:");
                        System.out.println("--------------------");
                        System.out.println();

                        // prompt user for which type of search algorithm to use
                        searchAlgorithm = getSearchAlgorithm();
                        printSearchType(searchAlgorithm);

                        // prompt user to enter id to search
                        searchTerm = getSearchTerm(articleArrayList);
                        performSearch(articleArrayList, searchAlgorithm, searchTerm);
                        break;

                    case 2:
                        // Search LinkedList
                        System.out.println();
                        System.out.println("Searching LinkedList:");
                        System.out.println("---------------------");
                        System.out.println();

                        // prompt user for which type of search algorithm to use
                        searchAlgorithm = getSearchAlgorithm();
                        printSearchType(searchAlgorithm);

                        // prompt user to enter id to search
                        searchTerm = getSearchTerm(articleLinkedList);
                        performSearch(articleLinkedList, searchAlgorithm, searchTerm);
                        break;

                    // done
                    case 3:
                        // Print ArrayList contents
                        System.out.println();
                        System.out.println("Printing contents of the ArrayList:");
                        System.out.println("-----------------------------------");
                        System.out.println();
                        printListContents(articleArrayList);
                        break;

                    case 4:
                        // Print LinkedList contents
                        System.out.println();
                        System.out.println("Printing contents of the LinkedList:");
                        System.out.println("------------------------------------");
                        System.out.println();
                        printListContents(articleLinkedList);
                        break;

                    default:
                        break;
                }

            } while (!exit);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

    }

    // Validation function 1
    // ----------------------------------------------------------------------------------------
    private static int readIntInput(Scanner scanner, int min, int max) {
        int choice;
        do {
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println();
                System.out.println("Invalid input. Please enter a number between (" + min + " and " + max + ")");
                System.out.print("Enter your choice: ");
                scanner.next(); // Clear the invalid input
            }
            choice = scanner.nextInt();

            if (choice < min || choice > max) {
                System.out.println();
                System.out.println("Invalid input. Please enter a number between (" + min + " and " + max + ")");
            }
        } while (choice < min || choice > max);
        return choice;
    }

    // Validation function 2
    // ----------------------------------------------------------------------------------------
    private static int readIntSearchTerm(Scanner scanner, int min, int max) {
        int id;
        do {
            System.out.print("Enter Search Term (ID): ");
            while (!scanner.hasNextInt()) {
                System.out.println();
                System.out.println("Invalid search term. Please enter an ID between (" + min + " and " + max + ")");
                System.out.print("Enter Search Term (ID): ");
                scanner.next(); // Clear the invalid input
            }
            id = scanner.nextInt();

            if (id < min || id > max) {
                System.out.println();
                System.out.println("Invalid search term. Please enter an ID between (" + min + " and " + max + ")");
            }
        } while (id < min || id > max);
        return id;
    }

    private static int getSearchAlgorithm() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a search algorithm:");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary Search");
        System.out.println("3. Sentinel Linear Search");
        System.out.println("4. Jump Search");

        int searchAlgorithmChoice = readIntInput(scanner, 1, 4);

        return searchAlgorithmChoice;
    }

    // Validation function 3
    // ----------------------------------------------------------------------------------------
    private static int getSearchTerm(List<Article> selectedList) {
        // To get the first id and last id to set the boundaries
        int minID = Integer.MAX_VALUE;
        int maxID = Integer.MIN_VALUE;

        for (Article article : selectedList) {
            int currentID = article.getId();
            if (currentID < minID) {
                minID = currentID;
            }
            if (currentID > maxID) {
                maxID = currentID;
            }
        }

        Scanner scanner = new Scanner(System.in);

        int searchTerm = readIntSearchTerm(scanner, minID, maxID);

        return searchTerm;
    }

    private static int getListType() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select which list to use:");
        System.out.println("1. ArrayList");
        System.out.println("2. LinkedList");

        int listType = readIntInput(scanner, 1, 2);

        return listType;
    }

    private static void printSearchType(int searchAlgo) {
        switch (searchAlgo) {
            case 1:
                System.out.println();
                System.out.println("Using Linear Search:");
                System.out.println("--------------------");
                System.out.println();
                break;

            case 2:
                System.out.println();
                System.out.println("Using Binary Search:");
                System.out.println("--------------------");
                System.out.println();
                break;

            case 3:
                System.out.println();
                System.out.println("Using Sentinel Linear Search:");
                System.out.println("-----------------------------");
                System.out.println();
                break;

            case 4:
                System.out.println();
                System.out.println("Using Jump Search:");
                System.out.println("------------------");
                System.out.println();
                break;

            default:
                break;
        }
    }

    private static void performSearch(List<Article> selectedList, int searchAlgorithmChoice, int searchTerm) {
        int articleIndex;

        // Only binary search needs the list to be sorted
        switch (searchAlgorithmChoice) {
            case 1:
                articleIndex = SearchAlgorithms.linearSearch(selectedList, searchTerm);
                break;

            case 2:
                // sort the list before invoking the methods
                selectedList.sort((article1, article2) -> Integer.compare(article1.getId(), article2.getId()));
                articleIndex = SearchAlgorithms.binarySearch(selectedList, searchTerm);
                break;

            case 3:
                articleIndex = SearchAlgorithms.sentinelLinearSearch(selectedList, searchTerm);
                break;

            case 4:
                articleIndex = SearchAlgorithms.jumpSearch(selectedList, searchTerm);
                break;

            default:
                System.out.println("Invalid search algorithm choice.");
                return;
        }

        if (articleIndex != -1) {
            Article foundArticle = selectedList.get(articleIndex);

            System.out.println();
            System.out.println("Article found at index: " + articleIndex);
            System.out.println("------------------------------");
            System.out.println("Article ID    : " + foundArticle.getId());
            System.out.println("Article Title : " + foundArticle.getTITLE());
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Oops! Article with the ID \'" + searchTerm + "\' does not exist!");
            System.out.println("-------------------------------------------------");
            System.out.println();
        }
    }

    private static void printListContents(List<Article> list) {
        for (Article article : list) {
            System.out.println("Article ID                   : " + article.id);
            System.out.println("Title                        : " + article.TITLE);
            // System.out.println("Abstract : " + article.ABSTRACT);
            System.out.println("Computer Science             : " + article.cs);
            System.out.println("Physics                      : " + article.physics);
            System.out.println("Mathematics                  : " + article.maths);
            System.out.println("Statistics                   : " + article.stats);
            System.out.println("Quantity Biology             : " + article.quantBio);
            System.out.println("Quantity Finance             : " + article.quantFinance);
            System.out.println();
        }
    }
}
