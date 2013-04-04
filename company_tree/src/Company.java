/**
 *  @author Daniel Segal
 * Creates and manipulates a business directory based on a HashMap 
 * using several different data types 
 * 
 */

import java.util.*;
import java.util.Map.Entry;


public class Company{
	/** Data structure used to hold members of a subcompany(department) */
	Map<String, String> managerMap = new HashMap<String, String>();
	/** Data structure used to hold members of a full company with employee connections */
	Set<Object> managerSet = new HashSet<Object>();
	/** Data structure used to hold sorted list of managers by department size */
	List<String> managerList = new ArrayList<String>();	
	/** Stores the members of a subcompany to be built by the departmentOf method */
	private Set<Object> keys = new TreeSet<Object>();
	
	/** Initializes a full company using a map */
	Company(Map<String, String> managers){
		managerMap.putAll(managers);
	}
	
	/** Initializes a sub-company using a set*/
	Company(Set<Object> managers){
		managerSet.addAll(managers);
		
	}

	
	/** Adds a new employee and its manager.
	@exception	UnsupportedOperationExcetion	Thrown if attempting to add an employee to a nonexistent manager or if employee already exists
	@param	employee	Name of the employee to be added
	@param	manager		Name of the employee's manager
	*/
	void add(String employee, String manager){
		if ((this.managerMap.containsKey(manager)  && !this.managerMap.containsKey(employee)) || manager == null){
			this.managerMap.put(employee,manager);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}
	
	/** Deletes an employee from the company records. 
	 *@exception	UnsupportedOperationException	Thrown if employee odesn't exist or has subordinates
	 *@param		employee	Name of the employee to be deleted
	 */
	void delete(String employee){
		if (this.managerMap.containsKey(employee) && !this.managerMap.containsValue(employee)){
			this.managerMap.remove(employee);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}
	
	/** Returns the manager of the given employee (it returns null if the employee is the CEO). 
	 * @exception	Thrown if the employee doesn't exist
	 * @param	employee	Name of the employee whose manager is being looked up
	 * @return	Returns the name of the manager for supplied employee
	 * */
	String managerOf(String employee){
		String managerName = "";
		if (this.managerMap.containsKey(employee)){
			managerName = this.managerMap.get(employee);		
		}
		else {
			throw new UnsupportedOperationException();
		}
		return managerName;
	}
	
	/** Returns the names of all managers (as a separate set, and not as a view on the manager Map) 
	 @return	Returns a set containing names of every manager
	 */
	Set<String> managerSet(){
		Collection<String> c = managerMap.values();
		Set<String> managerSet = new HashSet<String>();
		managerSet.addAll(c);
		return managerSet;
	}
	
	/** Returns a company consisting of the given manager and all his direct and indirect subordinates. 
	 It returns an empty  company if the manager is not on the company records. 
	 @param		manager		The name of the manager with which to generate the subcompany
	 @return	returns the subcompany(department) for the given manager
	 */
	Company departmentOf(String manager){
		keys.add(manager);
		for(Entry<String, String> entry : managerMap.entrySet()){
			if (manager.equals(entry.getValue())) {
	             keys.add(entry.getKey());
	             departmentOf(entry.getKey().toString());
	         }
		}
		Company department = new Company(keys);
		return department;
	}
	
	/** Returns the names of all the managers sorted depending on the number of direct  or indirect subordinates (if more 
	than one manager has the same number of subordinates, they have to be listed in alphabetical order).
	@return		returns a list of managers sorted by the size of their departments
	*/
	List<String> managersByDepartmentSize(){
		TreeMap<Integer, String> mBySize = new TreeMap<Integer, String>();
		Set<String> employees = new TreeSet<String>(managerMap.keySet());
		Iterator<String> it = employees.iterator();
		while (it.hasNext()){
			String manager = it.next().toString();
			keys.add(manager);
				mBySize.put(departmentOf(manager).managerSet.size(), manager);	
			keys.clear();
		}
		List<String> managerList = new ArrayList<String>(mBySize.values());
		return managerList;
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> manager = new HashMap<String, String>();		
		Company test = new Company(manager);
		test.add("CEO", null);
	}

}
