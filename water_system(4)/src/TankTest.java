import static org.junit.Assert.*;
import org.junit.Test;


public class TankTest {
	Tank a = new Tank();
	Tank b = new Tank();
	Tank c = new Tank();


	@Test
	public void testSetCoordinates() {
		assertFalse(a.alreadyCalled);	
		a.setCoordinates(new double[] {0,0,0}, new double[] {1,5,13});
		assertTrue(a.alreadyCalled);		
	}

	@Test
	public void testGetBottom() {
		a.setCoordinates(new double[] {0,0,0}, new double[] {1,5,13});
		b.setCoordinates(new double[] {11,12,13}, new double[] {13,45,18});	
		assertTrue(a.getBottom() == 0);
		assertFalse(b.getBottom() == 11);
	}

	@Test
	public void testGetTop() {
		a.setCoordinates(new double[] {0,0,0}, new double[] {1,5,13});
		b.setCoordinates(new double[] {11,12,13}, new double[] {13,45,18});		
		assertTrue(a.getTop() == 13);
		assertTrue(b.getTop() == 18);
	}

	@Test
	public void testBaseArea() {
		a.setCoordinates(new double[] {0,0,0}, new double[] {1,5,13});
		b.setCoordinates(new double[] {11,12,13}, new double[] {13,45,18});		
		assertFalse(a.baseArea() == 65);
		assertTrue(b.baseArea() == 66);
	}

}
