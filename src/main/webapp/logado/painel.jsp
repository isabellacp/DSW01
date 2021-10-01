<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="message">

    <head>
        <title><fmt:message key="panel.title"/></title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">

    </head>

    <body>

    <%
        String contextPath = request.getContextPath().replace("/", "");
    %>
    <%@include file="menu.jsp" %>
    </body>
</fmt:bundle>

</html>