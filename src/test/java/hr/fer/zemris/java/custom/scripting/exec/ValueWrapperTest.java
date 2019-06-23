package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.security.InvalidParameterException;

import org.junit.Test;

public class ValueWrapperTest {

	@Test
	public void testValueWrapper() {
		
		ValueWrapper value = new ValueWrapper("2");
		assertEquals("2", value.getValue());
		
		ValueWrapper value2 = new ValueWrapper(2);
		assertEquals(2, value2.getValue());
		
		ValueWrapper value3 = new ValueWrapper(2.2);
		assertEquals(2.2, value3.getValue());
		
		ValueWrapper value4 = new ValueWrapper("string");
		assertEquals("string", value4.getValue());
		
		ValueWrapper value5 = new ValueWrapper(null);
		assertNull(value5.getValue());
	}
	
	@Test
	public void testIncrement() {
		
		ValueWrapper value = new ValueWrapper("2");
		value.increment("3");
		
		assertEquals(5, value.getValue());
		
		value.increment(4);
		
		assertEquals(9, value.getValue());
		
		value.increment("1.3");
		
		assertEquals(10.3, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 10.3) < 0.0000001);
		
		value.increment(2);
		
		assertEquals(12.3, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 12.3) < 0.0000001);
		
		value.increment("1.0");
		
		assertEquals(13.3, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 13.3) < 0.0000001);
		
		value.increment(new Double(1.0));
		
		assertEquals(14.3, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 14.3) < 0.0000001);
	}
	
	@Test
	public void testDecrement() {
		
		ValueWrapper value = new ValueWrapper("12");
		value.decrement("3");
		
		assertEquals(9, value.getValue());
		
		value.decrement(4.0);
		
		assertEquals(5.0, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 5.0) < 0.0000001);
		
		value.decrement("1.3");
		
		assertEquals(3.7, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 3.7) < 0.0000001);
		
		value.decrement(3);
		
		assertTrue(Math.abs((Double)value.getValue() - 0.7) < 0.0000001);
		
		value.decrement("2.0");
		
		assertTrue(Math.abs((Double)value.getValue() + 1.3) < 0.0000001);
		
		value.decrement(1);
		
		assertTrue(Math.abs((Double)value.getValue() + 2.3) < 0.0000001);
	}
	
	@Test
	public void testMultiplyAndDivide() {
		
		ValueWrapper value = new ValueWrapper("12");
		value.multiply("3");
		
		assertEquals(36, value.getValue());
		
		value.divide(4);
		
		assertEquals(9, value.getValue());
		
		value.multiply("1.0");
		
		assertEquals(9.0, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 9.0) < 0.0000001);
		
		value.divide(3);
		
		assertEquals(3.0, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 3.0) < 0.0000001);
		
		value.multiply("2.5");
		
		assertEquals(7.5, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 7.5) < 0.0000001);
		
		value.divide(1);

		assertEquals(7.5, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 7.5) < 0.0000001);
		
		value.multiply(1.0);
		
		assertEquals(7.5, value.getValue());
		assertTrue(Math.abs((Double)value.getValue() - 7.5) < 0.0000001);
	}
	
	@Test
	public void testNumCompare() {
		
		ValueWrapper value = new ValueWrapper("0");
		value.increment("3");
		
		assertEquals(0, value.numCompare(3));
		
		assertTrue(value.numCompare(2) > 0);
		assertTrue(value.numCompare(2.5) > 0);
		assertTrue(value.numCompare("2.3") > 0);
		
		assertTrue(value.numCompare(4.5) < 0);
		assertTrue(value.numCompare(5) < 0);
		assertTrue(value.numCompare("20") < 0);
		
		value.increment(4.5);
		
		assertEquals(0, value.numCompare(7.5));
		
		assertTrue(value.numCompare(2) > 0);
		assertTrue(value.numCompare(2.5) > 0);
		assertTrue(value.numCompare("2.3") > 0);
		
		assertTrue(value.numCompare(8.5) < 0);
		assertTrue(value.numCompare(50.4) < 0);
		assertTrue(value.numCompare("20") < 0);
		
		
	}
	
	@Test
	public void testSetValue() {
		
		ValueWrapper value = new ValueWrapper("0");
		
		assertEquals("0", value.getValue());
		
		value.setValue(4);
		
		assertEquals(4, value.getValue());
		
		value.setValue(3);
		
		assertEquals(3, value.getValue());
		
	}
	
	@Test
	public void testParsingException() {
		
		ValueWrapper value = new ValueWrapper("0");
		
		value.increment("a");
	}
	
	@Test
	public void testParsingException2() {
		
		ValueWrapper value = new ValueWrapper("0");
		
		value.increment("a.a");
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testInvalidParameterException() {
		
		ValueWrapper value = new ValueWrapper("0");
		
		value.increment(new BufferedInputStream(System.in));
	}
	
	@Test
	public void testNullValueAsParameter() {
		
		ValueWrapper value = new ValueWrapper("0");
		
		value.increment(null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
