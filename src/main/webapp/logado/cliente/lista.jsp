<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="message">

    <head>
        <title>Lista de Clientes</title>
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
                <th><fmt:message key="client.cpf"/></th>
                <th><fmt:message key="client.name"/></th>
                <th><fmt:message key="client.email"/></th>
                <th><fmt:message key="action.title"/></th>

            </tr>
            <c:forEach var="cliente" items="${requestScope.listaClientes}">
                <tr>
                    <td>${cliente.cpf}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.email}</td>
                    <td><a
                            href="/<%= contextPath %>/cliente/edicao?id=<c:out value='${cliente.id}' />">
                        <fmt:message key="action.edit"/>
                    </a>
                        <a
                                href="/<%= contextPath %>/cliente/remocao?id=<c:out value='${cliente.id}' />"
                                onclick="return confirm('<fmt:message key="action.confirm"/>');">
                            <fmt:message key="action.delete"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
</fmt:bundle>

</html>