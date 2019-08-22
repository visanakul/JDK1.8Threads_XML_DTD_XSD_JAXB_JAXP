package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;

import demo.JAX_B_Demo;

public class EmployeeJaxb {
	public void marshal(Employee emp) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(emp, System.out);
			marshaller.marshal(emp, new File(JAX_B_Demo.FILE_PATH));
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
}
