package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Problem;

public class ProblemsModel extends Model {

	public int addProblem(Problem prblm) throws SQLException {

		int lastId = 0;

		stmt = con.prepareStatement("INSERT INTO problems VALUES(NULL,?,NOW())", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, prblm.getProblem());
		stmt.executeUpdate();

		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			lastId = rs.getInt(1);
		}

		return lastId;
	}

	public void updateProblem(Problem prblm) throws SQLException {
		stmt = con.prepareStatement("UPDATE problems SET problem=? WHERE ProbID=?");
		stmt.setString(1, prblm.getProblem());
		stmt.setInt(2, prblm.getProbID());

		int rs = stmt.executeUpdate();
		System.out.println("Problem Güncellendi");
	}

	public Problem readByID(int id) throws SQLException {
		stmt = con.prepareStatement("SELECT * FROM problems WHERE ProbID=?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			Problem problem = new Problem();
			problem.setProbID(rs.getInt("ProbID"));
			problem.setProblem(rs.getString("problem"));
			return problem;
		}
		return null;
	}

	public ArrayList<Problem> readAll() {
		ArrayList<Problem> list = new ArrayList();
		ResultSet rs;
		try {
			stmt = con.prepareStatement("SELECT * FROM problems ORDER BY ProbID DESC");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Problem p = new Problem();
				p.setProblem(rs.getString("problem"));
				p.setProbID(rs.getInt("ProbID"));
				p.setDate(rs.getDate("date"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<Problem> readSolAll() {
		ArrayList<Problem> list = new ArrayList();
		ResultSet rs;
		try {
			stmt = con.prepareStatement(
					"SELECT * FROM problems WHERE problems.ProbID IN (SELECT ProbID FROM solutions) ORDER BY ProbID DESC");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Problem p = new Problem();
				p.setProblem(rs.getString("problem"));
				p.setProbID(rs.getInt("ProbID"));
				p.setDate(rs.getDate("date"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<Problem> readPendAll() {
		ArrayList<Problem> list = new ArrayList();
		ResultSet rs;
		try {
			stmt = con.prepareStatement(
					"SELECT * FROM problems WHERE problems.ProbID NOT IN (SELECT ProbID FROM solutions) ORDER BY ProbID DESC");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Problem p = new Problem();
				p.setProblem(rs.getString("problem"));
				p.setProbID(rs.getInt("ProbID"));
				p.setDate(rs.getDate("date"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Problem> readlast() {
		ArrayList<Problem> list = new ArrayList();
		ResultSet rs;
		try {
			stmt = con.prepareStatement("SELECT * FROM problems ORDER BY ProbID DESC LIMIT 8");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Problem p = new Problem();
				p.setProbID(rs.getInt("ProbID"));
				p.setProblem(rs.getString("problem"));
				p.setDate(rs.getDate("date"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public HashMap statisticErrors() {

		HashMap<String, Integer> sterror = new HashMap<String, Integer>();
		ResultSet rs;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM problems");
			rs = stmt.executeQuery();
			while (rs.next()) {
				sterror.put("errorCount", rs.getInt(1));
			}
			stmt = con.prepareStatement("SELECT COUNT(DISTINCT(ProbID)) FROM solutions");
			rs = stmt.executeQuery();
			while (rs.next()) {
				sterror.put("solutionCount", rs.getInt(1));
			}
			stmt = con.prepareStatement(
					"SELECT COUNT(*) FROM problems WHERE problems.ProbID NOT IN (SELECT ProbID FROM solutions)");
			rs = stmt.executeQuery();
			while (rs.next()) {
				sterror.put("pandingCount", rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// for(Map.Entry gosterici : newHashMap.entrySet())
		// {
		// System.out.println(gosterici.getKey()+" "+gosterici.getValue());
		// }
		return sterror;
	}

	public void deleteProblem(int id) throws SQLException {
		stmt = con.prepareStatement("DELETE FROM problems WHERE ProbID=?");
		stmt.setInt(1, id);
		int rs = stmt.executeUpdate();
		
		stmt = con.prepareStatement("DELETE FROM solutions WHERE ProbID=?");
		stmt.setInt(1, id);
		int rs2 = stmt.executeUpdate();
		
		stmt = con.prepareStatement("DELETE FROM remarks WHERE ProbID=?");
		stmt.setInt(1, id);
		int rs3 = stmt.executeUpdate();

		System.out.println("Silindi");
	}

//	public ArrayList<Problem> searchInstant(String kelime) {
//
//		ArrayList<Problem> list = new ArrayList();
//		ResultSet rs;
//		try {
//			stmt = con.prepareStatement("SELECT * FROM problems WHERE problem  LIKE '%?%' LIMIT 10");
//			stmt.setString(1, kelime);
//			rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				Problem p = new Problem();
//				p.setProblem(rs.getString("problem"));
//				list.add(p);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//
//		// while($yaz=mysql_fetch_array($al)){
//		// echo "<div class='kelime' onClick='tamamla(\"".$yaz["EserAdi"]."\")'><font
//		// color='#000000'>".$yaz["EserAdi"]."</font></div>";
//		// }
//	}
	
	
	public ArrayList<Problem> readSearch(String kelime) {
		ArrayList<Problem> list = new ArrayList();
		ResultSet rs;
		try {
			stmt = con.prepareStatement("SELECT * FROM problems WHERE problem  LIKE ?");
			stmt.setString(1, "%" + kelime + "%");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Problem p = new Problem();
				p.setProblem(rs.getString("problem"));
				p.setProbID(rs.getInt("ProbID"));
				p.setDate(rs.getDate("date"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
