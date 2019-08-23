package demo;

public class Demo {

	public static void main(String[] args) {
		String x="hello";
		Object obj=x;
		try {
			Class cls=Class.forName(obj.getClass().getTypeName());
			System.out.println("Class="+cls);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
