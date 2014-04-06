// Created by Christian Guedel on 06.04.2014

package ch.fhnw.algd2.christianguedel;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashMapTest {

    @Test
    public void test() {
        
        HashMap hm = new HashMap();
        hm.put("a", "b");
        assertEquals("b", hm.get("a"));
        
        hm.put("a", "bc");
        assertEquals("bc", hm.get("a"));
        
        // keys have same hashcode: http://stackoverflow.com/a/10102397/932372
        hm.put("FB", "first");
        hm.put("Ea", "second");
        
        assertEquals("first", hm.get("FB"));
        assertEquals("second", hm.get("Ea"));
    }

}
