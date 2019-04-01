package View.Search;

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
 * Servlet implementation class DetayTagSearch
 */
@WebServlet("/DetayTagSearch")
public class DetayTagSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetayTagSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, EEEE");
        String currentDate = sdf.format(new Date());
        
        String kelime=request.getParameter("search");
        
		PrintWriter out = response.getWriter();
		ProblemsController pc = new ProblemsController();
		TagsController tc = new TagsController();

		ArrayList<Problem> result = pc.readAll();
		HashMap<String,Integer> resultcounterror = pc.statisticErrors();
		ArrayList<Tag> resultlastyuz = tc.readlastyuz();
		ArrayList<Tag> resultTagSearch = new ArrayList();

		int toplamsonuc=0;		
	
		resultTagSearch = tc.readSearch(kelime);
			toplamsonuc=resultTagSearch.size();
	
			
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
		
		+	"function addFunction(addtag,addtagc) {"
		+	"var ekrandanGelenTag = $('.' + addtagc).val();"
		
			   +" if ( ekrandanGelenTag==''){"
		 			+"alert(\"ETÝKET BOÞ BIRAKILAMAZ\");}"
			   +"else{"		
				   	+	"$.post('IsTagExist', {"
			 		+	"newEtiket: ekrandanGelenTag,"	
				   	+   "}," 	
			 		   
			 		+	"function(result){"
			 		+ "var donendeger=$.parseJSON(result);"
			 				+"if(donendeger.donendeger==true){"
			 					+"alert('Bu Etiket Zaten Var!');}"
			 				
			 				+"else{"
			 					+	"$.post(addtag, {"
						 		+	"newEtiket: ekrandanGelenTag,"				 	
						 		+	"},"
						 		+	"function(result){"		 		
						 		+	"$('.basarili').css({'display': 'block'}).delay(2000).slideUp();"
					 			+	"setTimeout(function(){"
					 			+	"location.reload();},2500);"
						 		+	"}\n"
				 			+")}"
					+	"}"
					
				 +	")}"
		 + " };"
						 
				 
				+	"function deleteFunction(deleteItem,deleteId) {"
				+	"var ekrandanGelenId = deleteId;"
				+	"if(deleteItem=='DeleteTag'){ tanim='ETÝKETÝ'; }"
				+	"if (confirm(tanim + ' SÝLMEK ÜZERESÝNÝZ!!')) {"
				
				+	"$.post(deleteItem , {"
				 		+	"id: ekrandanGelenId,"
				 		+	"},"
				 		+	"function(result){"
				 		
				 			+	"$('.basarili').css({'display': 'block'}).delay(2000).slideUp();"
				 			+	"setTimeout(function(){"
				 			+	"location.reload(); },2500);"
				 			
				 			+	"},"
				 		+	")"
				 + "}else{ alert('ÝPTAL EDÝLDÝ..'); }"
				 +"};"	
				 
				 
				+	"function updateFunction(updateItem,updateNew,updateNewId,eskiveri,dznlbtngr) {"
				+	"var ekrandanGelenNew = $('.' + updateNew).val();"
				+	"var ekrandanGelenId = updateNewId;"
				+	"$.post(updateItem , {"
				 		+	"id: ekrandanGelenId,"
				 		+	"newVal: ekrandanGelenNew"
				 		+	"},"
				 		+	"function(result){"
				 			+ 	"$('.' + eskiveri).html(ekrandanGelenNew);"
							+	"$('.' + eskiveri).toggle();"
							+	"$('.' + updateNew).hide();"
							+	"$('.' + dznlbtngr).toggle();"
							
						+	"location.reload();"
				 		+	"},"
				 +	")};"
				 		
					+ "$(document).ready(function(){"
					+ "$('textarea').hide();"
					+ "$('.etiketeklenecektext').toggle();"
						+ "$('button.tagdznlbtn').hide();"
						
							+ "$.ortamgoster = {"
								+ "duzenle : function(dznl,dznlo,dznlbtngr){"
									+ "$('.' + dznl).toggle();"
									+ "$('.' + dznlo).hide();"
									+ "$('.' + dznlbtngr).hide();"
									+ "$('.gizlialan').css({'visibility': 'visible'});"
								+ "}"
							+ "};"
								
							+ "$.ortamiptal = {"
							+ "iptal : function(dznl,dznlo,dznlbtngr){"
								+ "$('.' + dznl).hide();"
								+ "$('.' + dznlo).toggle();"
								+ "$('.' + dznlbtngr).toggle();"
							+ "}"
						+ "};"

					+ "});"
				 								
				+ "</script>");
		
		
