import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;


public class WaterSystemTest {
	Set<Tank> waterSystemSet = new HashSet<Tank>();
	Set<Double> breakpoints = new HashSet<Double>();
	
	NavigableMap<Double, Set<Tank>> tanksByBottom = new TreeMap<Double, Set<Tank>>();
	NavigableMap<Double, Set<Tank>> tanksByTop = new TreeMap<Double, Set<Tank>>();
	NavigableMap<Double, Set<Tank>> activeTanks = new TreeMap<Double, Set<Tank>>();
	NavigableMap<Double, Double> activeBaseArea = new TreeMap<Double, Double>();	
	Set<Tank> init_system = new HashSet<Tank>();


	@Test
	public void testTanksByBottom() {
		Tank a = new Tank();
		Tank b = new Tank();
		Tank c = new Tank();
		Tank d = new Tank();
		a.setCoordinates(new double[] {0,0,5}, new double[] {1,5,13});
		b.setCoordinates(new double[] {0,0,11}, new double[] {1,5,18});		
		c.setCoordinates(new double[] {0,0,15}, new double[] {1,4,21});
		d.setCoordinates(new double[] {0,0,19}, new double[] {1,8,23});

		
		init_system.add(a);
		init_system.add(b);
		init_system.add(c);
		init_system.add(d);		
		WaterSystem systemA = new WaterSystem(init_system);
		assertTrue(systemA.tanksByBottom().get(5d).contains(a));
		assertTrue(systemA.tanksByBottom().get(11d).contains(b));
		assertFalse(systemA.tanksByBottom().get(13d).contains(b));
	}

	@Test
	public void testTanksByTop() {
		Tank a = new Tank();
		Tank b = new Tank();
		Tank c = new Tank();
		Tank d = new Tank();
		a.setCoordinates(new double[] {0,0,5}, new double[] {1,5,13});
		b.setCoordinates(new double[] {0,0,11}, new double[] {1,5,18});		
		c.setCoordinates(new double[] {0,0,15}, new double[] {1,4,21});
		d.setCoordinates(new double[] {0,0,19}, new double[] {1,8,23});

		
		init_system.add(a);
		init_system.add(b);
		init_system.add(c);
		init_system.add(d);		
		WaterSystem systemA = new WaterSystem(init_system);
		assertFalse(systemA.tanksByTop().get(5d).contains(a));
		assertFalse(systemA.tanksByTop().get(11d).contains(b));
		assertTrue(systemA.tanksByTop().get(13d).contains(a));
	}

	@Test
	public void testActiveTanks() {
		Tank a = new Tank();
		Tank b = new Tank();
		Tank c = new Tank();
		Tank d = new Tank();
		a.setCoordinates(new double[] {0,0,5}, new double[] {1,5,13});
		b.setCoordinates(new double[] {0,0,11}, new double[] {1,5,18});		
		c.setCoordinates(new double[] {0,0,15}, new double[] {1,4,21});
		d.setCoordinates(new double[] {0,0,19}, new double[] {1,8,23});

		
		init_system.add(a);
		init_system.add(b);
		init_system.add(c);
		init_system.add(d);		
		WaterSystem systemA = new WaterSystem(init_system);
		assertTrue(systemA.activeTanks().get(5d).contains(a));
		assertTrue(systemA.activeTanks().get(11d).contains(b) && systemA.activeTanks().get(11d).contains(a));
		assertFalse(systemA.activeTanks().get(13d).contains(a));
	}

	@Test
	public void testActiveBaseArea() {
		Tank a = new Tank();
		Tank b = new Tank();
		Tank c = new Tank();
		Tank d = new Tank();
		a.setCoordinates(new double[] {0,0,5}, new double[] {1,5,13});
		b.setCoordinates(new double[] {0,0,11}, new double[] {1,5,18});		
		c.setCoordinates(new double[] {0,0,15}, new double[] {1,4,21});
		d.setCoordinates(new double[] {0,0,19}, new double[] {1,8,23});

		
		init_system.add(a);
		init_system.add(b);
		init_system.add(c);
		init_system.add(d);		
		WaterSystem systemA = new WaterSystem(init_system);
		System.out.println(systemA.activeBaseArea());
		assertTrue(systemA.activeBaseArea().get(5d).equals(5d));
		assertFalse(systemA.activeBaseArea().get(11d).equals(123d));
		assertTrue(systemA.activeBaseArea().get(19d).equals(12d));
		
	}

}
