package demo;

import jaxb.Employee;
import jaxb.EmployeeJaxb;

public class JAX_B_Demo {
	public static final String FILE_PATH="src/data/employee.xml";
	public static void main(String[] args) {
		EmployeeJaxb jaxb=new EmployeeJaxb();
		Employee e=new Employee("E101", "emp1", 2_00_000);
		jaxb.marshal(e);
		System.out.println("-------------------------------------------");
		jaxb.unMarshal();
	}

}
