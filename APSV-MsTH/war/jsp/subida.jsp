<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>
<c:if test="${not null user and not null msth and msth.estado == 2}">
    Document upload form. <c:out value="${msth.titulo}" />
    <form action="<%=blobstoreService.createUploadUrl("/upload")%>"
        method="post" enctype="multipart/form-data">
        <input id="author" name="author" type="hidden" value="${msth.autor}" />
        <input type="file" name="file" />
              <input type="submit" value="Upload document" />
              </form>
</c:if>
</body>
</html>