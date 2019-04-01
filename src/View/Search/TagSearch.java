package View.Search;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.ProblemsController;
import Controller.SolutionsController;
import Controller.TagsController;
import Entity.Problem;
import Entity.Solution;
import Entity.Tag;

/**
 * Servlet implementation class TagSearch
 */
@WebServlet("/TagSearch")
public class TagSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, EEEE");
        String currentDate = sdf.format(new Date());
	
        int tagid=Integer.parseInt(request.getParameter("tagid"));
        
		PrintWriter out = response.getWriter();
		ProblemsController pc = new ProblemsController();
		TagsController tc = new TagsController();
		
		ArrayList<Problem> resultSearchTagProb = new ArrayList();
		
		int toplamsonuc=0;
		
			resultSearchTagProb = tc.readSearchTagProb(tagid);
			toplamsonuc=resultSearchTagProb.size();
		
		
		ArrayList<Problem> resultlast = pc.readlast();

		out.println("<html><head>"
				+ "<title>ÇÖZÜM MASASI</title>"

				+ "<!-- CSS FOLDERS -->"
				+ "<link href='plugins/bootstrap/css/bootstrap.min.css' rel='stylesheet'>"
				+ "<link href='plugins/font-awesome/css/font-awesome.min.css' rel='stylesheet'>"
				+ "<link href='plugins/owl-carousel/assets/owl.carousel.css' rel='stylesheet'>"
				+ "<link href='plugins/owl-carousel/assets/owl.theme.default.css' rel='stylesheet'>"
				+ "<link href='plugins/style.css' rel='stylesheet'>"
				
				+ "<!-- JS FOLDERS -->"
				+ "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
				+ "<script src='plugins/owl-carousel/owl.carousel.min.js' rel='stylesheet'></script>"
				+ "<script src='plugins/bootstrap/js/bootstrap.min.js'></script>");
		
		out.println("</head><body>");
		out.println("<div style='height:10px; width:100%; background-color:#004892;'></div>");
		out.println("<div class='container' style='margin-top:20px;'>"
					+ "<div class='row'>"
						+ "<div class='col-md-4'>"
							+ "<a href='index.html'><img src='Images/cozummasasilogo.png' style='width:350px; height:auto;'></a>"
						+ "</div>"

						+ "<div class='col-md-6' style='margin-top:20px;'>"
						+ "<form action='DetaySearch' method='GET'>"

						+ "<div class='input-group'>"

						+ "<input type='text' class='form-control input-lg' placeholder='Problem, çözüm ve ya etiket arayýn' name='search' required>"
						
								+ "<span class='input-group-btn' style='width:175px;'>"
								+ "<select name='cat' id='dropdown' class='form-control input-lg'>"
								+ "<option value='problemSearch'>Problem Ara</option>"
								+ "<option value='solutionSearch'>Çözüm Ara</option>"
								+ "<option value='tagSearch'>Etiket Ara</option>"
								+ "</select>"
								+ "</span>"
								
								+ "<span class='input-group-btn'>"
									+ "<button type='submit' class='btn input-lg' type='button' style='background-color:#004892; color:#FFFFFF;'><i class='fa fa-search' aria-hidden='true'></i></button>"
								+ "</span>"
									
						+ "</div>"
						+ "</form>"

					+ "</div>"
					+ "<div class='col-md-2' style='margin-top:32px; text-align:right;'>" + (currentDate)
					+ "</div>"
				+ "</div>"

					+ "<p style='height:20px;'></p>"
					
					+ "<div class='row'>"
						+ "<div class='col-md-4'>"
							+ "<div class='panel panel-default' style='background-color:#f8f7ff;'>"
								+ "<div class='panel-body'>"
									+ "<a href='index.html'><button class='btn form-control input-lg' type='button' style='font-weight:bold; font-size:15px; font-family:system-ui; background-color:#004892; color:#FFFFFF;'> ANASAYFA </button></a>"
									+ "<p>&nbsp;</p>"
									+ "<h4 style='font-weight:bold; text-align:center; font-size:20px; font-family:system-ui;'>SON EKLENEN HATALAR</h4>");
										out.println("<ul style='font-size:20px; font-family:serif;'>");
											for (int i = 0; i < resultlast.size(); i++) { out.println("<li><a href='detayServlet?id=" + resultlast.get(i).getProbID() +"'>" + resultlast.get(i).getProblem().substring(0,20) + "...</a></li>"); }
										out.println("</ul>");
						out.print("</div>"
							+ "</div>"
						+ "</div>"
						
						+ "<div class='col-md-8'>"
							+ "<div class='panel panel-default' style='background-color:#f8f7ff;'>"
							+ "<div class='panel-body'>"
								+ "<h4 style='font-weight:bold; text-align:left; font-size:20px; font-family:system-ui; float:left;'>ARANAN ETÝKETE UYGUN SONUÇLAR</h4>"
								+ "<i style='float:right; margin-top:10px;'>Toplam <b>" + toplamsonuc + "</b> sonuç bulundu.</i>"
							+ "</div>"
							+ "</div>");

							for (int i = 0; i < resultSearchTagProb.size(); i++) { out.println("<li><a href='detayServlet?id=" + resultSearchTagProb.get(i).getProbID() + "'>" + resultSearchTagProb.get(i).getProblem() + "</a></li>"); }
							
							out.print("</div>"
				+ "</div>"
		
			+ "</div>"
							+ "<p style='height:20px;'></p>");
							out.println("<div style='height:10px; width:100%; background-color:#004892; bottom:0px; position:fixed; z-index:1;'></div>");


		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
