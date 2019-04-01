package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Problem;
import Entity.Remark;
import Model.ProblemsModel;

public class ProblemsController extends Controller {

	public ProblemsModel pm = new ProblemsModel();

	public ArrayList<Problem> readAll() {
		return pm.readAll();
	}
	
	public ArrayList<Problem> readSolAll() {
		return pm.readSolAll();
	}
	
	public ArrayList<Problem> readPendAll() {
		return pm.readPendAll();
	}

	public ArrayList<Problem> readlast() {
		return pm.readlast();
	}

	public HashMap<String, Integer> statisticErrors() {
		return pm.statisticErrors();
	}
	
	public ArrayList<Problem> readSearch(String kelime) {
		return pm.readSearch(kelime);
	}

	public Problem readByID(int id) {
		try {
			return pm.readByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateProblem(Problem prblm) {
		try {
			prblm.setProblem(stripHTML(prblm.getProblem()));
			pm.updateProblem(prblm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteProblem(int prblm) {
		try {
			pm.deleteProblem(prblm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addProblem(Problem prblm) {
		int result = 0;
		
		prblm.setProblem(stripHTML(prblm.getProblem()));
		
		try {
			result = pm.addProblem(prblm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
//	public  ArrayList<Problem> searchInstant(String kelime) {
//		return pm.searchInstant(kelime);
//	}
	

}
