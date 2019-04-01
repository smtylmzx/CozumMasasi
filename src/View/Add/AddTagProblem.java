package View.Add;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.TagsController;
import Entity.Tag;

/**
 * Servlet implementation class AddTag
 */
@WebServlet("/AddTagProblem")
public class AddTagProblem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTagProblem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TagsController tc = new TagsController();
		Tag tag = new Tag();

		int id = Integer.parseInt(request.getParameter("id"));
		int newVal = Integer.parseInt(request.getParameter("newVal"));

		tag.setProbID(id);
		tag.setTagID(newVal);

		tc.addTagsProblem(tag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
