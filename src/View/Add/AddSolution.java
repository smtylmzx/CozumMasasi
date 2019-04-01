package View.Add;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.ProblemsController;
import Controller.SolutionsController;
import Entity.Problem;
import Entity.Solution;

/**
 * Servlet implementation class AddSolution
 */
@WebServlet("/AddSolution")
public class AddSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSolution() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SolutionsController sc = new SolutionsController();
		Solution sol = new Solution();

		int id = Integer.parseInt(request.getParameter("id"));
		String newVal = request.getParameter("newVal");

		sol.setProbID(id);
		sol.setSolution(newVal);

		sc.addSolution(sol);
		
//		--------------------------------
		
//		String gelencoz = request.getParameter("gelencoz");
//		sol.setSolution(gelencoz);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
