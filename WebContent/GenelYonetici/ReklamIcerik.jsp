<%@page import="com.dCandan.DuyuruSistemi.Model.treklam"%>
<%@page import="com.dCandan.DuyuruSistemi.DatabaseController.database_genelYoneticiController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	session.setAttribute("secilenIslem", "ReklamBilgisiDuzenle");
	
	database_genelYoneticiController controller = new database_genelYoneticiController();
	
	int reklamID = Integer.parseInt(session.getAttribute("reklamID")+"");
	ArrayList<treklam> ButunReklamlar = controller.ReklamListele();
	treklam Reklam = new treklam();
	
	for (treklam reklam : ButunReklamlar){
		if (reklam.getReklamID() == reklamID){
			Reklam = reklam;
			break;
		}
	}
	String filePath = request.getContextPath()+"/ReklamYoneticisi/ReklamResimleri/";
	String imagePath = filePath + Reklam.getReklamResimPath();
%>
<form class="form-horizontal" action="../servlet_genelYoneticiController" method="post">
		<input type="hidden" name="islem" value="ReklamIcerik">
		<input type="hidden" name="reklamID" value="<%=Reklam.getReklamID()%>">
		<fieldset>
			<legend>Reklam Bilgisi Düzenleme Paneli</legend>
			<div class="control-group">
				<label class="control-label">Reklam Resmi</label>
				<div class="controls">
					<img src="<%=imagePath %>" width="220">
					<input type="hidden" name="resimPath" value="<%=Reklam.getReklamResimPath()%>">
					<input type="hidden" name="username" value="<%=Reklam.getUsername()%>">
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
				<label class="control-label">Reklam İçeriği</label>
				<div class="controls">
					<textarea rows="4" cols="9" name="reklamIcerik"><%=Reklam.getReklamIcerik()%></textarea>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="submit" value="Tamamla" class="btn" style="width: 5.8cm;">
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>