out.println("</head><body><div class='basarili' style='display:none; position:fixed; z-index:1; width:100%; height:50px; background-color:#178217; color:#FFFFFF; text-align:center; padding-top:10px; font-size:25px;'><b><i class='fa fa-check' aria-hidden='true'></i> BAÞARILI</b></div>");
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
						
					+ "<p style='height:10px;'></p>"
					
					
			+ "<div class='panel panel-default' style='background-color:#f8f7ff;'>"
				+ "<div class='row'>"	
					+ "<div class='panel-body'>"
						+ "<div class='col-md-4'>"					
								+ "<button class='btn form-control input-lg' type='button' style='font-weight:bold; font-size:15px; font-family:system-ui; background-color:#004892; color:#FFFFFF;' data-toggle='modal' data-target='#myModaladdtag'> YENÝ ETÝKET EKLE </button>"
						+ "</div>"
						+ "<div class='col-md-8'>"
							
		
		+ "<form action='DetayTagSearch' method='GET'>"
		
		+ "<div class='input-group'>"
		
		+ "<input type='text' class='form-control input-lg' placeholder='Etiket arayýn' name='search' value='" + kelime + "'>"
											
				+ "<span class='input-group-btn'>"
				+ "<button type='submit' class='btn input-lg' type='button' style='background-color:#004892; color:#FFFFFF;'>FÝLTRELE &nbsp<i class='fa fa-filter' aria-hidden='true'></i></button>"
				+ "</span>"
					
		+ "</div>"
		+ "</form>"

						+ "</div>"	
					+ "</div>"
				+ "</div>"
			+ "</div>"
						);
								
	
		out.print( "<div class='panel panel-default' style='background-color:#f8f7ff;'>"
		+ "<div class='panel-body'>"
			+ "<h4 style='font-weight:bold; text-align:left; font-size:20px; font-family:system-ui; float:left;'>ARAMA SONUÇLARI</h4>"
			+ "<i style='float:right; margin-top:10px;'>Toplam <b>" + toplamsonuc + "</b> sonuç bulundu.</i>"
		+ "</div>"
		+ "</div>");

	
		out.print("<div class='panel-body'>"); 	
		for (int i = 0; i < resultTagSearch.size(); i++) { 
			 out.print("<div class='item' style='float:left; margin-left:8px; margin-top:8px;'>"
			 		+ "<span class='tago"+ i +"'><a href='TagSearch?tagid=" + resultlastyuz.get(i).getTagID() + "'>#"+ resultTagSearch.get(i).getTag() + "</a></br>"
								 +"<i class='fa fa-pencil fa-7x' aria-hidden='true' style='color:#6fba00;'  onClick='$.ortamgoster.duzenle(\"tagn" + i + "\",\"tago" + i + "\", \"tagf"+ i +"\")'></i>"
						+ "&nbsp;&nbsp;"
						
+ "<i class='fa fa-times fa-7x' aria-hidden='true' style='color:#D61515;' onClick='deleteFunction(\"DeleteTag\",\"" + resultTagSearch.get(i).getTagID() + "\")'></i></span>"

				
			 		+	"<div class='gizlialan' style='visibility:hidden;'>"
					+ "<textarea name='newTag' class='form-control tagn"+ i +"' rows='2' style='width:130px; height:40px;'>" + resultTagSearch.get(i).getTag() + "</textarea>"
			
					 +	"</div>"
		
			+	"<div class='btn-group gizlialan' style='visibility:hidden;'>"
				+	"<button type='submit' class='btn btn-success btn-sm  tagn"+ i +" tagdznlbtn' name='tagdznlbtn' value='KYDT' ;' onClick='updateFunction(\"UpdateTag\",\"tagn"+ i +"\",\""+ resultTagSearch.get(i).getTagID() + "\", \"tago"+ i +"\", \"tagf"+ i +"\")'>KYDT</button>"
				+	"<button type='submit' class='btn btn-danger btn-sm  tagn"+ i +" tagdznlbtn' name='tagdznlbtn' value='ÝPTL' onClick='$.ortamiptal.iptal(\"tagn" + i + "\",\"tago" + i + "\", \"tagf"+ i +"\")'>ÝPTL</button>"
				+ "</div>"
				
				+	"</div>");
		 }
	
		out.print( "</div>"
		+ "</div>");
			
					out.print("  <div class='modal fade' id='myModaladdtag' role='dialog'>" + 
								"    <div class='modal-dialog'>" + 
								"      <div class='modal-content'>" + 
								"        <div class='modal-header'>" + 
								"          <button type='button' class='close' data-dismiss='modal'>&times;</button>" + 
								"          <h4 class='modal-title'><b style='color:#004892;'>ETÝKET EKLE</b></h4>" + 
								"        </div>" + 
								"        <div class='modal-body'>" + 
								"          <p>"
								+ "<textarea class='form-control etiketeklenecektext addtag' rows='4' style='resize:vertical;' placeholder='Bir etiket giriniz..'></textarea>"
								+ "</p>" + 
								"        </div>" + 
								"        <div class='modal-footer'>" + 
								"          <button type='button' class='btn btn-primary' data-dismiss='modal' onClick='addFunction(\"AddTag\",\"addtag\")'>KAYDET</button>" + 
								"          <button type='button' class='btn btn-default' data-dismiss='modal'>ÝPTAL</button>" + 
								"        </div>" + 
								"      </div>" +
								"     </div>" +
								"    </div>" +
								"  </div>"


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
