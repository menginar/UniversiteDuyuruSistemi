<%@page import="com.dCandan.DuyuruSistemi.Model.tduyuru_icerik"%>
<%@page import="com.dCandan.DuyuruSistemi.Model.tduyuru_bilgi"%>
<%@page import="com.dCandan.DuyuruSistemi.DatabaseController.database_fakulteYoneticisiController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<%
		database_fakulteYoneticisiController controller 
							= new database_fakulteYoneticisiController();
		int duzenlenecekDuyuruID = Integer.parseInt(session
				.getAttribute("duyuruID") + "");
		ArrayList<tduyuru_bilgi> ButunDuyuruBilgiler = controller
				.DuyuruBilgiListele();
		ArrayList<tduyuru_icerik> ButunDuyuruIcerikler = controller
				.DuyuruIcerikListele();

		tduyuru_bilgi DuzenlenecekDuyuruBilgi = null;
		tduyuru_icerik DuzenlenecekDuyuruIcerik = null;
		/*
		 *	HAngi duyurunun duzenleneceği seçiliyor.Aslında bir önceki formdan veri geliyor ancak 
		 *	türkçe karakter sorunu nedeniyle bu yol izlendi.
		 */
		for (tduyuru_bilgi DuyuruBilgi : ButunDuyuruBilgiler) {
			if (DuyuruBilgi.getDuyuruID() == duzenlenecekDuyuruID) {
				DuzenlenecekDuyuruBilgi = DuyuruBilgi;
				break;
			}
		}
		for (tduyuru_icerik DuyuruIcerik : ButunDuyuruIcerikler) {
			if (DuyuruIcerik.getDuyuruID() == duzenlenecekDuyuruID) {
				DuzenlenecekDuyuruIcerik = DuyuruIcerik;
				break;
			}
		}
		String fakulteAd = controller
				.IsimGetir("tfakulte", DuzenlenecekDuyuruBilgi.getFakulteID() + "");
		String bolumAd = controller
				.IsimGetir("tbolum", DuzenlenecekDuyuruBilgi.getBolumID() + "");
	%>
	<form class="form-horizontal" 
		action="../servlet_fakulteYoneticisiController" method="post">
		
		<input type="hidden" name="islem" value="DuyuruDuzenle"> 
		<input type="hidden" name="islemBelirtec" value="IcerikDuzenle0"> 
		<input type="hidden" name="duyuruID"
			value="<%=DuzenlenecekDuyuruBilgi.getDuyuruID()%>"> 
		<input type="hidden" name="fakulteID"
			value="<%=DuzenlenecekDuyuruBilgi.getFakulteID()%>"> 
		<input type="hidden" name="bolumID"
			value="<%=DuzenlenecekDuyuruBilgi.getBolumID()%>">
		
		<fieldset>
			<legend>Duyuru Düzenleme Paneli</legend>
			<div class="control-group">
				<label class="control-label">Yayınlanacak Fakülte</label>
				<div class="controls">
					<input type="text" value="<%=fakulteAd%>" disabled="disabled">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Yayınlanacak Bölüm</label>
				<div class="controls">
					<input type="text" value="<%=bolumAd%>" disabled="disabled">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Başlangıç Tarihi</label>
				<div class="controls">
					<input type="date" name="baslangicTarih"
						value="<%=DuzenlenecekDuyuruIcerik.getDuyuruBaslangicTarih()%>">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Bitiş Tarihi</label>
				<div class="controls">
					<input type="date" name="bitisTarih"
						value="<%=DuzenlenecekDuyuruIcerik.getDuyuruBitisTarih()%>">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Duyuru Başlığı</label>
				<div class="controls">
					<input type="text" name="duyuruBaslik"
						value="<%=DuzenlenecekDuyuruIcerik.getDuyuruBaslik()%>">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Duyuru İçeriği</label>
				<div class="controls">
					<textarea rows="4" cols="9" 
						name="duyuruIcerik"><%=DuzenlenecekDuyuruIcerik.getDuyuruIcerik()%></textarea>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="submit" value="Onaya Gönder" class="btn"
						style="width: 5.8cm;">
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>