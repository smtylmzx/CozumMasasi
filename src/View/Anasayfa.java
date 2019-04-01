
package View;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.ProblemsController;
import Controller.TagsController;
import Entity.Problem;
import Entity.Tag;

/**
 * Servlet implementation class Anasayfa
 */
@WebServlet("/index.html")
public class Anasayfa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Anasayfa() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String kelime= null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, EEEE");
        String currentDate = sdf.format(new Date());
	
		PrintWriter out = response.getWriter();
		ProblemsController pc = new ProblemsController();
		TagsController tc = new TagsController();
		
		int id =0;

		ArrayList<Problem> result = pc.readAll();
		ArrayList<Problem> resultlast = pc.readlast();
		HashMap<String,Integer> resultcounterror = pc.statisticErrors();
		ArrayList<Tag> resultlasttag = tc.readlast();
		ArrayList<Tag> resulttagID = tc.resulttagID(id);


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
		
		out.print("<script>"
				+ "var yeniproblemid=0;"
				+ "var problem;"
				+	"function addFunction(problemdeger,cozumdeger) {\n"
				+	"var ekrandanGelenProblem = $('#' + problemdeger).val();\n"
				+	"var ekrandanGelenCozum = $('#' + cozumdeger).val();\n"
				
			   +" if (ekrandanGelenProblem==''){"
		 		+"alert(\"HATA BOÞ BIRAKILAMAZ\");}"			 		
			    +"else if (ekrandanGelenProblem.length <= 20 ){"
		 		+"alert(\"HATA 20 KARAKTERDEN KÜÇÜK OLAMAZ\");}else{"
			    
				+	"$.post('AddProblem', {\n"
				 		+	"gelenprob: ekrandanGelenProblem,"
				 		+ 	"gelencozum:ekrandanGelenCozum\n"				 	
				 		+	"},\n"
				 		+	"function(result){"
				 		
				 		+	"problem = $.parseJSON(result);"
				 		+	"yeniproblemid=problem.problemid;"
				 		
				 		+	"},\n"
				 +	")}; }\n\n\n"
				 		
				+	"function etiketalaniniac() {\n"
				+	"$('#kayitalani').slideUp();"
				+	"$('#probaddbtn').hide();"
				+ 	"$('#etiketalani').slideDown();"
				+	"};\n\n\n" +
				
				"	$(document).ready(function(){" +
				"   $('#add').click(function() {" +
				"	var optiondangelenval=document.getElementById(\"selectoptionitemadd\");" +
				"	var gelendeger=optiondangelenval.options[optiondangelenval.selectedIndex].value;" + 
				"	ekle(gelendeger,yeniproblemid);" +
				"    return !$('#selectoptionitemadd option:selected').remove().appendTo('#selectoptionitemdelete');" + 
				"   });" + 
				
				"   $('#remove').click(function() {" +
				"	var optiondangelenval2=document.getElementById(\"selectoptionitemdelete\");" +
				"	var gelendeger2=optiondangelenval2.options[optiondangelenval2.selectedIndex].value;" + 
				"	sil(gelendeger2,yeniproblemid);" +				
				"    return !$('#selectoptionitemdelete option:selected').remove().appendTo('#selectoptionitemadd');" + 
				"   });" +
				" }); "
				
				+ "function ekle(tagid,probid){"
				+ "$.post('AddTagProblem', {"
		 		+	"id: probid,"
		 		+	"newVal: tagid,"
			 		+	"},"
			 		+	"function(result){"
			 			+	""
			 			+	"}"
			 	+	")}"
			 			
				+ "function sil(tagid,probid){"
				+ "$.post('DeleteTagProblem', {"
		 		+	"id: probid,"
		 		+	"newVal: tagid,"
			 		+	"},"
			 		+	"function(result){"
			 			+	""
			 			+	"}"
			 	+	")}"
				
			 	
				+	"function kapatclose() {\n"
				+ "location.reload();"
				+ "};"

				
				+ "</script>");
		
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
									+ "<button class='btn form-control input-lg' type='button' style='font-weight:bold; font-size:15px; font-family:system-ui; background-color:#004892; color:#FFFFFF;' data-toggle='modal' data-target='#myModalprob'> YENÝ PROBLEM EKLE </button>"
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
								+ "<h4 style='font-weight:bold; text-align:left; font-size:20px; font-family:system-ui;'>ÝSTATÝSTÝKLER</h4>");
						out.println("<p style='height:17px;'></p>");

