// Created by Roman Gribi on 06.04.2014

package ch.fhnw.algd2.romangribi;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HashMapTest {
    private HashMap map;
    
    @Before
    public void setUp() {
        this.map = new HashMap();
        
        this.map.put("KEY1", "Value1");
        this.map.put("KEY2", "Value2");
        this.map.put("FB", "Value3");
        this.map.put("Ea", "Value4");
    }
    
    @Test
    public void testValidKey() {
        assertEquals("Value1", this.map.get("KEY1"));
        assertEquals("Value2", this.map.get("KEY2"));
        assertEquals("Value1", this.map.get("KEY1"));
    }
    
    @Test
    public void testInvalidKey() {
        assertNull(this.map.get("KEY3"));
    }
    
    @Test
    public void testDuplicateHashCode() {
        assertEquals("Value3", this.map.get("FB"));
        assertEquals("Value4", this.map.get("Ea"));
    }
    
    @Test
    public void testOverride() {
        assertEquals("Value1", this.map.get("KEY1"));
        this.map.put("KEY1", "Value1_New");
        assertEquals("Value1_New", this.map.get("KEY1"));
    }
}
