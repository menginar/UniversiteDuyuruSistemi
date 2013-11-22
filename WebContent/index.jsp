<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styleFile/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="styleFile/css/bootstrap-responsive.css" rel="stylesheet"
	media="screen">
<link href="styleFile/css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
<script src="styleFile/js/bootstrap.min.js"></script>
<script src="styleFile/js/bootstrap.js"></script>
<style type="text/css" media="all">
#main {
	padding-top: 60px;
}
</style>
<title>Ana Ekran</title>
</head>
<%
	String username = (String) session.getAttribute("username") + "";
	if (username.equals(null) | username.equals(null + "")
			| username.equals("")) {

	} else {
		String yetkiID = session.getAttribute("yetkiID") + "";
		switch (Integer.parseInt(yetkiID)) {
		/*
		 * 	isUser metodu araciligiyla kullanicinin yetkiID'sini belirlemistik.
		 * 	Bu yetkiID'sine gore yonlendirme yapilan blok asagidadir.Herkez 
		 * 	kendini ilgilendiren sayfalardan baskasini goremez.
		 */
		case 1://	Normal Kullanici
			response.sendRedirect("NormalKullanici/HomePage.jsp");
			break;
		case 2://	Reklam Yoneticisi
			response.sendRedirect("ReklamYoneticisi/HomePage.jsp");
			break;
		case 3://	Fakulte Yoneticisi
			response.sendRedirect("FakulteYoneticisi/HomePage.jsp");
			break;
		case 4://	Genel Yonetici
			response.sendRedirect("GenelYonetici/HomePage.jsp");
			break;
		}
	}

	String userAgent = request.getHeader("User-Agent");
%>
<body id="body-gradient">
	<div class="container main">
		<%
			if (!userAgent.contains("Mobile")) {
		%>
		<br> <br> <br> <br> <br> <br> <br>
		<br>
		<%
			}
		%>
		<div id="main">
			<div class="span4 offset3">
				<ul class="thumbnails">
					<li>
						<div class="span5">
							<fieldset>
								<legend>Giriş</legend>
								<form class="form-horizontal" action="servlet_userController"
									method="post">
									<input type="hidden" name="islem" value="login">
									<!-- Hata Gösterilecek Ekran... -->
									<%
										String malformed_data = (String) session.getAttribute("basarisiz_islem");
										String basarili_islem = (String) session.getAttribute("basarili_islem");

										if (malformed_data != null) {
									%>
									<div class="alert alert-error"><%=malformed_data %></div>
									<%
										session.removeAttribute("basarisiz_islem");
										}
										if (basarili_islem != null) {
									%>
									<div class="alert alert-success"><%=basarili_islem%></div>
									<%
										session.removeAttribute("basarili_islem");
										}
									%>
									<div class="control-group">
										<label class="control-label">Kullanıcı Adı</label>
										<div class="controls">
											<input type="text" name="username"
												placeholder="Kullanıcı Adı">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Parola</label>
										<div class="controls">
											<input type="password" name="password" placeholder="Parola">
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn" style="width: 5.9cm;">Tamamla</button>
										</div>
									</div>
								</form>
							</fieldset>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>