//										for (int i = 0; i < resultlast.size(); i++) { out.println("<li><a href='#'>" + resultlast.get(i).getProblem().substring(0,20) + "...</a></li>"); }
										
						
						
						out.print("<div class='row' style='text-align:center;'>"
									+ "<a href='DetayStatistics?cat=problemAll'><div class='col-md-4' style='color:#D61515;'>"
										+ "<i class='fa fa-exclamation-triangle fa-5x' aria-hidden='true'></i><br/>"
										+ "<b>TOPLAM HATA</b><br/>");
						
							out.println("<b style='font-size:25px;'>" + resultcounterror.get("errorCount") + "</b>");
						
							out.print("</div></a>"
									+ "<a href='DetayStatistics?cat=solutionAll'><div class='col-md-4' style='color:#178217;'>"
										+ "<i class='fa fa-check-square fa-5x' aria-hidden='true'></i><br/>"
										+ "<b>ÇÖZÜLEN HATA</b><br/>");
							out.println("<b style='font-size:25px;'>" + resultcounterror.get("solutionCount") + "</b>");

							out.print("</div>"
									+ "<a href='DetayStatistics?cat=pendingAll'><div class='col-md-4' style='color:#004892;'>"
										+ "<i class='fa fa-pencil-square fa-5x' aria-hidden='true'></i><br/>"
										+ "<b>BEKLEYEN HATA</b><br/>");
										out.println("<b style='font-size:25px;'>" + resultcounterror.get("pandingCount") + "</b>");
							out.print("</div>"
								+ "</div>");
							
							out.println("<p style='height:15px;'></p>");

						
					out.print("</div>"
				+ "</div>");
		
				
				 
					out.print("<div class='row'>"
							+ "<div class='col-md-2'>"	
							+ "<a href='DetayTag'><button class='btn form-control input-lg' type='button' style='font-weight:bold; font-size:15px; font-family:system-ui; height:100px; background-color:#004892; color:#FFFFFF;'> ETÝKET </br> ÝÞLEMLERÝ </button></a>"
							+ "</div>"
							+ "<div class='col-md-10'>");
						
						out.print("<div class='owl-carousel owl-theme' >");
//						out.println(pc.readByTagID(id).getTag());
						 for (int i = 0; i < resultlasttag.size(); i++) { 
								 out.print(" <a href='TagSearch?tagid=" + resultlasttag.get(i).getTagID() +"'><div class='item'>#"+ resultlasttag.get(i).getTag() + "</div></a>");
							 }
						out.print("</div>"
								+ "</div>"
								+ "</div>"
								+ "</div>"
						+ "</div>");
						
						out.print("<div class='row'><div class='col-md-12'><i><b>UYARI:</b> Internet Explorer tarayýcýsý desteklenmemektedir.</i></div></div>");

						 out.print("</div>");
		

				 
					out.print("  <div class='modal fade' id='myModalprob' role='dialog'>" + 
							"    <div class='modal-dialog'>" + 
							"      <div class='modal-content'>" + 
							"        <div class='modal-header'>" + 
							"          <button type='button' class='close' data-dismiss='modal'>&times;</button>" + 
							"          <h4 class='modal-title'><b style='color:#004892;'>PROBLEM EKLE</b></h4>" + 
							"        </div>" + 
							"        <div class='modal-body'>" + 
							"			<p>" +

		"<div class='row' id='etiketalani' style='display:none; text-align:center; margin-bottom:20px;'>"
		+ ""
		+ ""
		+ ""
		+ ""
		+ "" +
		"<div class='col-md-12'>" + 
		"<div class='col-md-5'>"
		+ "" + 
		"<select multiple style='height:250px;' id='selectoptionitemadd' class='form-control'>");
