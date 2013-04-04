import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CompanyTest {

	@Test
	public void test() {
		//Initialize Company
		Map<String, String> manager = new HashMap<String, String>();		
		Company test = new Company(manager);
		test.add("CEO", null);
		
		//Test Cases for Add Method
		test.add("Vice President", "CEO");
		test.add("Assistant VP", "Vice President");
		test.add("Secretary", "Vice President");
		
		assertTrue(test.managerMap.containsKey("Vice President"));
		assertTrue(test.managerMap.containsKey("Assistant VP"));
		assertTrue(test.managerMap.containsKey("Secretary"));
		
		//Test Case for Remove Method
		test.delete("Secretary");
		assertFalse(test.managerMap.containsValue("Secretary"));
		
		//Re-adding to have data to work with
		test.add("Secretary", "Vice President");
		
		//Tests Manager of Method
		assertTrue(test.managerOf("Secretary").toString().equals("Vice President"));
		
		//Tests managerSet Method
		assertTrue(test.managerSet().contains("CEO"));
		assertTrue(test.managerSet().contains("Vice President"));
		assertFalse(test.managerSet().contains("Secretary"));
		
		//Tests departmenOf Method
		assertTrue(test.departmentOf("Vice President").managerSet.contains("Secretary"));
		assertFalse(test.departmentOf("Vice President").managerSet.contains("CEO"));
		
		//Tests Department Size Method
		assertTrue(test.managersByDepartmentSize().lastIndexOf("Secretary") == 0);
		assertFalse(test.managersByDepartmentSize().lastIndexOf("CEO") == 1);

		
	
	}

}
