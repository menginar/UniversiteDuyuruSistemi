<%@page import="com.dCandan.DuyuruSistemi.Model.tfakulte"%>
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
	ArrayList<tfakulte> fakulteler = controller.FakulteListele();
%>
	<form class="form-horizontal" action="../servlet_genelYoneticiController" method="post">
		<input type="hidden" name="islem" value="DuyuruIslemleri">
		<fieldset>
			<legend>Duyuru Düzenleme Paneli</legend>
			<div class="control-group">
				<label class="control-label">Getirilecek Fakülte</label>
				<div class="controls">
					<select name="fakulteID">
					<%
						if (fakulteler.size() == 0){%>
							<option value="0">Kayıtlı Fakülte Yok</option>
						<%}
						else{%>
							<option value="0">Fakülte Seçiniz</option>
						<%
							for (tfakulte fakulte : fakulteler){%>
									<option value="<%=fakulte.getFakulteID()%>"><%=fakulte.getFakulteAd() %></option><%
							}
						}
					%>
					</select>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="submit" value="Fakülte Duyuruları" class="btn" style="width: 5.8cm;">
				</div>
			</div>
			<br><br><br>
		</fieldset>
	</form>
</body>
</html>