for (int i = 0; i < resultlasttag.size(); i++) { 
	out.print("<option class='optionitem' value='"+ resultlasttag.get(i).getTagID() +"'><div class='item'>"+ resultlasttag.get(i).getTag() + "</div></option>");
}
out.print("</select>" + 
		"" + 
		"</div>" +
		"<div class='col-md-2' style='margin-left:-10px;'>" + 
		"" + 
		"<a href='#' id='add' class='btn btn-primary' style='width:120%; margin-top:80px;'>EKLE &gt;&gt;</a><br/><br/>" + 
		"<a href='#' id='remove' class='btn btn-danger' style='width:120%;'>&lt;&lt; SÝL</a>" + 
		""
		+ "</div>" +
		"<div class='col-md-5'>"
		+ "" + 
		"<select multiple style='height:250px;' id='selectoptionitemdelete' class='form-control'>");
		for (int i = 0; i < resulttagID.size(); i++) { 
			out.print("<option class='optionitem2' value='"+ resulttagID.get(i).getTagID() +"'><div class='item'>"+ resulttagID.get(i).getTag() + "</div></option>");
		}
	out.print("</select>" + 
		""
		+ "</div>" +

		"</div>"
			
		+ ""
		+ ""
		+ ""
		+ ""
		+ ""
		+ "</div>"

		+ "<div id='kayitalani' style='background-color:#FFFFFF;'><div class='row' style='text-align:center;'>"
				+"<div class='col-md-12'>"
					+ "<textarea placeholder='Kýsaca Sorununuzu Yazýnýz..' class='form-control' rows='4' style='resize: vertical;' id='problemdeger'></textarea><br/>"
				+ "</div>"
		+ "</div>" +
				
		"<div class='row' text-align:center;'>"
				+"<div class='col-md-12'>"
					+ "<textarea placeholder='Eðer Bir Çözümünüz Varsa Yazýnýz..' class='form-control' rows='4' style='resize: vertical;' id='cozumdeger'></textarea><br/>"
				+ "</div>"
		+ "</div>"
							
		+ "<div class='row' style='text-align:center;'>"
		+ "<i style='font-weight:bold; font-size:20px;' onClick='addFunction(\"problemdeger\",\"cozumdeger\");etiketalaniniac();'>Probleme etiket eklemek için týklayýn..</i>"
		+ "</div></div>" +
		
		
							"			</p>" + 
							"        </div>" + 
							"        <div class='modal-footer'>" + 
							"          <button type='button' class='btn btn-primary soln' name='soldznlbtn' id='probaddbtn' data-dismiss='modal' onClick='addFunction(\"problemdeger\",\"cozumdeger\");kapatclose();'>KAYDET</button>" + 
							"          <button type='button' class='btn btn-default' data-dismiss='modal' onClick='kapatclose();'>KAPAT</button>" + 
							"        </div>" + 
							"      </div>" + 
							"    </div>" + 
							"  </div>"
				 
	+ "<p style='height:20px;'></p>");
	out.println("<div style='height:10px; width:100%; background-color:#004892; bottom:0px; position:fixed; z-index:1;'></div>");

				
		out.println("<script>"
				+ "var owl = $('.owl-carousel');\r\n" + 
				"owl.owlCarousel({\r\n" + 
				"    items:20,\r\n" + 
				"    loop:true,\r\n" + 
				"    margin:10,\r\n" + 
				"    autoplay:true,\r\n" + 
				"    autoplayTimeout:2000,\r\n" + 
				"    autoplayHoverPause:true,\r\n" +
				"  autoWidth:true\r\n" + 
				"});\r\n" + 
				"$('.play').on('click',function(){\r\n" + 
				"    owl.trigger('play.owl.autoplay',[1000])\r\n" + 
				"})\r\n" + 
				"$('.stop').on('click',function(){\r\n" + 
				"    owl.trigger('stop.owl.autoplay')\r\n" + 
				"})</script>");
		
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
