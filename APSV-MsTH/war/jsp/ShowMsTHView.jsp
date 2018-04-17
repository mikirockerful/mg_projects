<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Pagina de gestion de trabajos fin de master</title>
</head>
<body>
	<p>MsTHs Management System</p>
	<c:if test="${user != null}"> 
		<c:out value="${user}"/>
	</c:if>
	<p>
	<a href="<c:out value="${url}"/>"><c:out value="${urlLinktext}"/></a></p>
	<table>
	<tr>
				<td>AUTOR</td>
				<td>TÍTULO</td>
				<td>RESUMEN</td>
				<td>TUTOR</td>
				<td>SECRETARIO</td>
				<td>ESTADO</td>
			</tr>
		<c:forEach items="${msths}" var="msthi">
			<tr>
				<td><c:out value="${msthi.autor}"/></td>
				<td><c:out value="${msthi.titulo}"/></td>
				<td><c:out value="${msthi.resumen}"/></td>
				<td><c:out value="${msthi.tutor}"/></td>
				<td><c:out value="${msthi.secretario}"/></td>
				<td><c:out value="${msthi.estado}"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>