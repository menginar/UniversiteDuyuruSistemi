<%@page import="com.dCandan.DuyuruSistemi.Model.treklam"%>
<%@page import="com.dCandan.DuyuruSistemi.DatabaseController.database_reklamYoneticisiController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
  <%
	session.setAttribute("secilenIslem", "ReklamDuzenle_ReklamIcerik");
	database_reklamYoneticisiController controller = new database_reklamYoneticisiController();
	
	int reklamID = Integer.parseInt(session.getAttribute("reklamID")+"");
	ArrayList<treklam> ButunReklamlar = controller.ReklamListele();
	treklam Reklam = new treklam();
	
	for (treklam reklam : ButunReklamlar){
	  if (reklam.getReklamID() == reklamID){
		Reklam = reklam;
		break;
	  }
	}
	String ProjePath = request.getContextPath()+"/ReklamYoneticisi/ReklamResimleri/";
	String KayitDizini = ProjePath+Reklam.getReklamResimPath();
  %>
	<form class="form-horizontal" action="../servlet_reklamYoneticisiController" method="post">
	  <input type="hidden" name="islem" value="ReklamDuzenle_ReklamIcerik">
	  <input type="hidden" name="reklamID" value="<%=Reklam.getReklamID()%>">
	  <fieldset>
		<legend>Reklam Düzenleme Paneli</legend>
		<div class="control-group">
		  <label class="control-label">Reklam Resim</label>
		  <div class="controls">
		    <img src="<%=KayitDizini%>" width="220">
			<input type="hidden" name="resimPath" value="<%=Reklam.getReklamResimPath()%>">
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label">Başlangıç Tarihi</label>
		  <div class="controls">
			<input type="date" name="baslangicTarih" value="<%=Reklam.getReklamBaslangicTarih()%>">
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label">Bitiş Tarihi</label>
		  <div class="controls">
			<input type="date" name="bitisTarih" value="<%=Reklam.getReklamBitisTarih()%>">
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label">Duyuru İçeriği</label>
		  <div class="controls">
			<textarea rows="4" cols="9" name="reklamIcerik"><%=Reklam.getReklamIcerik()%></textarea>
		  </div>
		</div>
		<div class="control-group">
		  <div class="controls">
			<input type="submit" value="Onaya Gönder" class="btn" style="width: 5.8cm;">
		  </div>
		</div>
	  </fieldset>
	</form>
  </body>
</html>