package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Problem;
import Entity.Tag;
import Model.TagsModel;

public class TagsController extends Controller {
	TagsModel tm = new TagsModel();
	
	public ArrayList<Tag> readlast() {
		return tm.readlast();
	}
	
	public ArrayList<Tag> resulttagID(int id) {
		return tm.resulttagID(id);
	}
	
	public ArrayList<Tag> readSearch(String kelime) {
		return tm.readSearch(kelime);
	}
	
	public ArrayList<Problem> readSearchTagProb(int tagid) {
		return tm.readSearchTagProb(tagid);
	}
	
	public ArrayList<Tag> readlastyuz() {
		return tm.readlastyuz();
	}
	
	public void addTag(Tag tag) {
		try {
			tag.setTag(stripHTML(tag.getTag()));
			tm.addTag(tag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addTagsProblem(Tag tag) {
		try {
			tm.addTagsProblem(tag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTagProblem(int id,int newVal) {
			try {
				tm.deleteTagProblem(id,newVal);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void deleteTag(int id) {
		try {
			tm.deleteTag(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTag(Tag tag) {
		try {
			tag.setTag(stripHTML(tag.getTag()));
			tm.updateTag(tag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
		
	public Boolean isTagExist(Tag tag) {
		Boolean sondeger= false;
		try {
			sondeger = tm.isTagExist(tag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sondeger;
	}
	
}
