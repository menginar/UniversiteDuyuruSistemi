<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<%
		session.setAttribute("secilenIslem", "ReklamEkle");
		String resimPath = session.getAttribute("resimPath") + "";
		session.removeAttribute("resimPath");

		if (resimPath.equals(null) | resimPath.equals(null + "")
				| resimPath.equals(""))
			resimPath = "Resim Secilmedi";
	%>
	<form class="form-horizontal" action="../servlet_reklamYoneticisiController" method="post"
		enctype="multipart/form-data">
		<fieldset>
			<legend>Reklam Ekleme Paneli</legend>
			<div class="control-group">
				<label class="control-label">Reklam Resmi Seç</label>
				<div class="controls">
					<input type="file" name="resimPath" id="resimPath" style="width: 6cm;">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Başlangıç Tarihi</label>
				<div class="controls">
					<input type="date" name="baslangicTarih" placeholder="Başlangıç Tarihi">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Bitiş Tarihi</label>
				<div class="controls">
					<input type="date" name="bitisTarih" placeholder="Bitiş Tarihi">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Reklam İçeriği</label>
				<div class="controls">
					<textarea rows="4" cols="7" name="reklamIcerik"></textarea>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="submit" value="Reklamı Ekle" class="btn" style="width: 5.8cm;">
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>