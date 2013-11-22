<%@page import="com.dCandan.DuyuruSistemi.Model.tuser"%>
<%@page import="com.dCandan.DuyuruSistemi.Model.tuser_bilgi"%>
<%@page import="com.dCandan.DuyuruSistemi.DatabaseController.database_genelYoneticiController"%>
<%@page import="java.util.Stack"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function autoSubmit(str, str2) {
		document.getElementById("islemBelirtec").value = str;
		document.getElementById("kullaniciBelirtec").value = str2; 
		document.getElementById("form").submit();
	}
</script>
</head>
<body>
<%
	session.setAttribute("secilenIslem", "KullaniciBilgisiDuzenle");
	
	database_genelYoneticiController controller = new database_genelYoneticiController();
	
	ArrayList<tuser_bilgi> ButunKullaniciBilgileri = controller.KullaniciBilgiListele();
	ArrayList<tuser> ButunKullanicilar = controller.KullaniciListele();
	
	int kullaniciAdet = ButunKullaniciBilgileri.size();
	int sayfaBasinaKullaniciAdet = 6;
	int sayfaAdet = (kullaniciAdet / sayfaBasinaKullaniciAdet);
	if ((kullaniciAdet % sayfaBasinaKullaniciAdet) > 0)
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
%>
	<form class="form-horizontal" action="../servlet_genelYoneticiController" method="post" id="form">
		<input type="hidden" name="islemBelirtec" id="islemBelirtec">
		<input type="hidden" name="kullaniciBelirtec" id="kullaniciBelirtec">
		<input type="hidden" name="islem" value="KullaniciBilgisiDuzenle">
		<fieldset>
			<legend>Kullanıcı Bilgisi Düzenleme Paneli</legend>
			<div class="control-group" style="height: auto;">
					<table class="table table-bordered" style="font-size: small;">
						<tr>
							<th>Kullanıcı Adı</th><th>Görevi</th><th>İsim</th><th>Soyisim</th><th>Adres</th><th>Telefon Numarası</th><th></th>
						</tr>
						<%
						if (ButunKullaniciBilgileri.size() > 0){
							for (; satirNo < ButunKullaniciBilgileri.size(); satirNo++) {
								tuser_bilgi UserBilgi = ButunKullaniciBilgileri.get(satirNo);
								String gorev = "";
								
								for (tuser User : ButunKullanicilar){
									if (User.getUsername().equals(UserBilgi.getUsername())){
										gorev = controller.IsimGetir("tyetki", User.getYetkiID()+"");
										break;
									}
								}
							%>
						<tr>
							<td>
								<%=UserBilgi.getUsername() %>
								<input type="hidden" name="username<%=i%>" value="<%=UserBilgi.getUsername()%>">
							</td>
							<td><%=gorev %></td>
							<td><%=UserBilgi.getName() %></td>
							<td><%=UserBilgi.getSurname() %></td>
							<td style="width: 7cm;"><%=UserBilgi.getAddress() %></td>
							<td><%=UserBilgi.getTelephone() %></td>
							<td>
								<a class="btn" onclick="autoSubmit('Duzenle','<%=i%>')"><i class="icon-edit"></i></a>
								<a class="btn" onclick="autoSubmit('Sil','<%=i%>')"><i class="icon-remove"></i></a>
							</td>
						</tr><%
							sayfaBasinaKullaniciAdet--;
							i++;
							if (sayfaBasinaKullaniciAdet == 0)
								break;
							}
						}else{
						%>
						<tr>
							<td>Kayıt Yok</td>
							<td>Kayıt Yok</td>
							<td>Kayıt Yok</td>
							<td>Kayıt Yok</td>
							<td>Kayıt Yok</td>
							<td>Kayıt Yok</td>
							<td/>
						</tr>
					<%} %>
					</table>
			</div>
		</fieldset>
		<input type="hidden" name="kullaniciAdet" value="<%=i%>">
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