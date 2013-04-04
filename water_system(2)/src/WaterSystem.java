/**
 * Name: Daniel Segal
 * 
 * Email: dls148@case.edu
 * 
 * This class populates a water system with objects of type Tank, and can return
 * the status of those tanks at various key water levels.
 * 
 */

import java.util.*;


public class WaterSystem{
	Set<Tank> waterSystemSet = new HashSet<Tank>();
	Set<Double> breakpoints = new HashSet<Double>();
	
	NavigableMap<Double, Set<Tank>> tanksByBottom = new TreeMap<Double, Set<Tank>>();
	NavigableMap<Double, Set<Tank>> tanksByTop = new TreeMap<Double, Set<Tank>>();
	NavigableMap<Double, Set<Tank>> activeTanks = new TreeMap<Double, Set<Tank>>();
	NavigableMap<Double, Double> activeBaseArea = new TreeMap<Double, Double>();
	
	/**
	 * Initializes a WaterSystem from a set of tanks
	 * @param init_system	A set of objects of type Tank
	 */
	public WaterSystem (Set<Tank> init_system) {
		waterSystemSet.addAll(init_system);
		getBreakpoints();
	}
	
	/**
	 * Iterates through tanks in WaterSystem, making a Set<Double> of all top
	 * and bottom values, to use as breakpoints.
	 */
	private void getBreakpoints(){
		Iterator<Tank> tankIterator = waterSystemSet.iterator();
		while (tankIterator.hasNext()){
			Tank temp = tankIterator.next();
			breakpoints.add(temp.getBottom());
			breakpoints.add(temp.getTop());
		}
	}
	
	/**
	 * Iterates through each breakpoint, then each tank at that breakpoint to build a map
	 * of which tanks have a bottom at the breakpoint.
	 * @return tanksByBottom	A map relating each breakpoint to the tanks which have a bottom
	 * 							at the breakpoint
	 */
	NavigableMap<Double, Set<Tank>> tanksByBottom(){
		for(Double breakpoint : breakpoints){
			Set<Tank> validTankBottom = new HashSet<Tank>();
			for(Tank tank: waterSystemSet){				
				if (breakpoint == tank.getBottom()){
					validTankBottom.add(tank);					
				}
			}			
			tanksByBottom.put(breakpoint,validTankBottom);
		}
		return tanksByBottom;
	}
	
	/**
	 * Iterates through each breakpoint, then each tank at that breakpoint to build a map
	 * of which tanks have a top at the breakpoint.
	 * @return tanksByTop		A map relating each breakpoint to the tanks which have a top
	 * 							at the breakpoint
	 */
	NavigableMap<Double, Set<Tank>> tanksByTop(){
		for(Double breakpoint : breakpoints){
			Set<Tank> validTankTop = new HashSet<Tank>();
			for(Tank tank: waterSystemSet){				
				if (breakpoint == tank.getTop()){
					validTankTop.add(tank);					
				}
			}			
			tanksByTop.put(breakpoint, validTankTop);
		}
		return tanksByTop;
	}
	
	/**
	 * Iterates through each breakpoint, then each tank at that breakpoint to build a map
	 * of which tanks have are active at the breakpoint.
	 * @return activeTanks		A map relating each breakpoint to the tanks which are active
	 * 							at the breakpoint
	 */
	NavigableMap<Double, Set<Tank>> activeTanks(){
		for(Double breakpoint : breakpoints){
			Set<Tank> validActiveTank = new HashSet<Tank>();
			for(Tank tank: waterSystemSet){				
				if (breakpoint >= tank.getBottom() && breakpoint < tank.getTop()){
					validActiveTank.add(tank);					
				}
			}			
			activeTanks.put(breakpoint, validActiveTank);
		}
		return activeTanks;
	}
	
	/**
	 * Iterates through each breakpoint, computing active base area based on active tanks.
	 * @return activeBaseArea	A map relating each breakpoint to the active base area
	 */
	NavigableMap<Double, Double> activeBaseArea(){
		for(Double breakpoint : breakpoints){
			double baseArea = 0;
			for(Tank tank: waterSystemSet){				
				if (breakpoint >= tank.getBottom() && breakpoint < tank.getTop()){
					baseArea = baseArea + tank.baseArea();					
				}
			}			
			activeBaseArea.put(breakpoint, baseArea);
		}
		return activeBaseArea;
	}
	

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Tank> init_system = new HashSet<Tank>();
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
		systemA.getBreakpoints();
		System.out.println("Tanks by Bottom: " + systemA.tanksByBottom());
		System.out.println("Tanks by Top: " + systemA.tanksByTop());
		System.out.println("Acitve Tanks: " + systemA.activeTanks());
		System.out.println("Acitve Base Area: " + systemA.activeBaseArea());
		
		System.out.println("A - " + a);
		System.out.println("B - " + b);
		System.out.println("C - " + c);
		System.out.println("D - " + d);
		
		
	}

}
