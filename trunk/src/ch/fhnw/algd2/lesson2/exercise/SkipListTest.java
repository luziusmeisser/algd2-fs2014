// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2.lesson2.exercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.Util;

public class SkipListTest {
    
    private ArrayList<ISkipList<Integer>> lists;
    
    @Before
    public void setUp() throws Exception {
        lists = new ArrayList<>();
        lists.add(new ch.fhnw.algd2.romangribi.SkipList<Integer>());
        lists.add(new ch.fhnw.algd2.luzius.SkipList<Integer>());// add your own LinkedList implementation here
        lists.add(new ch.fhnw.algd2.florianfankhauser.lesson2.SkipList<Integer>());
        lists.add(new ch.fhnw.algd2.yannickaugstburger.SkipList<Integer>());
    }
    
    @Test
    public void test1000(){
        for (ISkipList<Integer> list : lists) {
            for (int i=0; i<1000; i++){
                list.add(i);
            }
            for (int i=0; i<1000; i++){
                int first = list.removeFirst();
                assertEquals(first, i);
            }
        }
    }
    
    @Test
    public void testRandom(){
        for (ISkipList<Integer> list : lists) {
        	Random rand = new Random(44);
        	PriorityQueue<Integer> contol = new PriorityQueue<>();
            for (int i=0; i<1000; i++){
            	int random = rand.nextInt(200);
            	contol.add(random);
            	list.add(random);
            }
            while (contol.size() > 0){
            	assertEquals(contol.poll(), list.removeFirst());
            }
        }
    }
    
    @Test
    public void testAccessTime(){
        for (ISkipList<Integer> list : lists) {
        	Random rand = new Random(66);
        	int runs = 1024 * 8;
            for (int i=0; i<runs; i++){
            	int random = rand.nextInt(2000);
            	list.add(random);
            }
            int total = 0;
            int max = 0;
            int tests = 1000;
            for (int i=0; i<tests; i++){
            	int steps = list.countStepsTo(rand.nextInt(2000));
            	total += steps;
            	max = Math.max(max, steps);
            }
            System.out.println(Util.getAuthor(list) + "'s SkipList traversed " + (total / tests) + " steps at average, at most " + max);
            assertTrue(total > tests && total < tests * 20);
        }
    }
    
    @Test
    public void testStandardOps(){
        for (ISkipList<Integer> list : lists) {
            try {
                list.removeFirst();
                fail();
            } catch (NoSuchElementException e){
            }
            list.add(17);
            list.add(17);
            Integer i1 = list.removeFirst();
            assertEquals(17, i1.intValue());
            assertEquals(list.removeFirst().intValue(), 17);
        }
    }

}
