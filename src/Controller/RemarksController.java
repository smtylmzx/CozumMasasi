package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Remark;
import Entity.Solution;
import Model.RemarksModel;

public class RemarksController extends Controller {

	RemarksModel rm = new RemarksModel();

	public ArrayList<Remark> readByProbID(int id) {
		try {
			return rm.readByProbID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateRemark(Remark remark) {
		try {
			remark.setRemark(stripHTML(remark.getRemark()));
			rm.updateRemark(remark);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRemark(int remark) {
		try {
			rm.deleteRemark(remark);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addRemark(Remark remark) {
		try {
			remark.setRemark(stripHTML(remark.getRemark()));
			rm.addRemark(remark);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
