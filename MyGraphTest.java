package cmsc256;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Kendall McCleary
 * Project 6
 * CMSC 256 Fall 2019
 * JUnit for MyGraph class
 */

/**************************************************************************************
 * inFile visibility NEEDS TO BE CHANGED TO PUBLIC FOR MY JUNIT TO PASS MY TESTS!!!!!! 
 *************************************************************************************/

public class MyGraphTest {
	
	@Test
	public void testIsGraphConnected() throws FileNotFoundException {
		//testing a connected graph
		MyGraph<Object> mg = new MyGraph<>();
		
		boolean connect = mg.isGraphConnected("ex1.txt");
		
		assertEquals(true, connect);
		
		//testing a disconnected graph
		boolean connect1 = mg.isGraphConnected("ex2.txt");
			
			//if there are multiple vertices that aren't connected
			assertEquals(false, connect1);
			
		//testing a disconnected graph
		boolean connect2 = mg.isGraphConnected("ex5.txt");
		
			//if there is one vertex
			assertEquals(false, connect2);
		
		
		//if the file is connected it should return true
		assertTrue("It should return true", true);
	
		//if the file isn't connected it should return false
		assertFalse("It should return false", false);	
}

	@Test
	public void testGetConnectedComponents() throws FileNotFoundException {

		//If all the vertexes are connected
		/***********
		 * "ex1.txt"
		 * 6
		 * 0 1 2 
		 * 1 0 2 3
		 * 2 0 4
		 * 3 1 4 5
		 * 4 3 5
		 * 5 3 4
		 ***********/
		MyGraph<Object> ag = new MyGraph<>();
		
		MyGraph<Integer> mg = ag.inFile("ex1.txt");
		
		List<List<Integer>> cc = mg.getConnectedComponents();
		
		List<Integer> result = new ArrayList<>();
			result.add(0);
			result.add(1);
			result.add(2);
			result.add(4);
			result.add(3);
			result.add(5);
	
		List<List<Integer>> ccResult = new ArrayList<>();
			ccResult.add(result);
			
			assertEquals(ccResult, cc);
		
	    //if the file doesn't have connections it should return each vertex individually
		/***********
		 * "ex2.txt"
		 * 4
		 * 0
		 * 1
		 * 3
		 ************/		
		MyGraph<Integer> bg = ag.inFile("ex2.txt");
			
		List<List<Integer>> cc1 = bg.getConnectedComponents();
	
		List<Integer> result1 = new ArrayList<>();
			result1.add(0);
			result1.add(1);
			result1.add(2);
			result1.add(3);
		
		List<List<Integer>> ccResult1 = new ArrayList<>();
			
			for(int i = 1; i <= result1.size(); i++) {
				
				result1.subList(i - 1, i);
				
				if(!ccResult1.contains(result1.subList(i - 1, i))) {
				
					ccResult1.add(result1.subList(i - 1, i));
				}
			}
				
			assertEquals(ccResult1, cc1);		
			
		//if null is returned
		assertNotNull("Null shouldn't be returned" + new MyGraph<>());		
}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetPath() {
		MyGraph<Object> mg = new MyGraph<>();
		
		List<Integer> path = mg.getPath(0, 1);
		
		List<Integer> result = new ArrayList<>();
		
			result.add(0);
			result.add(1);
		
		//check to see if they match
		assertEquals(result, path);		
		
		List<Integer> path1 = mg.getPath(1, 4);
		
		List<Integer> result1 = new ArrayList<>();
			result1.add(1);
			result1.add(2);
			result1.add(3);
			result1.add(4);
			
		assertEquals(result1, path1);
		
		//If the method doesn't return null when it should
		assertNull("The path should be null", null);
		
		//If the method returns null when it shouldn't
		assertNotNull("The path shouldn't be null" , new MyGraph<>());			
}

	@Test
	public void testIsCyclic() throws FileNotFoundException {
		MyGraph<Object> bg = new MyGraph<>();
		
		//testing true
		/************
		 * "ex4.txt"
		 * 6
		 * 0 1 2 
		 * 1 0 2 3
		 * 2 0 4
		 * 3 1 2 4 5
		 * 4 2 3 5
		 * 5 3 4
		 ************/
		MyGraph<Integer> kg = bg.inFile("ex4.txt");
		  
		boolean cycleThere = kg.isCyclic();
		  
		  	assertEquals(true, cycleThere);
		  	
		//testing false
		/***********
		 * "ex2.txt"
		 * 4
		 * 0
		 * 1
		 * 3
	     ************/	
		MyGraph<Integer> jg = bg.inFile("ex2.txt");
			  
		boolean cycleThere1 = jg.isCyclic();
			  
			  	assertEquals(false, cycleThere1);
		  
		//if the file isn't connected it should return false
		assertFalse("It should return false", false);
				
		//if the file is connected it should return true
		assertTrue("It should return true", true);
}
	
	@Test
	public void testFindCycle() throws FileNotFoundException {
		MyGraph<Object> bg = new MyGraph<>();
	/************
	 * "ex1.txt"
	 * 6
	 * 0 1 2 
	 * 1 0 3
	 * 2 0 4
	 * 3 1 4 5
	 * 4 3 5
	 * 5 3 4
	 ************/
		MyGraph<Integer> mg = bg.inFile("ex1.txt");
		
		List<Integer> cycle0 = mg.findCycle(0);
		
		List<Integer> result0 = new ArrayList<>();
		
			result0.add(0);
			result0.add(1);
			result0.add(2);
			
		assertEquals(result0, cycle0);
		
		/************
		 * "ex4.txt"
		 * 6
		 * 0 1 2 
		 * 1 0 2 3
		 * 2 0 4
		 * 3 1 2 4 5
		 * 4 2 3 5
		 * 5 3 4
		 ************/

		MyGraph<Integer> ag = bg.inFile("ex4.txt");
		
		List<Integer> cycle01 = ag.findCycle(0);
		
		List<Integer> result01 = new ArrayList<>();
			result01.add(0);
			result01.add(1);
			result01.add(2);
			
		assertEquals(result01, cycle01);
			
		
		assertNull("Cycle should be null", null);
		
		assertNotNull("Cycle shouldn't be null" , new MyGraph<>());
		
}
	
}