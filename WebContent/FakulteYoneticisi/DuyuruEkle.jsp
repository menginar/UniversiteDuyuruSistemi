<%@page import="com.dCandan.DuyuruSistemi.Model.tfakulte"%>
<%@page import="com.dCandan.DuyuruSistemi.DatabaseController.database_fakulteYoneticisiController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
													pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	function bolumListele(str){
		var xmlhttp;
		if (str == "") {
			document.getElementById("bolumID").innerHTML = "";
			return;
		}
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("bolumID").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET", "../createAutoList.jsp?fakulteID=" + str, true);
		xmlhttp.send();
	}
</script>
</head>
<body>
	<%
	
		session.setAttribute("secilenIslem", "DuyuruEkle");
		database_fakulteYoneticisiController controller 
								= new database_fakulteYoneticisiController();
		ArrayList<tfakulte> fakulteler = controller.FakulteListele();
		int duyuruID = controller.DuyuruIDHesapla();
		int gorevID = Integer.parseInt(session.getAttribute("gorevID")+"");
		
	%>
	<form class="form-horizontal" action="../servlet_fakulteYoneticisiController" 
																method="post">
		<input type="hidden" name="islem" value="DuyuruEkle">
		<input type="hidden" name="duyuruTur" value="0">
		<input type="hidden" name="duyuruID" value="<%=duyuruID%>">
		<fieldset>
			<legend>Duyuru Ekleme Paneli</legend>
			<div class="control-group">
				<label class="control-label">Yayınlanacak Fakülte</label>
				<div class="controls">
					<select name="fakulteID" onchange="bolumListele(this.value)"><%
					if (fakulteler.size() == 0){%>
						<option value="0">Kayıtlı Fakülte Yok</option><%
					}
					else{%>
						<option value="0">Fakülte Seçiniz</option><%
						for (tfakulte fakulte : fakulteler){
							if (fakulte.getFakulteID() == gorevID){%>
								<option value="<%=fakulte.getFakulteID()%>">
									<%=fakulte.getFakulteAd() %>
								</option><%
							}
						}
					}%>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Yayınlanacak Bölüm</label>
				<div class="controls">
					<select name="bolumID" id="bolumID">
						<option value="0"></option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Başlangıç Tarihi</label>
				<div class="controls">
					<input type="date" name="baslangicTarih" 
												placeholder="Başlangıç Tarihi">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Bitiş Tarihi</label>
				<div class="controls">
					<input type="date" name="bitisTarih" 
													placeholder="Bitiş Tarihi">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Duyuru Başlığı</label>
				<div class="controls">
					<input type="text" name="duyuruBaslik" 
													placeholder="Duyuru Başlığı">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Duyuru İçeriği</label>
				<div class="controls">
					<textarea rows="4" cols="7" name="duyuruIcerik"></textarea>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="submit" value="Duyuru Ekle" class="btn" 
														style="width: 5.8cm;">
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>