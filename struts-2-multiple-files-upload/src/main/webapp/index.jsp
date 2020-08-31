<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Struts 2 Multiple Files Upload Example</title>
</head>
<body>
    <s:if test="hasActionErrors()">
        <s:actionerror />
    </s:if>
    <s:if test="hasActionMessages()">
        <s:actionmessage />
    </s:if>
    <s:form action="uploadMultipleFiles" method="post" enctype="multipart/form-data">
        <s:file label="File1" name="file"></s:file>
        <s:file label="File2" name="file"></s:file>
        <s:file label="File3" name="file"></s:file>
        <s:file label="File4" name="file"></s:file>
        <s:submit value="Upload"></s:submit>
    </s:form>
</body>
</html>