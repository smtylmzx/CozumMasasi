import Controller.Controller;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller c = new Controller();
		System.out.println(c.stripHTML("test<>"));
	}

}
