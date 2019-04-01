package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Remark;

public class RemarksModel extends Model {

	public void addRemark(Remark rmrk) throws SQLException {
		stmt = con.prepareStatement("INSERT INTO remarks VALUE(NULL,?,?)");
		stmt.setString(1, rmrk.getRemark());
		stmt.setInt(2, rmrk.getProbID());
		int rs = stmt.executeUpdate();
		System.out.println("Not eklendi");
	}

	public void updateRemark(Remark rmrk) throws SQLException {
		stmt = con.prepareStatement("UPDATE remarks SET remark=? WHERE RemarkID=?");
		stmt.setString(1, rmrk.getRemark());
		stmt.setInt(2, rmrk.getRemarkID());
		int rs = stmt.executeUpdate();
		System.out.println("Notunuz guncellendi");
	}

	public void deleteRemark(int id) throws SQLException {
		stmt = con.prepareStatement("Delete FROM remarks WHERE RemarkID=?");
		stmt.setInt(1, id);
		int rs = stmt.executeUpdate();
		System.out.println("Not silindi");
	}

	public ArrayList<Remark> readByProbID(int id) throws SQLException {
		ArrayList<Remark> liste2 = new ArrayList();
		stmt = con.prepareStatement("SELECT * FROM remarks WHERE probID=? ORDER BY RemarkID DESC");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Remark remark = new Remark();
			remark.setProbID(rs.getInt("ProbID"));
			remark.setRemarkID(rs.getInt("RemarkID"));
			remark.setRemark(rs.getString("remark"));
			liste2.add(remark);
		}
		return liste2;
	}
}