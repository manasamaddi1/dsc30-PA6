/*
 * Name: Lakshmi Manasa Maddi
 * PID:  A17735225
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Lakshmi Manasa Maddi
 * @since  5/14/24
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();


                // populate three trees with the information you just read
                populateHelper(movieTree, cast, movie);
                populateHelper(studioTree, studios, movie);
                populateHelper(ratingTree, cast, rating);
                // hint: create a helper function and reuse it to build all three trees

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    private static void populateHelper(BSTree tree, String[] values, String key) {

        for (int i = 0; i < values.length; i++) {

            String keylower = key.toLowerCase();

            if (tree.findKey(keylower) == false) {
                //if the key that we want to add does not exist, add it below
                tree.insert(keylower);
                LinkedList listvalues = new LinkedList();
                listvalues.add(values[i]);
                tree.insertData(keylower, listvalues);




            } else if (tree.findKey(keylower)) {
                //if you did find the key looking for and its not in the datalist already
                //add the value to the list
                LinkedList<String> datalist = tree.findDataList(keylower);
                if (datalist.contains(values[i]) == false) {
                    datalist.add(values[i]);
                }
            }
        }

    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        /* TODO */
        // process query
        String[] keys = query.toLowerCase().split(" ");

        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful

        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful

    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        /* TODO */
        // initialize search trees

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);

        // populate search trees

        // choose the right tree to query

    }
}
