<%@page import="com.dCandan.DuyuruSistemi.Model.tuser_bilgi"%>
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
	database_genelYoneticiController controller = new database_genelYoneticiController();

	String username = session.getAttribute("kullanilacakID")+"";
	ArrayList<tuser_bilgi> ButunKullanicilar = controller.KullaniciBilgiListele();
	tuser_bilgi UserBilgi = null;
	
	for (tuser_bilgi User : ButunKullanicilar){
		System.out.println("user : "+User.getUsername()+", usernaem : "+username);
		if (User.getUsername().equals(username)){
			UserBilgi = User;
			break;
		}
	}
%>
<form class="form-horizontal" action="../servlet_genelYoneticiController" method="post">
		<input type="hidden" name="islem" value="KullaniciBilgisi">
		<input type="hidden" name="username" value="<%=UserBilgi.getUsername()%>">
		<fieldset>
			<legend>Kullanici Bilgisi Düzenleme Paneli</legend>
			<div class="control-group">
				<label class="control-label">Kullanıcı Adı</label>
				<div class="controls">
					<input type="text" value="<%=UserBilgi.getUsername()%>" disabled="disabled">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">İsim</label>
				<div class="controls">
					<input type="text" name="name" value="<%=UserBilgi.getName()%>">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Soyisim</label>
				<div class="controls">
					<input type="text" name="surname" value="<%=UserBilgi.getSurname()%>">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Telefon</label>
				<div class="controls">
					<input type="text" name="telefon" value="<%=UserBilgi.getTelephone()%>">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Adres</label>
				<div class="controls">
					<textarea rows="4" cols="3" name="adres"><%=UserBilgi.getAddress() %></textarea>
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