import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTest {

    BSTree<Integer> tree1;

    BSTree<Integer> tree2;

    BSTree<Integer> tree3;

    Iterator<Integer> iter1;
    Iterator<Integer> iter2;
    Iterator<Integer> iter3;


    @BeforeEach
    public void setup() {

        tree1 = new BSTree<>();
        tree2 = new BSTree<>();
        tree3 = new BSTree<>();
        iter1 = tree1.iterator();
        iter2 = tree2.iterator();
        iter3 = tree3.iterator();




    }


    @Test
    public void testBSTree() {
        assertNull(tree1.getRoot());
        assertEquals(0, tree1.getSize());
        assertTrue(0 == tree1.getSize());

    }

    @Test
    public void testBSTestgetRoot() {
        tree1.insert(30);
        tree1.insert(20);
        tree1.insert(70);
        tree1.insert(80);
        assertEquals(30, tree1.getRoot().getKey());
        tree1.insert(20);
        assertEquals(30, tree1.getRoot().getKey());
        tree2.insert(40);
        assertEquals(40, tree2.getRoot().getKey());


    }

    @Test
    public void testgetSize() {
        tree3.insert(20);
        tree3.insert(10);
        tree3.insert(12);
        assertEquals(3, tree3.getSize());
        tree2.insert(22);
        tree2.insert(44);
        assertEquals(2, tree2.getSize());
        tree1.insert(50);
        tree1.insert(15);
        assertEquals(2, tree1.getSize());


    }

    @Test
    public void testInsert() {
        tree1.insert(13);
        tree1.insert(21);
        tree1.insert(41);
        tree1.insert(35);
        assertEquals(4, tree1.getSize());
        BSTree.BSTNode node = tree1.getRoot();
        assertEquals(21, node.getRight().getKey());
        assertEquals(41, node.getRight().getRight().getKey());
        assertEquals(35, node.getRight().getRight().getLeft().getKey());
//        tree1.insert(35);
//        assertEquals(5, tree1.getSize());
//        tree1.insert(56);
//        assertEquals(6, tree1.getSize()); //not working for duplicates
//        assertEquals(true, tree1.insert(14));
//        assertEquals(false, tree1.insert(21));
//
//        assertThrows(NullPointerException.class, () -> {
//            tree2.insert(null);
//        });






    }


    @Test
    public void testfindkey() {
        tree2.insert(4);
        tree2.insert(7);
        tree2.insert(23);
        assertEquals(true, tree2.findKey(7));
        tree2.insert(5);
        assertEquals(true, tree2.findKey(23));
        tree1.insert(12);
        tree1.insert(17);
        assertTrue(!tree1.findKey(100));

        assertThrows(NullPointerException.class, () -> {
            tree2.insert(null);
        });


    }

    @Test
    public void testInsertData() {
        tree2.insert(50);
        tree2.insert(30);
        tree2.insert(90);
        tree2.insertData(50, 23);
        assertEquals(false, tree2.findKey(57));
        tree1.insert(90);
        tree1.insertData(90, 45);
        assertEquals(1, tree1.getSize());
        assertTrue(1 == tree1.getSize());

        assertThrows(NullPointerException.class, () -> {
            tree2.insert(null);
        });

        assertThrows(NullPointerException.class, () -> {
            tree1.insertData(90, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            tree2.insertData(120, 23);;
        });




    }

    @Test
    public void testfindDataList() {
        tree2.insert(40);
        tree2.insert(55);
        tree2.insert(78);
        tree2.insert(34);

        tree2.insertData(55, 45);
        LinkedList<Integer> list = tree2.findDataList(55);
        assertEquals(true, list.contains(45));

        tree2.insertData(78, 67);
        LinkedList<Integer> list1 = tree2.findDataList(78);
        assertEquals(true, list1.contains(67));

        tree2.insertData(40, 71);
        LinkedList<Integer> list2 = tree2.findDataList(40);
        assertEquals(true, list2.contains(71));

        assertThrows(NullPointerException.class, () -> {
            tree2.insert(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            tree2.insertData(154, 23);;
        });


    }

    @Test
    public void testfindHeight() {
        assertTrue(-1 == tree1.findHeight()); //add something larger and test
        tree1.insert(5);
        assertEquals(0, tree1.findHeight());
        tree1.insert(4);
        assertEquals(1, tree1.findHeight());
        tree1.insert(3);
        assertEquals(2, tree1.findHeight());
        tree1.insert(6);
        assertEquals(2, tree1.findHeight());


    }

    @Test
    public void testBSTreeIterator() {

        assertFalse(iter1.hasNext());
        tree1.insert(3);
        tree1.insert(23);
        iter1 = tree1.iterator();
        assertTrue(iter1.hasNext());
        assertEquals(23, (int)iter1.next());
    }

    @Test
    public void testHasNext() {
        assertFalse(iter2.hasNext());
        tree2.insert(50);
        tree2.insert(40);
        tree2.insert(12);
        tree2.insert(90);
        tree2.insert(34);
        iter2 = tree2.iterator();


        assertTrue(iter2.hasNext());
        assertEquals(34, (int)iter2.next());
        assertTrue(iter2.hasNext());
        assertEquals(40, (int)iter2.next());
        assertTrue(iter2.hasNext());
        assertEquals(90, (int)iter2.next());
        assertFalse(iter2.hasNext()); //has no more nodes to iterate through because
        //with each pop from the stack the successor takes over

    }

    @Test
    public void testNext() {
        assertFalse(iter2.hasNext());
        tree1.insert(23);
        tree1.insert(19);
        tree1.insert(37);
        iter1 = tree1.iterator();
        assertTrue(iter1.hasNext());
        assertEquals(19, (int)iter1.next());
        assertTrue(iter1.hasNext());
        assertEquals(37, (int)iter1.next());
        assertFalse(iter1.hasNext());

        tree2.insert(12);
        tree2.insert(13);
        tree2.insert(7);
        iter2 = tree2.iterator();
        assertTrue(iter2.hasNext());
        assertEquals(7, (int)iter2.next());
        tree2.insert(6);
        assertTrue(iter2.hasNext());
    }

}