package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Problem;
import Entity.Solution;
import Model.SolutionsModel;

public class SolutionsController extends Controller {

	SolutionsModel sm = new SolutionsModel();

	public ArrayList<Solution> readByID(int id) {
		try {
			return sm.readByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Solution> readSearch(String kelime) {
		return sm.readSearch(kelime);
	}

	public void updateSolution(Solution sltion) {
		try {
			sltion.setSolution(stripHTML(sltion.getSolution()));
			sm.updateSolution(sltion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSolution(int sltion) {
		try {
			sm.deleteSolution(sltion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addSolution(Solution sltion) {
		try {
			sltion.setSolution(stripHTML(sltion.getSolution()));
			sm.addSolution(sltion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
