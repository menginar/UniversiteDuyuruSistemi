<%@page import="com.dCandan.DuyuruSistemi.Model.treklam"%>
<%@page
	import="com.dCandan.DuyuruSistemi.DatabaseController.database_reklamYoneticisiController"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function autoSubmit(str) {
		document.getElementById("reklamBelirtec").value = str;
		document.getElementById("form").submit();
	}
</script>
</head>
<body>
	<%
		session.setAttribute("secilenIslem", "ReklamDuzenle_Reklamlar");
		database_reklamYoneticisiController controller = new database_reklamYoneticisiController();
		ArrayList<treklam> ButunReklamlar = controller.ReklamListele();
		ArrayList<treklam> ListelenecekReklamlar = new ArrayList<treklam>();

		for (treklam Reklam : ButunReklamlar) {
			if ((Reklam.getYayinlanmaDurum() == 1) && (Reklam.getUsername().equals(session.getAttribute("username"))))
				ListelenecekReklamlar.add(Reklam);
		}
		int reklamAdet = ListelenecekReklamlar.size();
		int sayfaBasinaReklamAdet = 4;
		int sayfaAdet = (reklamAdet / sayfaBasinaReklamAdet);
		if ((reklamAdet % sayfaBasinaReklamAdet) > 0)
			sayfaAdet++;
		String pageNum = request.getParameter("page") + "";
		int ilkSatir = 0;
		if (pageNum.equals(null) | pageNum.equals(null + "")
				| pageNum.equals("0"))
			ilkSatir = 0;
		if (pageNum.equals("1"))
			ilkSatir = 4;
		if (pageNum.equals("2"))
			ilkSatir = 8;
		if (pageNum.equals("3"))
			ilkSatir = 12;
		if (pageNum.equals("4"))
			ilkSatir = 16;
		if (pageNum.equals("5"))
			ilkSatir = 20;
		int satirNo = ilkSatir;
		int i = 0;
	%>
	<form class="form-horizontal"
		action="../servlet_reklamYoneticisiController" method="post" id="form">
		<input type="hidden" name="reklamBelirtec" id="reklamBelirtec">
		<input type="hidden" name="islem" value="ReklamDuzenle">
		<fieldset>
			<legend>Reklam Düzenleme Paneli</legend>
			<div class="control-group" style="height: auto;">
				<table class="table table-bordered" style="font-size: small;">
					<tr>
						<th style="text-align: center;">Reklam No</th>
						<th style="text-align: center;">Reklam İcerik</th>
						<th style="text-align: center;">Reklam Başlangıç</th>
						<th style="text-align: center;">Reklam Bitiş</th>
						<th style="text-align: center;">Reklam Resmi</th>
						<th />
					</tr>
					<%
						if (ListelenecekReklamlar.size() > 0) {
							for (; satirNo < ListelenecekReklamlar.size(); satirNo++) {
								treklam Reklam = ListelenecekReklamlar.get(satirNo);
								sayfaBasinaReklamAdet--;
								i++;

								String reklamIcerik = "";
								if (Reklam.getReklamIcerik().length() > 25)
									reklamIcerik = Reklam.getReklamIcerik()
											.substring(0, 25) + "...";
								else
									reklamIcerik = Reklam.getReklamIcerik();
								String filePath = request.getContextPath()+"/ReklamYoneticisi/ReklamResimleri/";
								String imagePath = filePath + Reklam.getReklamResimPath();
					%>
					<tr>
						<td style="text-align: center;"><%=Reklam.getReklamID()%> <input
							type="hidden" name="reklamID<%=i%>"
							value="<%=Reklam.getReklamID()%>"></td>
						<td style="text-align: center;">
							<a title="<%=Reklam.getReklamIcerik()%>"><%=reklamIcerik%></a>
						</td>
						<td style="text-align: center;"><%=Reklam.getReklamBaslangicTarih()%>
						</td>
						<td style="text-align: center;"><%=Reklam.getReklamBitisTarih()%>
						</td>
						<td style="text-align: center;">
							<img src="<%=imagePath%>" width="80">
						</td>
						<td><a class="btn" onclick="autoSubmit('<%=i%>')"><i
								class="icon-edit"></i></a></td>
					</tr>
					<%
						if (sayfaBasinaReklamAdet == 0)
									break;
							}
						} else {
					%>
					<tr>
						<td>Kayıt Yok</td>
						<td>Kayıt Yok</td>
						<td>Kayıt Yok</td>
						<td>Kayıt Yok</td>
						<td>Kayıt Yok</td>
						<td />
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</fieldset>
		<input type="hidden" name="reklamAdet" value="<%=i%>">
	</form>
	<div class="pagination pagination-centered">
		<ul>
			<%
				if (sayfaAdet > 1) {
					for (int j = 0; j < sayfaAdet; j++) {
			%>
			<li><a href="?page=<%=j%>"><%=j + 1%></a></li>
			<%
				}
				}
			%>
		</ul>
	</div>
</body>
</html>