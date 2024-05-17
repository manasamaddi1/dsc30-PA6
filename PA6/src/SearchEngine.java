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
     * @return false if file not found, true otherwise
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


    /**
     * Helper method for the PopulateSearchTrees method. It checks if there is an existing key
     * or not, and depending on that executes a certain part of the for loop to add the data
     * for a certain key into the tree
     * @return a boolean value representing if the Stack is empty. If the stack
     * is empty, then it should return false, true otherwise.
     */
    private static void populateHelper(BSTree<String> tree, String[] keys, String value) {

        for (int i = 0; i < keys.length; i++) {

            String keylower = keys[i].toLowerCase();

            if (!tree.findKey(keylower)) {
                //if the key that we want to add does not exist, add it below
                tree.insert(keylower);
                tree.insertData(keylower, value);



            } else {

                if (!tree.findDataList(keylower).contains(value)) {
                    tree.insertData(keylower, value);
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


        // process query
        String[] keys = query.toLowerCase().split(" ");

        LinkedList<String> finalList = new LinkedList<>();

        //intersection* of documents that contain all the words
        for (int i = 0; i < keys.length; i++) {

            if (searchTree.findKey(keys[i])) {

                //stores the linkedlists of data for each key
                LinkedList<String> items = searchTree.findDataList(keys[i]);

                //if there are items in the linkedlist
                if (items != null && items.isEmpty() == false) {
                    if (i == 0) {
                        //if the list is empty then add all the items that are in the key
                        finalList.addAll(items);
                    } else {
                        //only add items that repeat between both if there already exists items
                        finalList.retainAll(items);
                    }
                }
            }
        }

        print(query, finalList);



        for (int i = 0; i < keys.length; i++) {

            if (searchTree.findKey(keys[i])) {

            //stores the linkedlists of data for each key
            LinkedList<String> items = searchTree.findDataList(keys[i]);

            items.removeAll(finalList);


            //if there are items in the linkedlist

                if (!items.isEmpty()) {
                    //if the list is empty then add all the items that are in the key
                    print(keys[i], items);
                    finalList.addAll(items);
                }
            } else {
                print(keys[i], null);
            }

        }






//        if (!finalList.isEmpty()) {
//
//            break;
//        }






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
            System.out.println("Documents related to " + query + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        BSTree<String> movieTree = new BSTree<String>();
        BSTree<String> studioTree = new BSTree<String>();
        BSTree<String> ratingTree = new BSTree<String>();




        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);

        // populate search trees
       //System.out.println(populateSearchTrees(movieTree, studioTree, ratingTree, fileName));

        if (populateSearchTrees(movieTree, studioTree, ratingTree, fileName)) {

            //create a for loop iterating through args[2] to the end

            String finalstr = new String();
            for (int i = 2; i < args.length; i++) {
                finalstr += (args[i]) + " ";
            }

            if (searchKind == 0) {
                searchMyQuery(movieTree, finalstr);
            } else if (searchKind == 1){
                searchMyQuery(studioTree,finalstr);
            } else {
                searchMyQuery(ratingTree, finalstr);
            }

        } else {
            System.out.println("File is not found");
        }


    }
}

