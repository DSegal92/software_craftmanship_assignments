import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.HashMap;

/**
 * Name: Daniel Segal
 * 
 * Email: dls148@case.edu
 * 
 * This class populates a water system with objects of type Tank, and can return
 * the status of those tanks at various key water levels.
 * 
 */

public class WaterSystem{
	Set<Tank> waterSystemSet = new HashSet<Tank>();
	Set<Double> breakpoints = new HashSet<Double>();
	
	NavigableMap<Double, Set<Tank>> tanksByBottom = new TreeMap<Double, Set<Tank>>();
	NavigableMap<Double, Set<Tank>> tanksByTop = new TreeMap<Double, Set<Tank>>();
	NavigableMap<Double, Set<Tank>> activeTanks = new TreeMap<Double, Set<Tank>>();
	NavigableMap<Double, Double> activeBaseArea = new TreeMap<Double, Double>();
	
	HashMap<Tank, Double> heightToTankLevel = new HashMap<Tank, Double>();
	
	/**
	 * Initializes a WaterSystem from a set of tanks
	 * @param init_system	A set of objects of type Tank
	 * Complexity: 0
	 */
	public WaterSystem (Set<Tank> init_system) {
		waterSystemSet.addAll(init_system);
		getBreakpoints();
		generateMaps();
	}
	
	/**
	 * Iterates through tanks in WaterSystem, making a Set<Double> of all top
	 * and bottom values, to use as breakpoints.
	 * Complexity: 1
	 */
	private void getBreakpoints(){
		Iterator<Tank> tankIterator = waterSystemSet.iterator();
		while (tankIterator.hasNext()){
			Tank temp = tankIterator.next();
			breakpoints.add(temp.getBottom());
			breakpoints.add(temp.getTop());
		}
	}
	
	private void generateMaps(){
		//Iterate through each breakpoint
		for(Double breakpoint : breakpoints){
			//Create 3 temporary sets to hold tanks which meet criteria at breakpoint
			Set<Tank> validTankBottom = new HashSet<Tank>();
			Set<Tank> validTankTop = new HashSet<Tank>();
			Set<Tank> validActiveTank = new HashSet<Tank>();
			double baseArea = 0;
			//Iterate through each tank at current breakpoint, adding to temp set if meeting criteria
			for(Tank tank: waterSystemSet){				
				if (breakpoint == tank.getBottom()){
					validTankBottom.add(tank);					
				}
				if (breakpoint == tank.getTop()){
					validTankTop.add(tank);					
				}
				//If tank is active both adds it to the activeTanks map and adds baseArea to activeBaseArea map 
				if (breakpoint >= tank.getBottom() && breakpoint < tank.getTop()){
					validActiveTank.add(tank);
					baseArea = baseArea + tank.baseArea();					
				}
			}
			//Dump contents of temporary sets into corresponding maps
			tanksByBottom.put(breakpoint,validTankBottom);
			tanksByTop.put(breakpoint,validTankTop);
			activeTanks.put(breakpoint,validActiveTank);
			activeBaseArea.put(breakpoint, baseArea);
		}		
	}
	
	/**
	 * Iterates through each breakpoint, then each tank at that breakpoint to build a map
	 * of which tanks have a bottom at the breakpoint.
	 * @return tanksByBottom	A map relating each breakpoint to the tanks which have a bottom
	 * 							at the breakpoint
	 * Complexity: 3
	 */
	NavigableMap<Double, Set<Tank>> tanksByBottom(){
		return tanksByBottom;
	}
	
	/**
	 * Iterates through each breakpoint, then each tank at that breakpoint to build a map
	 * of which tanks have a top at the breakpoint.
	 * @return tanksByTop		A map relating each breakpoint to the tanks which have a top
	 * 							at the breakpoint
	 * Complexity: 3
	 */
	NavigableMap<Double, Set<Tank>> tanksByTop(){
		return tanksByTop;
	}
	
	/**
	 * Iterates through each breakpoint, then each tank at that breakpoint to build a map
	 * of which tanks have are active at the breakpoint.
	 * @return activeTanks		A map relating each breakpoint to the tanks which are active
	 * 							at the breakpoint
	 * Complexity: 3
	 */
	NavigableMap<Double, Set<Tank>> activeTanks(){
		return activeTanks;
	}
	
	/**
	 * Iterates through each breakpoint, computing active base area based on active tanks.
	 * @return activeBaseArea	A map relating each breakpoint to the active base area
	 * Complexity: 3
	 */
	NavigableMap<Double, Double> activeBaseArea(){		
		return activeBaseArea;
	}
	
	Map<Tank, Double> heightToTankLevel(Double waterHeight){
		for(Tank tank: waterSystemSet){	
			double waterLevel = 0.0;
			if (tank.containsWater(waterHeight)){
				if (tank.isFull(waterHeight)){
					waterLevel = tank.getTop() - tank.getBottom();
					heightToTankLevel.put(tank, waterLevel);
					
				}
				else{
					waterLevel = waterHeight - tank.getBottom();
					heightToTankLevel.put(tank, waterLevel);
				}
			}
			else {
				heightToTankLevel.put(tank, waterLevel);
			}
		}
		System.out.println(heightToTankLevel);
		return heightToTankLevel;
	}
	
	Double heightToVolume(Double waterHeight){
		double computedVolume = 0;
		Iterator<Tank> it = heightToTankLevel.keySet().iterator();
		while (it.hasNext()){
			Tank temp = it.next();
			computedVolume = computedVolume + (temp.baseArea() * heightToTankLevel.get(temp));
	}
		System.out.println(computedVolume);
		return computedVolume;
	}
}
