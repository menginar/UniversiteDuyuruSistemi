<%@page import="com.dCandan.DuyuruSistemi.Model.tbolum"%>
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
	String gelen_veri = request.getQueryString();
	String fakulteID = gelen_veri.substring(gelen_veri.indexOf("=") + 1, gelen_veri.length());
		
	database_genelYoneticiController controller = new database_genelYoneticiController();
		
	ArrayList<tbolum> bolumler = controller.BolumListele();
		
	out.println("<select name=\"bolumID\">");
	for (tbolum bolum : bolumler) {
	  if (bolum.getFakulteID() == Integer.parseInt(fakulteID)) {
		out.println("<option value=\""+bolum.getBolumID()+"\">"+bolum.getBolumAd()+"</option>");
	  }
	}
	out.println("</select>");
	%>
  </body>
</html>