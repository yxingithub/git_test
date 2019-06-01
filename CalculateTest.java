import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CalculateTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	System.out.println("this is beforeClasss....");
	}
 
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("this is afterClasss....");
	}
 
	@Before
	public void setUp() throws Exception {
		System.out.println("this is brfore....");
	}
 
	@After
	public void tearDown() throws Exception {
		System.out.println("this is after....");
	}


	@Test
	public void testAdd(){
		System.out.println("this is testAdd...");
		assertEquals(6,new Calculate().add(3, 2));
	}
	
	@Test
	public void testsubstract(){
		System.out.println("this is testsubstract...");
		assertEquals(2,new Calculate().substract(5, 3));
	}
	
	@Test
	public void testcheng(){
		System.out.println("this is testcheng...");
		assertEquals(15,new Calculate().cheng(5, 3));
	}
	@Test
	public void testchu(){
		System.out.println("this is testchu...");
		assertEquals(2,new Calculate().chu(6, 3));
	}


}
