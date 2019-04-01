package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Problem;
import Entity.Solution;

public class SolutionsModel extends Model {

	public void addSolution(Solution sltion) throws SQLException {
		stmt = con.prepareStatement("INSERT INTO solutions VALUE (NULL,?,?)");
		stmt.setString(1, sltion.getSolution());
		stmt.setInt(2, sltion.getProbID());
		int rs = stmt.executeUpdate();
		System.out.println("??z?m eklendi.");
	}

	public void updateSolution(Solution sltion) throws SQLException {
		stmt = con.prepareStatement("UPDATE solutions SET solution=? WHERE SolutionID=?");
		stmt.setString(1, sltion.getSolution());
		stmt.setInt(2, sltion.getSolutionsID());
		int rs = stmt.executeUpdate();
		System.out.println("??z?m g?ncellendi.");
	}

	public void deleteSolution(int solutionid) throws SQLException {
		stmt = con.prepareStatement("DELETE FROM solutions WHERE SolutionID=?");
		stmt.setInt(1, solutionid);
		int rs = stmt.executeUpdate();
		System.out.println("??z?m silindi.");
	}
	
	public ArrayList<Solution> readByID(int id) throws SQLException {
		ArrayList<Solution> list = new ArrayList();
		stmt = con.prepareStatement("SELECT * FROM solutions WHERE ProbID=? ORDER BY SolutionID DESC");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Solution s = new Solution();
			s.setSolutionsID(rs.getInt("SolutionID"));
			s.setSolution(rs.getString("solution"));
			list.add(s);
		}
		return list;
	}
	
	public ArrayList<Solution> readSearch(String kelime) {
		ArrayList<Solution> list = new ArrayList();
		ResultSet rs;
		try {
			stmt = con.prepareStatement("SELECT * FROM solutions WHERE solution  LIKE ?");
			stmt.setString(1, "%" + kelime + "%");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Solution p = new Solution();
				p.setSolution(rs.getString("solution"));
				p.setSolutionsID(rs.getInt("SolutionID"));
				p.setProbID(rs.getInt("ProbID"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
}