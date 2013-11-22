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
	<title>Reklam Yöneticisi Ana Ekranı</title>
  </head>
  <body id="body-gradient">
  <%
	String isim = (String) session.getAttribute("isim")+"";
	String gorev = (String) session.getAttribute("gorev")+"";
	
	Warnings warning = new Warnings();
	String yetkiID = (String)(session.getAttribute("yetkiID")+"");
	
	if ((isim == null) | (isim.equals(null+"")) | Integer.parseInt(yetkiID) != 2){
		session.setAttribute("basarisiz_islem", warning.getWarnings(13));
		response.sendRedirect("../index.jsp");
	}else{
  %>
	<p>
	<table>
	  <tr>
		<td style="width: 0.5cm;" />
		<td style="width: 35cm;">
		  <div class="navbar" id="div-gradient">
			<div class="navbar-inner">
			  <ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="#"><%=isim%></a></li>
				<li class="divider-vertical"></li>
				<li><a href="?SecilenIslem=ReklamEkle"><%=gorev%></a></li>
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
					String islem1 = null, islem2 = null;
	
					if ((islem == null) | (islem.equals(null+""))){
					  String sessionIslem = (String) session.getAttribute("secilenIslem")+"";
		
		 			  if (sessionIslem.equals(null+"") | sessionIslem.equals(null)){
						islem = "ReklamEkle";
					  }else{
						islem = sessionIslem;
					  }
	  				}
					session.setAttribute("secilenIslem", islem);
	
					if ((islem == null) | (islem.equals("ReklamEkle")) 
							| (islem.equals(null+""))){
					  islem1 = "active";
					}
					if (islem.equals("ReklamDuzenle_Reklamlar") 
							| islem.equals("ReklamDuzenle_ReklamIcerik")){	
					  islem2 = "active";
					}
					%>
				  <li class="<%=islem1%>">
				  	<a href="?SecilenIslem=ReklamEkle">Reklam Ekle</a>
				  </li>
				  <li class="<%=islem2%>">
				  	<a href="?SecilenIslem=ReklamDuzenle_Reklamlar">Reklam Düzenle</a>
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
			    /*	Oluşabilecek Hataların Belirtileceği Alan	*/
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
				if ((islem == null) | (islem.equals("ReklamEkle")) | (islem.equals(null+""))) {%> 
				  <jsp:include page="ReklamEkle.jsp" /> <%
				}
				if (islem.equals("ReklamDuzenle_Reklamlar")) {%>
				  <jsp:include page="ReklamDuzenle_Reklamlar.jsp" /> <%
				}
				if (islem.equals("ReklamDuzenle_ReklamIcerik")) {%>
				  <jsp:include page="ReklamDuzenle_ReklamIcerik.jsp" /> <%
				}
				if (islem.equals("SifreDegis")) {%>
				  <jsp:include page="../sifreDegis.jsp" /> <%
				}
			  %>
			  </td>
			</tr>
		  </table>
	    </div>
	  </div>
	</div>
	<p><%
	} %>
  </body>
</html>