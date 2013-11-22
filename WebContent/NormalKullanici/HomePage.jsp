<%@page import="com.dCandan.DuyuruSistemi.ServletController.Warnings"%>
<%@page import="com.dCandan.DuyuruSistemi.Model.tduyuru_icerik"%>
<%@page import="com.dCandan.DuyuruSistemi.Model.tduyuru_bilgi"%>
<%@page import="com.dCandan.DuyuruSistemi.DatabaseController.database_normalKullaniciController"%>
<%@page import="com.dCandan.DuyuruSistemi.Model.treklam"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../styleFile/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="../styleFile/css/bootstrap-responsive.css" rel="stylesheet"
	media="screen">
<link href="../styleFile/css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
<script src="../styleFile/js/bootstrap.min.js"></script>
<script src="../styleFile/js/bootstrap.js"></script>
<title>Normal Kullanıcı Ana Ekranı</title>
</head>
<body id="body-gradient">
	<%
	response.setIntHeader("Refresh", 3600);
	
		String isim = (String) session.getAttribute("isim") + "";
		String gorevliOlunanFakulteID = session.getAttribute("gorevID")+ "";
		ArrayList<treklam> Reklamlar = null;
		Warnings warning = new Warnings();
		String yetkiID = (String)(session.getAttribute("yetkiID")+"");
		
		if ((isim == null) | (isim.equals(null+"")) | Integer.parseInt(yetkiID) != 1){
			session.setAttribute("basarisiz_islem", warning.getWarnings(13));
			response.sendRedirect("../index.jsp");
		} else {
			database_normalKullaniciController controller = new database_normalKullaniciController();
			ArrayList<tduyuru_bilgi> ButunDuyuruBilgiler = controller.DuyuruBilgiListele();
			ArrayList<tduyuru_icerik> ButunDuyuruIcerikler = controller.DuyuruIcerikListele();
			Reklamlar = controller.ReklamListele();
			
			ArrayList<tduyuru_bilgi> ListelenecekDuyuruBilgiler = new ArrayList<tduyuru_bilgi>();

			for (tduyuru_bilgi DuyuruBilgi : ButunDuyuruBilgiler) {
				if (DuyuruBilgi.getFakulteID() == Integer.parseInt(gorevliOlunanFakulteID)){
					if (DuyuruBilgi.getDuyuruTurID() == 1)
						ListelenecekDuyuruBilgiler.add(DuyuruBilgi);
				}
			}
			int ilkGosterilecekDuyuruAdet = 10;
			String fakulteAd = controller.IsimGetir("tfakulte",gorevliOlunanFakulteID);
	%>
	<p>
	<table>
		<tr>
			<td style="width: 0.5cm;" />
			<td style="width: 38cm;">
				<div class="navbar">
					<div class="navbar-inner">
						<ul class="nav">
							<li class="divider-vertical"></li>
							<li><a><%=fakulteAd%></a></li>
							<li class="divider-vertical"></li>
							<li><a href="../closeSystem.jsp">Çıkış</a></li>
							<li class="divider-vertical"></li>
						</ul>
					</div>
				</div>
			</td>
			<td style="width: 0.5cm;"/>
		</tr>
	</table>
	<table>
		<tr>
			<td style="width: 1.5cm;"/>
			<td bgcolor="white">
				<marquee style="width: 33cm; height: 11cm;" scrollamount="1" direction="up" scrolldelay="1">
			<%
			int duyuruID = 0, duyuruTurID = 0, bolumID = 0;
			String duyuruBaslik = "", duyuruIcerik = "", baslangicTarih = "", bitisTarih = "";
			if (ListelenecekDuyuruBilgiler.size() > 0) {
				for (tduyuru_bilgi DuyuruBilgi : ListelenecekDuyuruBilgiler) {
					duyuruTurID = DuyuruBilgi.getDuyuruTurID();
					if (duyuruTurID == 1) {
						duyuruID = DuyuruBilgi.getDuyuruID();
						bolumID = DuyuruBilgi.getBolumID();
						for (tduyuru_icerik DuyuruIcerik : ButunDuyuruIcerikler) {
							if (DuyuruIcerik.getDuyuruID() == duyuruID) {
								duyuruBaslik = DuyuruIcerik.getDuyuruBaslik();
								duyuruIcerik = DuyuruIcerik.getDuyuruIcerik();
								baslangicTarih = DuyuruIcerik.getDuyuruBaslangicTarih();
								bitisTarih = DuyuruIcerik.getDuyuruBitisTarih();
								break;
							}
						}
						ilkGosterilecekDuyuruAdet--;
					}
					%>
					<table>
					<tr>
						<td style="width: 0.5cm;"/>
						<td>
							<h4 style="color: #800517;">
								<strong><%=duyuruID+" - "+duyuruBaslik%></strong>
							</h4>
							<p><p><%=duyuruIcerik%><p><p>
							<font color="#800517">
								<%=controller.IsimGetir("tbolum", bolumID+"") %> Bölümü Için 
								<%=bitisTarih%> Tarihine Kadar Geçerli Duyuru
							</font>
						</td>
					</tr>
				</table>
					<hr>
					<%
						}
					}
				%>
				</marquee>
			</td>
			<td style="width: 1.5cm;"/>
		</tr>
	</table>
	<%	}%>
	<hr>
		<table style="text-align: center;">
		<tr>
			<td style="width: 1.5cm;"/>
			<%
				for (treklam Reklam : Reklamlar){
					if (Reklam.getYayinlanmaDurum() == 1){
						String filePath = request.getContextPath()+"/ReklamYoneticisi/ReklamResimleri/";
						String imagePath = filePath + Reklam.getReklamResimPath();
					%>
					<td>
						<img src="<%=imagePath %>" style="width: 3cm; height: 2.5cm;">
					</td>
					<td style="width: 4cm;"><%=Reklam.getReklamIcerik() %></td>
					<td style="width: 1cm;"/>
					<%
					}
				}%>
			<td style="width: 1.5cm;"/>
		</tr>
	</table>
</body>
</html>
