package Controller;

public class Controller {
	public String stripHTML(String input) {
		
		return input.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
