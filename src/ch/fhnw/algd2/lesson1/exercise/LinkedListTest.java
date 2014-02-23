// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2.lesson1.exercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.Clock;

public class LinkedListTest {
    
    private ArrayList<AbstractLinkedList<Integer>> lists;
    
    @Before
    public void setUp() throws Exception {
        lists = new ArrayList<>();
        lists.add(new ch.fhnw.algd2.florianfankhauser.LinkedList<Integer>());
        lists.add(new ch.fhnw.algd2.romangribi.LinkedList<Integer>());
        lists.add(new ch.fhnw.algd2.christianguedel.LinkedList<Integer>());
        lists.add(new ch.fhnw.algd2.emanuelmistretta.LinkedList<Integer>());
        lists.add(new ch.fhnw.algd2.stephenrandles.LinkedList<Integer>());
        lists.add(new ch.fhnw.algd2.yannickaugstburger.LinkedList<Integer>());
        lists.add(new ch.fhnw.algd2.mariusdubach.LinkedList<Integer>());
        lists.add(new ch.fhnw.algd2.stephanbrunner.LinkedList<Integer>());
        lists.add(new ch.fhnw.algd2.luzius.LinkedList<Integer>()); 
        lists.add(new ch.fhnw.algd2.kevinwieser.LinkedList<Integer>());// add your own LinkedList implementation here
    }
    
    @Test
    public void testList(){
        for (AbstractLinkedList<Integer> list : lists) {
            for (int i=0; i<10000; i++){
                list.add(i);
            }
            Clock c1 = new Clock();
            for (int i=9999; i>=0; i--){
                list.remove(i);
            }
            c1.stop();
            for (int i=0; i<10000; i++){
                list.add(i);
            }
            Clock c2 = new Clock();
            for (int i=0; i<10000; i++){
                list.remove(i);
            }
            c2.stop();
            assertTrue(list.getClass().getName(), c1.tookMuchLongerThan(c2) || c2.tookMuchLongerThan(c1));
            
        }
    }
    
    @Test
    public void testStandardOps(){
        for (AbstractLinkedList<Integer> list : lists) {
            try {
                list.iterator().next();
                fail();
            } catch (NoSuchElementException e){
            }
            list.add(17);
            list.add(17);
            assertEquals(2, list.size());
            try {
                Iterator<Integer> iter = list.iterator();
                assertEquals(17, iter.next().intValue());
                iter.remove();
                iter.remove();
            } catch (IllegalStateException e){
            }
            assertEquals(1, list.size());
            list.clear();
            assertTrue(list.isEmpty());
        }
    }

}
