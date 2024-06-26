/*
 * Name: Lakshmi Manasa Maddi
 * PID:  A17735225
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Lakshmi Manasa Maddi
 * @since  5/10/2024
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    /**
     * A protected class where variables are initialized
     */
    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this(left, right, new LinkedList<>(), key);
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {

            if (dataList.contains(data)) {
                dataList.remove(data);
                return true;
            }

            return false;

        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        root = null;
        nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {

        if (root == null) {
            return null;
        }

        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * @param key a T type that represents what needs to be inside the node
     * @return true if insertion is successful and false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean insert(T key) {

        if (key == null) {
            throw new NullPointerException("the key is null");
        }

        if (this.root == null) {
            root = new BSTNode(null, null, key);
            nelems += 1;
            return true;


        }

        if (insertHelper(root, key)) {
            nelems = nelems + 1;
            return true;
        }

        return false;
    }


    /**
     * Helper for the insertData method
     *
     * @param currRoot the current node
     * @param key the number that is referenced when attempting to access a node
     * @return a boolean value representing if you were able to successfully insert
     * the key into the BST
     */
    private boolean insertHelper(BSTNode currRoot, T key) {

        int num = key.compareTo(currRoot.getKey());

        if (num == 0) {
            return false;
        }

        if (num < 0) {
            if (currRoot.getLeft() == null) {
                currRoot.setLeft(new BSTNode(null, null, key));
                return true;
            }
            else {
                insertHelper(currRoot.getLeft(), key);
            }
        }
        else if (num > 0) {
            if (currRoot.getRight() == null) {
                currRoot.setRight(new BSTNode(null, null, key));
                return true;
            }
            else {
                return insertHelper(currRoot.getRight(),key);
            }
        }
        return true;

    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException("The key provided cannot be null");
        }


        return findKeyHelper(root, key);
    }

    /**
     * Helper for the insertData method
     *
     * @param currRoot the current node
     * @param key the number that is referenced when attempting to access a node
     * @return a boolean value representing if you have found the specified key or not
     */
    private boolean findKeyHelper(BSTNode currRoot, T key) {

        if (currRoot == null) {
            return false;

        }

        int num = key.compareTo(currRoot.getKey());

        if (num < 0) {
            return findKeyHelper(currRoot.getLeft(), key);

        }
        else if (num > 0) {
            return findKeyHelper(currRoot.getRight(),key);
        }
        else {
            return true;
        }

    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {

        if (key == null || data == null) {
            throw new NullPointerException("The key is null");
        }

        if (findKey(key) == false) {
            throw new IllegalArgumentException("the key is not found in BST");
        } else {
            this.insertDataHelper(root, key, data);
        }
    }

    /**
     * Helper for the insertData method
     *
     * @param currNode the current node
     * @param key the number that is referenced when attempting to access a node
     * @param data the data that is it be inserted in a certain node
     */

    private void insertDataHelper(BSTNode currNode, T key, T data) {

        int num = key.compareTo(currNode.getKey());

        if (num < 0) {
            insertDataHelper(currNode.getLeft(), key, data);
        }
        else if (num > 0) {
            insertDataHelper(currNode.getRight(), key, data);
        }
        else { //condition if there is an existing node
            currNode.addNewInfo(data);
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {

        if (key == null) {
            throw new NullPointerException("The key is null");
        }

        if (!findKey(key)) {
            throw new IllegalArgumentException("the key is not found in BST");
        }

        return findDataListHelper(root, key);
    }


    /**
     * Helper for the findDataList method
     *
     * @param currNode the current node
     * @param key the number that is referenced when attempting to access a node
     * @return a LinkedList of the node with the specified key
     */
    private LinkedList<T> findDataListHelper(BSTNode currNode, T key) {

        if (currNode == null) {
            return null;
        }

        int num = key.compareTo(currNode.getKey());

        if (num < 0) {
            return findDataListHelper(currNode.getLeft(), key);
        }
        else if (num > 0) {
            return findDataListHelper(currNode.getRight(), key);
        }
        else { //condition if there is an existing node
            return currNode.getDataList();
        }


    }

    /**
     * Return the height of the tree
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {

        if (root == null) {
            return -1;
        }

        int lefttreenum = findHeightHelper(root.getLeft()); //find the maximum from both sides
        int righttreenum = findHeightHelper(root.getRight());

        return Math.max(lefttreenum, righttreenum) + 1;


    }


    /* * * * * BST Iterator * * * * */


    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */

    public class BSTree_Iterator implements Iterator<T> {

        private Stack<BSTNode> treestack;



        /**
         * A constructor that initializes the Stack with the leftPath of the root
         */
        public BSTree_Iterator() {
            treestack = new Stack<>();
            BSTNode currNode = root;

            while (currNode != null ) {
                treestack.push(currNode);
                //reset currNode to being the left path
                currNode = currNode.getLeft();
            }

        }


        /**
         * Method used to determine if there are nodes left to iterate through to
         * push onto the stack.
         * @return a boolean value representing if the Stack is empty. If the stack
         * is empty, then it should return false, true otherwise.
         */

        public boolean hasNext() {
            return (!treestack.isEmpty());
        }

        /**
         * Method that is used to return the next item that there is in the BST
         * @return the item that is next in the BST
         * @throws NoSuchElementException if there is no next item within the stack
         */
        public T next() {

            if (hasNext() == false) {
                throw new NoSuchElementException("there are no more elements to iterate through");
            }

            //to return in stacks, you have to pop elements of the stack


            BSTNode currNode = treestack.pop();
            BSTNode temp = currNode.getRight();

            if (temp != null) {

                treestack.push(temp);


                while (temp.getLeft() != null) {
                    temp = temp.getLeft();
                    treestack.push(temp);
                }

                return currNode.getKey();


            }

            return currNode.getKey();

        }
    }


    /**
     * Method created to iterate through the BSTree
     * @return an iterator object
     */
    public Iterator<T> iterator() {

        return new BSTree_Iterator();
    }







    /* * * * * Extra Credit Methods * * * * */

//    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
//        /* TODO */
//        return null;
//    }
//
//    public T levelMax(int level) {
//        /* TODO */
//        return null;
//    }
}
