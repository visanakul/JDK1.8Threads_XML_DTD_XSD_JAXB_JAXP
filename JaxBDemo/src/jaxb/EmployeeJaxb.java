package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;

import demo.JAX_B_Demo;
//com.myexample.test.ObjectFactory.class.getPackage().getName(),
//com.myexample.test.ObjectFactory.class.getClassLoader()
public class EmployeeJaxb {
	public void marshal(Object obj) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(obj, System.out);
			marshaller.marshal(obj, new File(JAX_B_Demo.FILE_PATH));
			System.out.println("XML created...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finish");
		}
	}

	public void unMarshal() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Employee emp = (Employee) unmarshaller.unmarshal(new File(JAX_B_Demo.FILE_PATH));
			System.out.println("Employee : " + emp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finish");
		}
	}
	public void marshal1() {
		try {
			Employee empMarshal=new Employee("E002", "emp2", 90000);
			Object obj=empMarshal;
			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(obj, System.out);
			marshaller.marshal(obj, new File(JAX_B_Demo.FILE_PATH));
			System.out.println("XML created...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finish");
		}
	}
}
