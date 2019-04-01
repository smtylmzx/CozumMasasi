package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Util.Database;

public class Model {
	Connection con;
	PreparedStatement stmt;

	public Model() {
		con = Database.baglan();
		System.out.println("");
	}
}
