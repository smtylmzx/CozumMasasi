package View.Add;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.RemarksController;
import Controller.SolutionsController;
import Entity.Remark;
import Entity.Solution;

/**
 * Servlet implementation class AddRemark
 */
@WebServlet("/AddRemark")
public class AddRemark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRemark() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RemarksController rc = new RemarksController();
		Remark remark = new Remark();

		int id = Integer.parseInt(request.getParameter("id"));
		String newVal = request.getParameter("newVal");

		remark.setProbID(id);
		remark.setRemark(newVal);

		rc.addRemark(remark);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
