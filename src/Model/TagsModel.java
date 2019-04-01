package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Problem;
import Entity.Tag;

public class TagsModel extends Model {

	public void addTag(Tag rmrk) throws SQLException {
		stmt = con.prepareStatement("INSERT INTO tags VALUE(NULL,?)");
		stmt.setString(1, rmrk.getTag());
		int rs = stmt.executeUpdate();
		System.out.println("Tag eklendi");
	}

	public void updateTag(Tag rmrk) throws SQLException {
		stmt = con.prepareStatement("UPDATE tags SET tag=? WHERE TagID=?");
		stmt.setString(1, rmrk.getTag());
		stmt.setInt(2, rmrk.getTagID());
		int rs = stmt.executeUpdate();
		System.out.println("Tag guncellendi");
	}

	public void deleteTag(int id) throws SQLException {
		stmt = con.prepareStatement("Delete FROM tags WHERE TagID=?");
		stmt.setInt(1, id);
		int rs = stmt.executeUpdate();
		System.out.println("Tag silindi");
	}

	public ArrayList<Tag> readByID(int id) throws SQLException {
		ArrayList<Tag> liste2 = new ArrayList();
		stmt = con.prepareStatement("SELECT * FROM tags WHERE TagID=?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Tag tag = new Tag();
			tag.setTagID(rs.getInt("TagID"));
			tag.setTag(rs.getString("tag"));
			liste2.add(tag);
		}

		for (int i = 0; i < liste2.size(); i++) {
			System.out.println(liste2.get(i).getTag());
		}

		return liste2;
	}

	public boolean isTagExist(Tag rmrk) throws SQLException {

		boolean result = false;

		PreparedStatement stmt;
		int kontrol = 0;
		stmt = con.prepareStatement("SELECT COUNT(*) FROM tags WHERE tag=?");
		stmt.setString(1, rmrk.getTag());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			kontrol = rs.getInt(1);
			if (kontrol > 0) {
				result = true;
			}
		}
		return result;
	}

	public ArrayList<Problem> getProblemsByHashtag(Tag tagdeger) throws SQLException {
		ArrayList<Problem> hashtaglist = new ArrayList();

		stmt = con.prepareStatement(
				"SELECT * FROM tagproblems INNER JOIN problems ON tagproblems.ProbID=problems.ProbID WHERE tagproblems.TagID=(SELECT tagID FROM tags WHERE tag=?)");
		stmt.setString(1, tagdeger.getTag());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Problem prblm = new Problem();
			prblm.setProblem(rs.getString("problem"));
			hashtaglist.add(prblm);
		}

		for (int i = 0; i < hashtaglist.size(); i++) {
			System.out.println(hashtaglist.get(i).getProblem());
		}

		return hashtaglist;
	}

	public ArrayList<Tag> readlast() {
		ArrayList<Tag> list = new ArrayList<Tag>();
		ResultSet rs;
		try {
			stmt = con.prepareStatement("SELECT * FROM tags ORDER BY tag");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Tag t = new Tag();
				t.setTagID(rs.getInt("TagID"));
				t.setTag(rs.getString("tag"));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<Tag> readSearch(String kelime) {
		ArrayList<Tag> list = new ArrayList();
		ResultSet rs;
		try {
			stmt = con.prepareStatement("SELECT * FROM tags WHERE tag  LIKE ?");
			stmt.setString(1, "%" + kelime + "%");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Tag p = new Tag();
				p.setTag(rs.getString("tag"));
				p.setTagID(rs.getInt("TagID"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<Problem> readSearchTagProb(int tagid) {
		ArrayList<Problem> list = new ArrayList();
		ResultSet rs;
		try {
			stmt = con.prepareStatement("SELECT * FROM problems INNER JOIN tagproblems ON problems.ProbID=tagproblems.ProbID WHERE TagID=?");
			stmt.setInt(1, tagid);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Problem p = new Problem();
				p.setProblem(rs.getString("problem"));
				p.setProbID(rs.getInt("ProbID"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<Tag> resulttagID(int id) {
		ArrayList<Tag> list = new ArrayList<Tag>();
		ResultSet rs;
		try {
			stmt = con.prepareStatement("SELECT * FROM tags INNER JOIN tagproblems ON tags.TagID=tagproblems.TagID WHERE tagproblems.ProbID=? ORDER BY tag");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Tag t = new Tag();
				t.setTagID(rs.getInt("TagID"));
				t.setTag(rs.getString("tag"));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public void addTagsProblem(Tag tag) throws SQLException {
		stmt = con.prepareStatement("INSERT INTO tagproblems VALUE(NULL,?,?)");
		stmt.setInt(1, tag.getTagID());
		stmt.setInt(2, tag.getProbID());
		int rs = stmt.executeUpdate();
		System.out.println("Tag eklendi");
	}
	
	public void deleteTagProblem(int id,int newVal) throws SQLException {
		stmt = con.prepareStatement("Delete FROM tagproblems WHERE TagID=? AND ProbID=?");
		stmt.setInt(1, newVal);
		stmt.setInt(2, id);
		int rs = stmt.executeUpdate();
		System.out.println("Tag silindi");
	}
	
	public ArrayList<Tag> readlastyuz() {
		ArrayList<Tag> list = new ArrayList<Tag>();
		ResultSet rs;
		try {
			stmt = con.prepareStatement("SELECT * FROM tags ORDER BY TagID DESC LIMIT 100");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Tag t = new Tag();
				t.setTagID(rs.getInt("TagID"));
				t.setTag(rs.getString("tag"));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
}
