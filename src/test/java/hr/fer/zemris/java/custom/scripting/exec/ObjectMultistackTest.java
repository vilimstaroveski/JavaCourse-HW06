package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

public class ObjectMultistackTest {

	@Test
	public void testPush() {
		
		ObjectMultistack mStack = new ObjectMultistack();
		
		mStack.push("year", new ValueWrapper("godina"));
		mStack.push("price", new ValueWrapper("200.12"));
		mStack.push("year", new ValueWrapper(2004));
		mStack.push("year2", new ValueWrapper(41.2));
	
		HashMap<String, ObjectMultistack.MultistackEntry> map = mStack.getHashMap();
		
		assertEquals(map.size(), 3);
		
		Set<String> keySet = map.keySet();
		
		assertEquals("[year, price, year2]", keySet.toString());
	}
	
	@Test
	public void testPop() {
		
		ObjectMultistack mStack = new ObjectMultistack();
		
		mStack.push("test", new ValueWrapper("2000"));
		mStack.push("test", new ValueWrapper("200.12"));
		mStack.push("test", new ValueWrapper(null));
		mStack.push("test2", new ValueWrapper(new Integer(2)));
	
		HashMap<String, ObjectMultistack.MultistackEntry> map = mStack.getHashMap();
		
		ValueWrapper poppedValue = mStack.pop("test");
		assertNull(poppedValue.getValue());
		poppedValue = mStack.pop("test2");
		assertEquals(2, poppedValue.getValue());
		poppedValue = mStack.pop("test");
		assertEquals("200.12", poppedValue.getValue());
		poppedValue = mStack.pop("test");
		assertEquals("2000", poppedValue.getValue());
		
		assertEquals(0, map.size());
	}
	
	@Test(expected=NonExistingStackException.class)
	public void testPopWithException() {

		ObjectMultistack mStack = new ObjectMultistack();
		
		mStack.push("test", new ValueWrapper("2000"));
		mStack.push("test", new ValueWrapper("200.12"));
		
		mStack.pop("test3");
	}
	
	@Test
	public void testPeek() {
		
		ObjectMultistack mStack = new ObjectMultistack();
		
		mStack.push("test", new ValueWrapper("2000"));
		mStack.push("test", new ValueWrapper("200.12"));
		mStack.push("test", new ValueWrapper(null));
		mStack.push("test2", new ValueWrapper(new Integer(2)));
	
		HashMap<String, ObjectMultistack.MultistackEntry> map = mStack.getHashMap();
		
		ValueWrapper peekValue = mStack.peek("test");
		assertNull(peekValue.getValue());
		
		peekValue = mStack.peek("test");
		assertNull(peekValue.getValue());
		
		mStack.pop("test");
		mStack.pop("test");
		
		peekValue = mStack.peek("test");
		assertEquals("2000", peekValue.getValue());
		
		assertEquals(2, map.size());
	}
	
	@Test(expected=NonExistingStackException.class)
	public void testPeekWithException() {

		ObjectMultistack mStack = new ObjectMultistack();
		
		mStack.push("test", new ValueWrapper("2000"));
		mStack.push("test", new ValueWrapper("200.12"));
		
		mStack.peek("test3");
	}
	
	@Test
	public void testIsEmpty() {

		ObjectMultistack mStack = new ObjectMultistack();
		
		assertTrue(mStack.isEmpty("test"));
		
		mStack.push("test", new ValueWrapper(200));
		
		assertFalse(mStack.isEmpty("test"));
		
		mStack.pop("test");
		
		assertTrue(mStack.isEmpty("test"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
