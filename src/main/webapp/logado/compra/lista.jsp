<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="message">

    <head>
        <title><fmt:message key="package.list_title"/></title>
        <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>

    <body>

    <%
        String contextPath = request.getContextPath().replace("/", "");
    %>
    <%@include file="../menu.jsp" %>


    <div align="center">
        <table class="table">
            <caption></caption>
            <tr>
                <th><fmt:message key="package.name"/></th>
                <th><fmt:message key="package.destination"/></th>
                <th><fmt:message key="package.departure"/></th>

                <th><fmt:message key="package.date_bought"/></th>

            </tr>
            <c:forEach var="compra" items="${requestScope.listaCompras}">
                <tr>
                    <td>${compra.pacote.id}</td>
                    <td>${compra.pacote.destino}</td>
                    <td>${compra.pacote.partida}</td>
                    <td>${compra.data}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
</fmt:bundle>

</html>