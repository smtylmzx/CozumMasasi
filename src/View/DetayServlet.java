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
import Controller.RemarksController;
import Controller.SolutionsController;
import Controller.TagsController;
import Entity.Problem;
import Entity.Remark;
import Entity.Solution;
import Entity.Tag;

/**
 * Servlet implementation class detayServlet
 */
@WebServlet("/detayServlet")
public class DetayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, EEEE");
        String currentDate = sdf.format(new Date());
	
		PrintWriter out = response.getWriter();
		ProblemsController pc = new ProblemsController();
		SolutionsController sc = new SolutionsController();
		RemarksController rc = new RemarksController();
		TagsController tc = new TagsController();

		ArrayList<Problem> result = pc.readAll();
		HashMap<String,Integer> resultcounterror = pc.statisticErrors();
		
		int id = Integer.parseInt(request.getParameter("id"));

		ArrayList<Solution> resultsolutions=sc.readByID(id);
		ArrayList<Remark> resultremarks = rc.readByProbID(id);
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
				
				+	"function updateFunction(updateItem,updateNew,updateNewId,eskiveri,dznlbtngr) {"
				+	"var ekrandanGelenNew = $('.' + updateNew).val();"
				+	"var ekrandanGelenId = updateNewId;"

				+ "if (ekrandanGelenNew == '' ){"
				+"alert('HATA!! BOÞ DEÐER EKLENEMEZ.');}else{"
				
				+	"$.post(updateItem , {"
				 		+	"id: ekrandanGelenId,"
				 		+	"newVal: ekrandanGelenNew"
				 		+	"},"
				 		+	"function(result){"
				 			+ 	"$('.' + eskiveri).html(ekrandanGelenNew);"
							+	"$('.' + eskiveri).toggle();"
							+	"$('.' + updateNew).hide();"
							+	"$('.' + dznlbtngr).toggle();"
//				 			+	"alert('Kayýt Güncellendi.');"
							
				 			+	"$('.basarili').css({'display': 'block'}).delay(2000).slideUp();"
				 		
				 		+	"},"
				 +	")}};"
				 		
				
				+	"function addFunction(addNewCat,addNewContent,ContentId) {"
				+	"var ekrandanGelenNew = $('.' + addNewContent).val();"
				+	"var ekrandanGelenId = ContentId;"
				
				+ "if (ekrandanGelenNew == '' ){"
				+"alert('HATA!! BOÞ DEÐER EKLENEMEZ.');}else{"
				
				+	"$.post(addNewCat , {"
			 		+	"id: ekrandanGelenId,"
			 		+	"newVal: ekrandanGelenNew,"
				 		+	"},"
				 		+	"function(result){"
//				 			+	"alert('Kayýt Eklendi.');"
				 		
				 			+	"$('.basarili').css({'display': 'block'}).delay(2000).slideUp();"
				 			+	"setTimeout(function(){"
				 			+	"location.reload();},2500);"
				 			
				 			+	"},"
				 +	")}};"
				
				 
				+	"function deleteFunction(deleteItem,deleteId) {"
				+	"var ekrandanGelenId = deleteId;"
				+	"if(deleteItem=='DeleteProblem'){ tanim='PROBLEMÝ'; }else if(deleteItem=='DeleteSolution'){ tanim='ÇÖZÜMÜ'; }else{ tanim='NOTU'; }"
				+	"if (confirm(tanim + ' SÝLMEK ÜZERESÝNÝZ!!')) {"
				
				+	"$.post(deleteItem , {"
				 		+	"id: ekrandanGelenId,"
				 		+	"},"
				 		+	"function(result){"
