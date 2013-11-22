<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body id="body-gradient">
<%
	String username = (String) session.getAttribute("username")+"";
	if (username.equals(null) | username.equals(null+"") | username.equals("")){
		response.sendRedirect("index.jsp");
	}
	else{
	session.setAttribute("secilenIslem", "SifreDegis");
%>
	<form class="form-horizontal" action="../servlet_userController" method="post">
	  <input type="hidden" name="islem" value="SifreDegis">
	  <fieldset>
	    <legend>Şifre Değişim Paneli</legend>
	    <div class="control-group">
	      <label class="control-label">Kullanıcı Adı</label>
		<div class="controls">
		  <input type="text" disabled="disabled" value="<%=session.getAttribute("username")%>">
		  <input type="hidden" name="username" value="<%=session.getAttribute("username")%>">
		</div>
		</div>
		<div class="control-group">
		  <label class="control-label">Eski Parola</label>
	      <div class="controls">
			<input type="text" name="last_password" placeholder="Eski Parola">
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label">Yeni Parola</label>
		  <div class="controls">
			<input type="password" name="password" placeholder="Yeni Parola">
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label">Yeni Parola Tekrar</label>
		  <div class="controls">
			<input type="password" name="password2" placeholder="Yeni Parola Tekrar">
		  </div>
		</div>
		<div class="control-group">
		  <div class="controls">
			<button type="submit" class="btn" style="width: 5.9cm;">Tamamla</button>
		  </div>
		</div>
	  </fieldset>
	</form>
<%
	if (session.getAttribute("gorev").equals("Fakulte Yoneticisi"))
		response.sendRedirect("FakulteYoneticisi/HomePage.jsp");
	if (session.getAttribute("gorev").equals("Genel Yonetici"))
		response.sendRedirect("GenelYonetici/HomePage.jsp");
	if (session.getAttribute("gorev").equals("Reklam Yoneticisi"))
		response.sendRedirect("ReklamYoneticisi/HomePage.jsp");
	}
%>
  </body>
</html>