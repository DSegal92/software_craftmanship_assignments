/**
 * @author Daniel Segal
 * 
 * Email: dls148@case.edu
 * 
 * This class creates water tanks in 3 Dimensional space, and can return
 * their attributes.
 * 
 */
public class Tank {
	private double [] topRight;
	private double [] bottomLeft;
	boolean alreadyCalled = false;

	/**
	 *  Constructor for tanks. Initializes them as points in 3D space
	 */
	public Tank(){
		//Coordinates defined as {x,y,z} such that X is coming out of page, Y is horizontal, Z is vertical
		bottomLeft = new double[] {0,0,0};
		topRight = new double [] {0,0,0};
	}

	/** Sets and checks coordinates for both attributes of a Tank
	 * @param bottomLeft	An array of coordinates representing the cube's location
	 * @param topRight		An array of coordinates representing the cube's dimensions
	 * @throws	IllegalArgumentException	Thrown if any of the coordinates are negative
	 * @throws	IllegalStateException		Thrown if the coordinates for the cube have previously been assigned
	 * Complexity: 4
	 */
	void setCoordinates(double [] bottomLeft, double [] topRight)throws IllegalStateException, IllegalArgumentException {
		try {
			if (this.alreadyCalled == false){
				//Counter fixed at 3 since no other possible amount of coordinates
				for (int coordinateCounter = 0; coordinateCounter < 3; coordinateCounter++){
					this.bottomLeft[coordinateCounter] = bottomLeft[coordinateCounter];
					this.topRight[coordinateCounter] = topRight[coordinateCounter];
				}
				this.alreadyCalled = true;
				if (!checkValidity()){
					throw new IllegalArgumentException("Coordinates are Invalid");
				}
			}
			else{
				throw new IllegalStateException("Coordinates Already Set for Tank");
			}

		} catch (IllegalArgumentException invalidCoordinates){
			System.out.println(invalidCoordinates.getMessage());

		} catch (IllegalStateException coordinatesSet){
			System.out.println(coordinatesSet.getMessage());
		}
	}

	/**
	 * Tests if Coordinates result in non-negative sides, and only have 3 points
	 * Complexity: 3
	 * @return Returns true if coordinates don't break any rules
	 */
	boolean checkValidity(){
		boolean coordinatesValid = true;
		for (int coordinateCounter = 0; coordinateCounter < 3; coordinateCounter++){
			if ((this.topRight[coordinateCounter] - this.bottomLeft[coordinateCounter]) <= 0){
				coordinatesValid = false;
			}
		}
		if (this.bottomLeft.length > 3 || this.topRight.length > 3){
			coordinatesValid = false;
		}
		return coordinatesValid;
	}
	/**
	 * Returns the Z coordinate of the bottom of the tank
	 * @return bottom	The height of the bottom of the tank
	 * Complexity: 0
	 */
	double getBottom(){
		double bottom = this.bottomLeft[2];
		return bottom;
	}

	/**
	 * Returns the Z coordinate of the top of the tank
	 * @return top	The height of the top of the tank
	 * Complexity: 0
	 */
	double getTop(){
		double top = this.topRight[2];
		return top;
	}

	/**
	 * Returns the base area of the tank, using X and Y values, with the bottomLeft as the origin
	 * @return	baseArea	The area of the tank's base
	 * Complexity: 0
	 */
	double baseArea(){
		double baseArea = Math.abs((this.topRight[0] - this.bottomLeft[0])) * Math.abs((this.topRight[1] - this.bottomLeft[1]));
		return baseArea;
	}

	/**
	 * Checks to see if a tank contains water at a given global water height
	 * @param waterHeight	Global water height in system
	 * @return	Returns true if tank contains water, false otherwise
	 */
	boolean containsWater(double waterHeight){
		boolean isActive = false;
		if (waterHeight >= this.getBottom()){
			isActive = true;
		}
		return isActive;
	}

	/**
	 * Checks to see if a tank is full at a given global water height
	 * @param waterHeight Global water height in system
	 * @return Returns true if a tank is full, false otherwise
	 */
	boolean isFull(double waterHeight){
		boolean isFull = false;
		if (waterHeight > this.getTop()){
			isFull = true;
		}
		return isFull;
	}


	/**
	 * Helper method to heightToTankLevel, computes level of water in tanks
	 * 
	 * @param tank
	 *            Tank for which to calculate water level
	 * @param waterHeight
	 *            Height of water in the system
	 * @return Returns the waterLevel in a tank as a Double
	 */
	Double computeWaterLevel(Tank tank, Double waterHeight) {
		double waterLevel = 0.0;
		// If tank is full, water level is length of Z dimension of tank
		// (height)
		if (tank.isFull(waterHeight)) {
			waterLevel = tank.getTop() - tank.getBottom();
			return waterLevel;
		}
		// If tank isn't full, water level is global level - height of bottom of
		// tank
		else {
			waterLevel = waterHeight - tank.getBottom();
			return waterLevel;
		}
	}
	
	/**
	 * Calculates the volume of a tank
	 * @return Returns the volume of water in a tank
	 */
	double getVolume(){
		double volume = 0;
		volume = this.baseArea() * Math.abs(this.getTop()-this.getBottom());
		return volume;
	}

}

