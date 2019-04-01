package View.Add;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SolutionsController;
import Controller.TagsController;
import Entity.Solution;
import Entity.Tag;

/**
 * Servlet implementation class IsTagExist
 */
@WebServlet("/IsTagExist")
public class IsTagExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsTagExist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TagsController tc = new TagsController();
		PrintWriter out = response.getWriter();
		
		Tag tag = new Tag();
		
		String newEtiket = request.getParameter("newEtiket");

		tag.setTag(newEtiket);

		
	out.print("{\"donendeger\":"+tc.isTagExist(tag)+"}");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
