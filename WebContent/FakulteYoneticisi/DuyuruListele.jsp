<%@page import="com.dCandan.DuyuruSistemi.Model.tduyuru_icerik"%>
<%@page import="com.dCandan.DuyuruSistemi.Model.tduyuru_bilgi"%>
<%@page import="com.dCandan.DuyuruSistemi.DatabaseController.database_fakulteYoneticisiController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	session.setAttribute("secilenIslem", "DuyuruListele");

	database_fakulteYoneticisiController controller 
								= new database_fakulteYoneticisiController();
	
	ArrayList<tduyuru_bilgi> ButunDuyuruBilgiler = controller
														.DuyuruBilgiListele();
	ArrayList<tduyuru_icerik> ButunDuyuruIcerikler = controller
														.DuyuruIcerikListele();
	ArrayList<tduyuru_bilgi> ListelenecekDuyuruBilgiler 
											= new ArrayList<tduyuru_bilgi>();
	//	FakulteYöneticisi için buraya bulundukları fakültenin numarası yazılacak.
	int userGorevID = Integer
							.parseInt(session.getAttribute("gorevID")+"");
	for (tduyuru_bilgi DuyuruBilgi : ButunDuyuruBilgiler){
		if (((DuyuruBilgi.getDuyuruTurID() == 1) | (DuyuruBilgi.getDuyuruTurID() == 3)) 
				&& (DuyuruBilgi.getFakulteID() == userGorevID)){
			ListelenecekDuyuruBilgiler.add(DuyuruBilgi);
		}
	}
	int duyuruAdet = ListelenecekDuyuruBilgiler.size();
	int sayfaBasinaDuyuruAdet = 8;
	int sayfaAdet = (duyuruAdet / sayfaBasinaDuyuruAdet);
	if ((duyuruAdet % sayfaBasinaDuyuruAdet) > 0)
		sayfaAdet++;
	String pageNum = request.getParameter("page")+"";
	int ilkSatir = 0;
	if (pageNum.equals(null) | pageNum.equals(null+"") | pageNum.equals("0"))
		ilkSatir = 0;
	if (pageNum.equals("1"))
		ilkSatir = 8;
	if (pageNum.equals("2"))
		ilkSatir = 16;
	if (pageNum.equals("3"))
		ilkSatir = 24;
	if (pageNum.equals("4"))
		ilkSatir = 32;
	if (pageNum.equals("5"))
		ilkSatir = 40;
	int satirNo = ilkSatir;
	String ozelDuyuru = null;
%>
	<form class="form-horizontal" action="../servlet_fakulteYoneticisiController" 
		method="post">
		<fieldset>
			<legend>Duyuru Listeleme Paneli</legend>
			<div class="control-group" style="height: auto;">
				<table class="table table-bordered" style="font-size: small;">
					<tr>
						<th>Duyuru No</th>
						<th>Duyuru Fakültesi</th>
						<th>Duyuru Bölümü</th>
						<th>Duyuru Başlık</th>
						<th>Duyuru Başlangıç</th>
						<th>Duyuru Bitiş</th>
					</tr><%
	if (ListelenecekDuyuruBilgiler.size() > 0){
		for (; satirNo < ListelenecekDuyuruBilgiler.size(); satirNo++){
			tduyuru_bilgi DuyuruBilgi = ListelenecekDuyuruBilgiler.get(satirNo);
			tduyuru_icerik DuyuruIcerik = null;
			for (tduyuru_icerik Icerik : ButunDuyuruIcerikler){
				if (DuyuruBilgi.getDuyuruID() == Icerik.getDuyuruID()){
					DuyuruIcerik = Icerik;
					sayfaBasinaDuyuruAdet--;
					break;
				}
			}
			String fakulteAd = controller
						.IsimGetir("tfakulte", DuyuruBilgi.getFakulteID()+"");
			String bolumAd = controller
						.IsimGetir("tbolum", DuyuruBilgi.getBolumID()+"") ;
			if (DuyuruBilgi.getDuyuruTurID() == 3){
				ozelDuyuru = "bold";
			}%>
					<tr style="font-weight: <%=ozelDuyuru%>; ">	
						<td><%=DuyuruBilgi.getDuyuruID()%></td>
						<td><%=fakulteAd %></td>
						<td><%=bolumAd%></td>
						<td>
							<a title="<%=DuyuruIcerik.getDuyuruIcerik()%>">
								<%=DuyuruIcerik.getDuyuruBaslik() %>
							</a>
						</td>
						<td><%=DuyuruIcerik.getDuyuruBaslangicTarih() %></td>
						<td><%=DuyuruIcerik.getDuyuruBitisTarih() %></td>
					</tr><%
			if (sayfaBasinaDuyuruAdet == 0)
				break;
			ozelDuyuru = null;
		}
	}else{%>
					<tr>
						<td>Kayıt Yok</td>
						<td>Kayıt Yok</td>
						<td>Kayıt Yok</td>
						<td>Kayıt Yok</td>
						<td>Kayıt Yok</td>
						<td>Kayıt Yok</td>
					</tr><%
	}%>
				</table>
			</div>
		</fieldset>
	</form>
	<div class="pagination pagination-centered">
		<ul><%
		if (sayfaAdet > 1){
			for (int i = 0; i < sayfaAdet; i++) {%>
				<li><a href="?page=<%=i%>"><%=i+1 %></a></li><%
			}
		}%>
		</ul>
	</div>
</body>
</html>