<%@page import="com.dCandan.DuyuruSistemi.Model.tfakulte"%>
<%@page import="com.dCandan.DuyuruSistemi.DatabaseController.database_fakulteYoneticisiController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
													pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function autoSubmit(str) {
		document.getElementById("islem").value = str;
		document.getElementById("form").submit();
	}
</script>
</head>
<body>
<form id="form">
	<input type="hidden" name="islem" id="islem">
</form>

	<%
	
		session.setAttribute("secilenIslem", "KullaniciEkle");
		database_fakulteYoneticisiController controller = new database_fakulteYoneticisiController();
		ArrayList<tfakulte> fakulteler = controller.FakulteListele();
		String islem = request.getParameter("islem")+"";
		
		if (islem.equals(null+"") | islem == null | islem.equals(null)){
			islem = "NormalKullanici";
	%>
	<table>
	<tr>
		<td style="width: 6cm;"></td>
	</tr>
	<tr>
		<td></td>
		<td>
			<table class="table table-bordered" style="font-size: small;">
				<tr>
					<td><input type="radio" name="islem" value="FakulteYoneticisi" onclick="autoSubmit(this.value)"></td>
					<td>Fakülte Yöneticisi Ekle</td>
					<td><input type="radio" name="islem" value="ReklamYoneticisi" onclick="autoSubmit(this.value)"></td>
					<td>Reklam Yöneticisi Ekle</td>
				</tr>
				<tr>
					<td><input type="radio" name="islem" value="GenelYonetici" onclick="autoSubmit(this.value)"></td>
					<td>Genel Yöneticisi Ekle</td>
					<td><input type="radio" name="islem" value="NormalKullanici" onclick="autoSubmit(this.value)"></td>
					<td>Normal Kullanıcı Ekle</td>
				</tr>
			</table>
		</td>
	</tr>	
	</table>
	<%}
		else{%>
	<form class="form-horizontal" action="../servlet_genelYoneticiController" method="post">
	<%
	if (islem.equals("GenelYonetici")){%>
		<input type="hidden" name="gorevID" value="0">
		<input type="hidden" name="yetkiID" value="4"><%
	}
	if (islem.equals("ReklamYoneticisi")){%>
		<input type="hidden" name="gorevID" value="0">
		<input type="hidden" name="yetkiID" value="2"><%
	}
	if (islem.equals("FakulteYoneticisi")){%>
		<input type="hidden" name="yetkiID" value="3"><%
	}
	if (islem.equals("NormalKullanici")){%>
		<input type="hidden" name="yetkiID" value="1"><%
	}
	%>
		<input type="hidden" name="islem" value="KullaniciEkle">
		<fieldset>
			<legend><%=islem %> Ekleme Paneli</legend><%	
			if (islem.equals("FakulteYoneticisi") | islem.equals("NormalKullanici")){%>
				<div class="control-group">
					<label class="control-label">Kullanıcının Fakultesi</label>
					<div class="controls">
						<select name="gorevID"><%
						if (fakulteler.size() == 0){%>
							<option value="0">Kayıtlı Fakülte Yok</option>						
						<%}
						else{%>
							<option value="0">Fakülte Seçiniz</option><%
							for (tfakulte fakulte : fakulteler){%>
								<option value="<%=fakulte.getFakulteID()%>">
									<%=fakulte.getFakulteAd() %>
								</option>		
							<%}
						}%>
						</select>
					</div>
				</div><%
			} %>
			<div class="control-group">
				<label class="control-label">Kullanıcı Adı</label>
				<div class="controls">
					<input type="text" name="username" placeholder="Username">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Kullanıcı Parolası</label>
				<div class="controls">
					<input type="text" name="password" placeholder="Password">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Kullanıcının İsmi</label>
				<div class="controls">
					<input type="text" name="name" placeholder="Kullanıcının İsmi">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Kullanıcının Soyismi</label>
				<div class="controls">
					<input type="text" name="surname" placeholder="Kullanıcının Soyismi">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Kullanıcının Telefonu</label>
				<div class="controls">
					<input type="text" name="telefon" placeholder="Kullanıcının Telefonu">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Kullanıcının Adresi</label>
				<div class="controls">
					<textarea rows="4" cols="9" name="adres"></textarea>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="submit" value="Kullanıcı Ekle" class="btn" style="width: 5.8cm;">
				</div>
			</div>
		</fieldset>
	</form>
	<%} %>
</body>
</html>