//			 				+	"alert('Kayýt Silindi.');"
				 		
				 			+	"$('.basarili').css({'display': 'block'}).delay(2000).slideUp();"
				 			+	"setTimeout(function(){"
				 			+	"if(deleteItem=='DeleteProblem') { window.location.assign(\"index.html\"); } else { location.reload(); }},2500);"
				 			
				 			+	"},"
				 		+	")"
				 + "}else{ alert('ÝPTAL EDÝLDÝ..'); }"
				 +"};"	
					
				 
					+ "$(document).ready(function(){"
					+ "$('textarea').hide();"
						+ "$('.addSolTextArea').toggle();"
						+ "$('.addRemTextArea').toggle();"
						+ "$('button.remdznlbtn').hide();"
						+ "$('button.soldznlbtn').hide();"
						+ "$('button.textdznlbtn').hide();"
						
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
		
		
		
		
		out.print(
				" <script type='text/javascript'>" + 
				"  $().ready(function() {" + 
				"   $('#add').click(function() {" +
				"	var optiondangelenval=document.getElementById(\"selectoptionitemadd\");" +
				"	var gelendeger=optiondangelenval.options[optiondangelenval.selectedIndex].value;" + 
				"	ekle(gelendeger,"+ id +");" +
				"    return !$('#selectoptionitemadd option:selected').remove().appendTo('#selectoptionitemdelete');" + 
				"   });" + 
				"   $('#remove').click(function() {" +
				"	var optiondangelenval2=document.getElementById(\"selectoptionitemdelete\");" +
				"	var gelendeger2=optiondangelenval2.options[optiondangelenval2.selectedIndex].value;" + 
				"	sil(gelendeger2,"+ id +");" +				
				"    return !$('#selectoptionitemdelete option:selected').remove().appendTo('#selectoptionitemadd');" + 
				"   });" + 
				"  }); " + 
				" "
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
				+ "</script>  ");
		
		
		
		out.println("</head><body><div class='basarili' style='display:none; position:fixed; z-index:1; width:100%; height:50px; background-color:#178217; color:#FFFFFF; text-align:center; padding-top:10px; font-size:25px;'><b><i class='fa fa-check' aria-hidden='true'></i> BAÞARILI</b></div>");
		out.println("<div style='height:10px; width:100%; background-color:#004892;'></div>");
		out.println("<div id='messagePane' style='height:100px; width:100%; background-color:green;display:none;'>Kaydedildi!</div>");
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
						
					+ "<p style='height:20px;'></p>");
					
					out.print("<div class='row'>"
				+	"<div class='col-md-3'>"
						+ "<div class='row'>"
						+ "<div class='col-md-12'>"
							+ "<div class='panel panel-default'>"
							+ "<div class='panel-heading' style='background-color:#004892; color:#FFFFFF; text-align:center;'><b style='font-size:20px;'>ÝÞLEMLER</b></div>"

								+ "<div class='panel-body'>"
								+ "<button class='btn form-control input-lg' type='button' style='font-weight:bold; font-size:15px; font-family:system-ui; margin-top:10px; border-style:solid; border-width:1px; border-color:#004892;' data-toggle='modal' data-target='#myModalsol'><i class='fa fa-plus-square-o' aria-hidden='true'></i> ÇÖZÜM EKLE </button>"
								+ "<button class='btn form-control input-lg' type='button' style='font-weight:bold; font-size:15px; font-family:system-ui; margin-top:10px; border-style:solid; border-width:1px; border-color:#004892;' data-toggle='modal' data-target='#myModalrem'><i class='fa fa-file-text-o' aria-hidden='true'></i> NOT EKLE </button>"
								+ "<button class='btn form-control input-lg' type='button' style='font-weight:bold; font-size:15px; font-family:system-ui; margin-top:10px; border-style:solid; border-width:1px; border-color:#004892;' data-toggle='modal' data-target='#myModaltag'><i class='fa fa-hashtag' aria-hidden='true'></i> ETÝKET DÜZENLE </button>");

						out.print("</div>"
							+ "</div>"
						+ "</div>"
					+ "</div>"
						
						+ "<div class='row'>"
						+ "<div class='col-md-12'>"
							+ "<div class='panel panel-default'>"
								+ "<div class='panel-body'>"
								+ "<b>Etiketler: </b> ");
								if(resulttagID.size()==0) { out.print("<i>Etiket Bulunamadý.</i>");
								}else {
								for (int i = 0; i < resulttagID.size(); i++) {
								out.print("<a href='TagSearch?tagid=" + resulttagID.get(i).getTagID() + "'>#" + resulttagID.get(i).getTag() + "</a> ");
								}
								}
						out.print("</div>"
							+ "</div>"
						+ "</div>"
					+ "</div>"	
				
				+ "</div>");
					
					out.print("<div class='col-md-9'>"
					
										+ "<div class='row'>"
											+ "<div class='col-md-12'>"
												+ "<div class='panel panel-warning' style='background-color:#FCF8E3;'>"
												+ "<div class='panel-body'>"
													+ "<div class='row'>"
														+ "<div class='col-md-1' style='text-align:right;'>"
															+ "<i class='fa fa-exclamation-triangle fa-2x' aria-hidden='true' style='color:#D61515;'></i>"
														+ "</div>"
													
														+ "<div class='col-md-9'>"
															+ "<font style='font-size:15px; font-weight:bold;' class='texto'> ");
														out.println(pc.readByID(id).getProblem());
														out.print("</div></font>");
														out.print("<div style='text-align:right;' class='col-md-2 textf'>"
																+ "<i class='fa fa-pencil-square fa-2x' aria-hidden='true' style='color:#D61515;' onClick='$.ortamgoster.duzenle(\"textn\",\"texto\", \"textf\")'></i>"
																+ "&nbsp;&nbsp;"
																+ "<i class='fa fa-times fa-2x' aria-hidden='true' style='color:#D61515;' onClick='deleteFunction(\"DeleteProblem\",\"" + pc.readByID(id).getProbID() + "\")'></i>"
													+	"</div>"
													+	"<div class='col-md-9 gizlialan' style='visibility:hidden;'>"
															+ "<textarea name='newProblem' class='form-control textn' rows='2' style='resize:vertical;'>" + pc.readByID(id).getProblem() + "</textarea>"
													+	"</div>"
													+	"<div class='col-md-2 btn-group gizlialan' style='visibility:hidden;'>"
														+	"<button type='submit' class='btn btn-success btn-sm  textn textdznlbtn' name='textdznlbtn' value='KYDT' style='height:54px;' onClick='updateFunction(\"updateProblem\",\"textn\",\""+ pc.readByID(id).getProbID() + "\", \"texto\", \"textf\")'>KYDT</button>"
														+	"<button type='submit' class='btn btn-danger btn-sm  textn textdznlbtn' name='textdznlbtn' value='ÝPTL' style='height:54px;' onClick='$.ortamiptal.iptal(\"textn\",\"texto\", \"textf\")'>ÝPTL</button>"
													+	"</div>");
											out.print("</div>"
												+	"</div>"
											+	"</div>"
										+	"</div>"
									+	"</div>");
						
						for (int i = 0; i < resultsolutions.size(); i++) { 		
			out.println("<div class='row'>"
						+ "<div class='col-md-12'>"
							+ "<div class='panel panel-success' style='background-color:#e0ffdd;'>"
							+ "<div class='panel-body'>"
								+ "<div class='row'>"
									+ "<div class='col-md-1' style='text-align:right;'>"
										+ "<i class='fa fa-check fa-2x kaydedildi' aria-hidden='true' style='color:#178217;'></i>"
									+ "</div>"
								
									+ "<div class='col-md-9'>"
										+ "<font style='font-size:15px;' class='solo"+ i +"'> ");
										out.println(resultsolutions.get(i).getSolution());
									out.print("</div></font>");
									out.print("<div style='text-align:right;' class='col-md-2 solf"+ i +"'>"
											+ "<i class='fa fa-pencil-square fa-2x' aria-hidden='true' style='color:#178217;' onClick='$.ortamgoster.duzenle(\"soln" + i + "\",\"solo" + i + "\", \"solf"+ i +"\")'></i>"
													+ "&nbsp;&nbsp;"
													+ "<i class='fa fa-times fa-2x' aria-hidden='true' style='color:#178217;' onClick='deleteFunction(\"DeleteSolution\",\"" + resultsolutions.get(i).getSolutionsID() + "\")'></i>"
								+	"</div>"
								+	"<div class='col-md-9 gizlialan' style='visibility:hidden;'>"
										+ "<textarea name='newSolution' class='form-control soln"+ i +"' rows='2' style='resize:vertical;'>" + resultsolutions.get(i).getSolution() + "</textarea>"
								+	"</div>"
								+	"<div class='col-md-2 btn-group gizlialan' style='visibility:hidden;'>"
									+	"<button type='submit' class='btn btn-success btn-sm  soln"+ i +" soldznlbtn' name='soldznlbtn' value='KYDT' style='height:54px;' onClick='updateFunction(\"updateSolution\",\"soln"+ i +"\",\""+ resultsolutions.get(i).getSolutionsID() + "\", \"solo"+ i +"\", \"solf"+ i +"\")'>KYDT</button>"
									+	"<button type='submit' class='btn btn-danger btn-sm  soln"+ i +" soldznlbtn' name='soldznlbtn' value='ÝPTL' style='height:54px;' onClick='$.ortamiptal.iptal(\"soln" + i + "\",\"solo" + i + "\", \"solf"+ i +"\")'>ÝPTL</button>"
								+	"</div>");
						out.print("</div>"
							+	"</div>"
						+	"</div>"
					+	"</div>"
				+	"</div>");
						}
						
						for (int j = 0; j < resultremarks.size(); j++) { 		
			out.println("<div class='row'>"
						+ "<div class='col-md-12'>"
							+ "<div class='panel panel-warning' style='background-color:#fffbdb;'>"
							+ "<div class='panel-body'>"
								+ "<div class='row'>"
									+ "<div class='col-md-1' style='text-align:right;'>"
										+ "<i class='fa fa-pencil fa-2x' aria-hidden='true' style='color:#827917;'></i>"
									+ "</div>"
								
									+ "<div class='col-md-9'>"
										+ "<font style='font-size:15px;' class='remo"+ j +"'> ");
										out.println(resultremarks.get(j).getRemark());
									out.print("</div></font>");
									out.print("<div style='text-align:right;' class='col-md-2 remf"+ j +"'>"
											+ "<i class='fa fa-pencil-square fa-2x' aria-hidden='true' style='color:#827917;' onClick='$.ortamgoster.duzenle(\"remn" + j + "\",\"remo" + j + "\", \"remf"+ j +"\")'></i>"
													+ "&nbsp;&nbsp;"
													+ "<i class='fa fa-times fa-2x' aria-hidden='true' style='color:#827917;' onClick='deleteFunction(\"DeleteRemark\",\"" + resultremarks.get(j).getRemarkID() + "\")'></i>"
								+	"</div>"
								+	"<div class='col-md-9 gizlialan' style='visibility:hidden;'>"
										+ "<textarea name='newRemark' class='form-control remn"+ j +"' rows='2' style='resize:vertical;'>" + resultremarks.get(j).getRemark() + "</textarea>"
								+	"</div>"
								+	"<div class='col-md-2 btn-group gizlialan' style='visibility:hidden;'>"
									+	"<button type='submit' class='btn btn-success btn-sm  remn"+ j +" remdznlbtn' name='remdznlbtn' value='KYDT' style='height:54px;' onClick='updateFunction(\"updateRemark\",\"remn"+ j +"\",\""+ resultremarks.get(j).getRemarkID() + "\", \"remo"+ j +"\", \"remf"+ j +"\")'>KYDT</button>"
									+	"<button type='submit' class='btn btn-danger btn-sm  remn"+ j +" remdznlbtn' name='remdznlbtn' value='ÝPTL' style='height:54px;' onClick='$.ortamiptal.iptal(\"remn" + j + "\",\"remo" + j + "\", \"remf"+ j +"\")'>ÝPTL</button>"
								+	"</div>");
						out.print("</div>"
							+	"</div>"
						+	"</div>"
					+	"</div>"
				+	"</div>");
						}
						
						out.print("</div>");
						out.print("</div>");
						

						out.print("</div>");


						
						out.print("  <div class='modal fade' id='myModalsol' role='dialog'>" + 
								"    <div class='modal-dialog'>" + 
								"      <div class='modal-content'>" + 
								"        <div class='modal-header'>" + 
								"          <button type='button' class='close' data-dismiss='modal'>&times;</button>" + 
								"          <h4 class='modal-title'><b style='color:#004892;'>ÇÖZÜM EKLE</b></h4>" + 
								"        </div>" + 
								"        <div class='modal-body'>" + 
								"          <p>"
								+ "<textarea name='addSol' class='form-control addSolTextArea' rows='4' style='resize:vertical;' placeholder='Bir çözüm giriniz..'></textarea>"
								+ "</p>" + 
								"        </div>" + 
								"        <div class='modal-footer'>" + 
								"          <button type='button' class='btn btn-primary' data-dismiss='modal' onClick='addFunction(\"AddSolution\",\"addSolTextArea\",\"" + id + "\")'>KAYDET</button>" + 
								"          <button type='button' class='btn btn-default' data-dismiss='modal'>ÝPTAL</button>" + 
								"        </div>" + 
								"      </div>" + 
								"    </div>" + 
								"  </div>");
						
						out.print("  <div class='modal fade' id='myModalrem' role='dialog'>" + 
								"    <div class='modal-dialog'>" + 
								"      <div class='modal-content'>" + 
								"        <div class='modal-header'>" + 
								"          <button type='button' class='close' data-dismiss='modal'>&times;</button>" + 
								"          <h4 class='modal-title'><b style='color:#004892;'>NOT EKLE</b></h4>" + 
								"        </div>" + 
								"        <div class='modal-body'>" + 
								"          <p>"
								+ "<textarea name='addRem' class='form-control addRemTextArea' rows='4' style='resize:vertical;' placeholder='Bir not giriniz..'></textarea>"
								+ "</p>" + 
								"        </div>" + 
								"        <div class='modal-footer'>" + 
								"          <button type='button' class='btn btn-primary' data-dismiss='modal' onClick='addFunction(\"AddRemark\",\"addRemTextArea\",\"" + id + "\")'>KAYDET</button>" + 
								"          <button type='button' class='btn btn-default' data-dismiss='modal'>ÝPTAL</button>" + 
								"        </div>" + 
								"      </div>" + 
								"    </div>" + 
								"  </div>");
						
						
						
						out.print("  <div class='modal fade' id='myModaltag' role='dialog'>" + 
								"    <div class='modal-dialog'>" + 
								"      <div class='modal-content'>" + 
								"        <div class='modal-header'>" + 
								"          <button type='button' class='close' data-dismiss='modal'>&times;</button>" + 
								"          <h4 class='modal-title'><b style='color:#004892;'>ETÝKET DÜZENLE</b></h4>" + 
								"        </div>" + 
								"        <div class='modal-body'>" + 
								"          <p>" +

								"<div class='row'>" + 
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
								+ "</div>" +
								
								"		</p>" + 
								"        </div>" + 
								"        <div class='modal-footer'>" + 
								"          <button type='button' class='btn btn-primary' data-dismiss='modal' onClick='location.reload();'>KAYDET</button>" + 
//								"          <button type='button' class='btn btn-default' data-dismiss='modal'>ÝPTAL</button>" + 
								"        </div>" + 
								"      </div>" + 
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
