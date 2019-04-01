package View.Update;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SolutionsController;
import Entity.Solution;

/**
 * Servlet implementation class updateSolution
 */
@WebServlet("/updateSolution")
public class UpdateSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSolution() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		SolutionsController sc=new SolutionsController();
		Solution sol=new Solution();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String newVal = request.getParameter("newVal");

		sol.setSolutionsID(id);
		sol.setSolution(newVal);
		
		sc.updateSolution(sol);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
