package View.Add;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class AddProblem
 */
@WebServlet("/AddProblem")
public class AddProblem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProblem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ProblemsController pc = new ProblemsController();
		Problem prob = new Problem();
		

		String gelenprob = request.getParameter("gelenprob");
		String gelencozum = request.getParameter("gelencozum");

		prob.setProblem(gelenprob);

		int id =pc.addProblem(prob);
		
		if (id!= 0 && gelencozum!="") {
			Solution s = new Solution();
			s.setProbID(id);
			s.setSolution(gelencozum);
						
			SolutionsController sc=new SolutionsController();
			sc.addSolution(s);
		}else {
			String gelenprob1 = request.getParameter("gelenprob");
			prob.setProblem(gelenprob1);
		}
		
		out.print("{\"problemid\":"+ id +"}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
