<%@page import="com.dCandan.DuyuruSistemi.Model.tduyuru_icerik"%>
<%@page import="com.dCandan.DuyuruSistemi.Model.tduyuru_bilgi"%>
<%@page import="com.dCandan.DuyuruSistemi.DatabaseController.database_genelYoneticiController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function autoSubmit(str) {
		document.getElementById("duyuruBelirtec").value = str;
		document.getElementById("form").submit();
	}
</script>
</head>
<body>
	<%
	session.setAttribute("secilenIslem", "DuyuruBilgisiDuzenle");
	
	int fakulteID = Integer.parseInt(session.getAttribute("fakulteID")+"");
	
	database_genelYoneticiController controller = new database_genelYoneticiController();
	
	ArrayList<tduyuru_bilgi> ButunDuyuruBilgiler = controller.DuyuruBilgiListele();
	ArrayList<tduyuru_icerik> ButunDuyuruIcerikler = controller.DuyuruIcerikListele();
	ArrayList<tduyuru_bilgi> ListelenecekDuyuruBilgiler = new ArrayList<tduyuru_bilgi>();
	
	for (tduyuru_bilgi DuyuruBilgi : ButunDuyuruBilgiler){
		if (DuyuruBilgi.getFakulteID() == fakulteID)
			ListelenecekDuyuruBilgiler.add(DuyuruBilgi);
	}
	int duyuruAdet = ListelenecekDuyuruBilgiler.size();
	int sayfaBasinaDuyuruAdet = 6;
	int sayfaAdet = (duyuruAdet / sayfaBasinaDuyuruAdet);
	if ((duyuruAdet % sayfaBasinaDuyuruAdet) > 0)
		sayfaAdet++;
	String pageNum = request.getParameter("page")+"";
	int ilkSatir = 0;
	if (pageNum.equals(null) | pageNum.equals(null+"") | pageNum.equals("0"))
		ilkSatir = 0;
	if (pageNum.equals("1"))
		ilkSatir = 6;
	if (pageNum.equals("2"))
		ilkSatir = 12;
	if (pageNum.equals("3"))
		ilkSatir = 18;
	if (pageNum.equals("4"))
		ilkSatir = 24;
	if (pageNum.equals("5"))
		ilkSatir = 30;
	int satirNo = ilkSatir;
	int i=0;
	String ozelDuyuru = null;
	%>
	<form class="form-horizontal" action="../servlet_genelYoneticiController" method="post" id="form">
		<input type="hidden" name="islem" value="DuyuruBilgisiDuzenle">
		<input type="hidden" name="duyuruBelirtec" id="duyuruBelirtec">
		<fieldset>
			<legend>Duyuru Bilgisi Düzenleme Paneli</legend>
			<div class="control-group" style="height: auto;">
					<table class="table table-bordered" style="font-size: small;">
						<tr>
							<th>Duyuru No</th><th>Duyuru Fakültesi</th><th>Duyuru Bölümü</th>
							<th>Duyuru Başlık</th><th>Duyuru Başlangıç</th><th>Duyuru Bitiş</th>
						</tr><%
							if (ListelenecekDuyuruBilgiler.size() > 0) {
								for (; satirNo < ListelenecekDuyuruBilgiler.size(); satirNo++) {
									tduyuru_bilgi DuyuruBilgi = ListelenecekDuyuruBilgiler.get(satirNo);
									tduyuru_icerik DuyuruIcerik = null;
									for (tduyuru_icerik Icerik : ButunDuyuruIcerikler) {
										if (DuyuruBilgi.getDuyuruID() == Icerik.getDuyuruID()) {
											DuyuruIcerik = Icerik;
											sayfaBasinaDuyuruAdet--;
											i++;
											break;
										}
									}
									if (DuyuruBilgi.getDuyuruTurID() == 3){
										ozelDuyuru = "bold";
									}%>
						<tr style="font-weight: <%=ozelDuyuru%>; ">	
							<td>
								<%=DuyuruBilgi.getDuyuruID() %>
								<input type="hidden" name="duyuruID<%=i%>" value="<%=DuyuruBilgi.getDuyuruID()%>">
							</td>
							<td>
								<%=controller.IsimGetir("tfakulte", DuyuruBilgi.getFakulteID()+"") %>
							</td>
							<td>
								<%=controller.IsimGetir("tbolum", DuyuruBilgi.getBolumID()+"") %>
							</td>
							<td>
								<a title="<%=DuyuruIcerik.getDuyuruIcerik()%>"><%=DuyuruIcerik.getDuyuruBaslik() %></a>
							</td>
							<td>
								<%=DuyuruIcerik.getDuyuruBaslangicTarih() %>
							</td>
							<td>
								<%=DuyuruIcerik.getDuyuruBitisTarih() %>
							</td>
							<td>
								<a class="btn" onclick="autoSubmit('<%=i%>')"><i class="icon-edit"></i></a>
							</td>
						</tr><%
								if (sayfaBasinaDuyuruAdet == 0)
									break;
								ozelDuyuru = null;
								}
							}else{%>
						<tr>
							<td>Kayıt Yok</td>
							<td>Kayıt Yok</td>
							<td>Kayıt Yok</td>
							<td>Kayıt Yok</td>
							<td>Kayıt Yok</td>
							<td>Kayıt Yok</td>
						</tr>
						<%	}%>
					</table>
			</div>
		</fieldset>
		<input type="hidden" name="duyuruAdet" value="<%=i%>">
	</form>
	<div class="pagination pagination-centered">
		<ul><%
		if (sayfaAdet > 1){
			for (int j = 0; j < sayfaAdet; j++) {%>
				<li><a href="?page=<%=j%>"><%=j+1 %></a></li><%
			}
		}%>
		</ul>
	</div>
</body>
</html>