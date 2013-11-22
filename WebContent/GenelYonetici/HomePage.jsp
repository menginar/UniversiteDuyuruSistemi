<%@page import="com.dCandan.DuyuruSistemi.ServletController.Warnings"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Genel Yönetici Ana Ekranı</title>
</head>
<body id="body-gradient">
<%
	String isim = (String) session.getAttribute("isim")+"";
	String gorev = (String) session.getAttribute("gorev")+"";
	
	String sistemdenCikar = session.getAttribute("sistemdenCikar")+"";
	session.removeAttribute("sistemdenCikar");
	
	Warnings warning = new Warnings();
	String yetkiID = (String)(session.getAttribute("yetkiID")+"");
	
	if (sistemdenCikar.equals("true") | ((isim == null) | (isim.equals(null+"")))| Integer.parseInt(yetkiID) != 4){
		if (sistemdenCikar.equals("true"))
			session.setAttribute("basarili_islem", warning.getWarnings(14));
		else
			session.setAttribute("basarisiz_islem", warning.getWarnings(13));
		response.sendRedirect("../index.jsp");		
	}
	else{
%>
	<p>
	<table>
		<tr>
			<td style="width: 0.5cm;" />
			<td style="width: 35cm;">
				<div class="navbar">
					<div class="navbar-inner">
						<ul class="nav">
							<li class="divider-vertical"></li>
							<li><a href="#"><%=isim%></a></li>
							<li class="divider-vertical"></li>
							<li><a href="?SecilenIslem=DuyuruIslemleri_Fakulteler1"><%=gorev%></a></li>
							<li class="divider-vertical"></li>
							<li><a href="../sifreDegis.jsp">Şifre Değiş</a></li>
							<li class="divider-vertical"></li>
							<li><a href="../closeSystem.jsp">Çıkış</a></li>
							<li class="divider-vertical"></li>
						</ul>
					</div>
				</div>
			</td>
			<td style="width: 0.5cm;" />
		</tr>
	</table>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2" id="div-gradient">
				<table>
					<tr>
						<td style="width: 6cm; height: 15cm;">
							<ul class="nav nav-list">
								<li class="nav-header" style="font-size: small;">YÖNETİCİ İŞLEMLERİ</li>
								<%
	/*	Sol Blok (İşlem Seçimi Yapılacak Alan)	*/
	String islem = request.getParameter("SecilenIslem")+"";
	String islem1 = null, islem2 = null, islem3 = null, islem4 = null, islem5 = null, islem6 = null;
	
	if ((islem == null) | (islem.equals(null+""))){
		/*	paginate kullanılarak sayfa değişiyorsa görüntülenen sayfa içerisinde sessiona eklenen
			secilenIslem parametresi ile hangi işlemin yapılacağını belirliyoruz.
		*/
		String sessionIslem = (String) session.getAttribute("secilenIslem")+"";
		
		if (sessionIslem.equals(null+"") | sessionIslem.equals(null)){
			islem = "DuyuruIslemleri_Fakulteler1";
		}
		else{
			islem = sessionIslem;
		}
	}
	session.setAttribute("secilenIslem", islem);
	
	if (islem.equals("DuyuruIslemleri_Fakulteler1") | islem.equals("DuyuruYayiniDuzenle")){
		islem1 = "active";
	}
	if (islem.equals("DuyuruIslemleri_Fakulteler2") | islem.equals("DuyuruBilgisiDuzenle") | islem.equals("DuyuruIcerik")){	
		islem2 = "active";
	}
	if (islem.equals("ReklamYayiniDuzenle")){	
		islem3 = "active";
	}
	if (islem.equals("ReklamBilgisiDuzenle") | islem.equals("ReklamIcerik")){	
		islem4 = "active";
	}
	if (islem.equals("KullaniciBilgisiDuzenle") | islem.equals("KullaniciBilgisi")){
		islem5 = "active";
	}
	if (islem.equals("KullaniciEkle")){
		islem6 = "active";
	}
	%>
		<li class="<%=islem1%>">
			<a href="?SecilenIslem=DuyuruIslemleri_Fakulteler1">Duyuru Yayını Duzenle</a>
		</li>
		<li class="<%=islem2%>">
			<a href="?SecilenIslem=DuyuruIslemleri_Fakulteler2">Duyuru Bilgisi Duzenle</a>
		</li>
		<li class="<%=islem3%>">
			<a href="?SecilenIslem=ReklamYayiniDuzenle">Reklam Yayını Duzenle</a>
		</li>
		<li class="<%=islem4%>">
			<a href="?SecilenIslem=ReklamBilgisiDuzenle">Reklam Bilgisi Duzenle</a>
		</li>
		<li class="<%=islem5%>">
			<a href="?SecilenIslem=KullaniciBilgisiDuzenle">Kullanıcı Bilgisi Duzenle</a>
		</li>
		<li class="<%=islem6%>">
			<a href="?SecilenIslem=KullaniciEkle">Yeni Kullanıcı Ekle</a>
		</li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
			<div class="span10" id="div-gradient">
				<table>
					<tr>
						<td style="width: 2cm;"/>
						<td style="height: 15cm;">
							<%
							/*	
							 	Oluşabilecek Hataların Belirtileceği Alan
							*/
							String malformed_data = (String) session.getAttribute("malformed_data");
							String basarili_islem = (String) session.getAttribute("basarili_islem");
							String basarisiz_islem = (String) session.getAttribute("basarisiz_islem");

							if (malformed_data != null){%>
								<div class="alert alert-error"><%=malformed_data %></div><p><%
								session.removeAttribute("malformed_data");
							}
							if (basarili_islem != null){%>
								<div class="alert alert-success"><%=basarili_islem %></div><p><%
								session.removeAttribute("basarili_islem");
							}
							if (basarisiz_islem != null){%>
								<div class="alert alert-error"><%=basarisiz_islem %></div><p><%
								session.removeAttribute("basarisiz_islem");
							}
							/*	İnclude İşleminin Yapıldığı Alan	*/
							
							if (islem.equals("DuyuruIslemleri_Fakulteler1") 
									| islem.equals("DuyuruIslemleri_Fakulteler2")){%>
								<jsp:include page="DuyuruIslemleri_Fakulteler.jsp"/><%
							}
							if (islem.equals("DuyuruYayiniDuzenle")){%>
								<jsp:include page="DuyuruYayiniDuzenle.jsp"/><%
							}
							if (islem.equals("DuyuruBilgisiDuzenle")){%>	
								<jsp:include page="DuyuruBilgisiDuzenle.jsp"/><%
							}
							if (islem.equals("ReklamYayiniDuzenle")){%>	
								<jsp:include page="ReklamYayiniDuzenle.jsp"/><%
							}
							if (islem.equals("ReklamBilgisiDuzenle")){%>
								<jsp:include page="ReklamBilgisiDuzenle.jsp"/><%
							}
							if (islem.equals("ReklamIcerik")){%>
								<jsp:include page="ReklamIcerik.jsp"/>	<%
							}
							if (islem.equals("DuyuruIcerik")){%>
								<jsp:include page="DuyuruIslemleri_DuyuruIcerik.jsp"/>	<%
							}
							if (islem.equals("KullaniciBilgisiDuzenle")){%>
								<jsp:include page="KullaniciBilgiIslemleri_Kullanicilar.jsp"/>	<%
							}
							if (islem.equals("KullaniciBilgisi")){%>
								<jsp:include page="KullaniciBilgiIslemleri_KullaniciBilgisi.jsp"/>	<%
							}
							if (islem.equals("KullaniciEkle")){%>
								<jsp:include page="KullaniciEkle.jsp"/>	<%
							}
							if (islem.equals("SifreDegis")){%>
								<jsp:include page="../sifreDegis.jsp"/>	<%
							}
							%>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<p>
	<%	} %>
	</body>
</html>