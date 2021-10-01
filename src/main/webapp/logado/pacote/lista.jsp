<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="message">

    <head>
        <title><fmt:message key="package.title"/></title>
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
                <th><fmt:message key="agency.cnpj"/></th>
                <th><fmt:message key="package.destination"/></th>
                <th><fmt:message key="package.departure"/></th>
                <th><fmt:message key="package.duration"/></th>
                <th><fmt:message key="package.price"/></th>
                <c:if test="${sessionScope.usuarioLogado!= null}">
                    <th><fmt:message key="package.actions"/></th>
                </c:if>

            </tr>
            <c:forEach var="pacote" items="${requestScope.listaPacotes}">
                <tr>
                    <td>${pacote.agencia.cnpj}</td>
                    <td>${pacote.destino}</td>
                    <td>${pacote.partida}</td>
                    <td>${pacote.duracao}</td>
                    <td>${pacote.valor}</td>
                    <td>
                        <c:if test="${sessionScope.usuarioLogado!= null && sessionScope.usuarioLogado.role=='agencia'}">
                            <a
                                    href="/<%= contextPath %>/pacote/edicao?id=<c:out value='${pacote.id}' />">
                                <fmt:message key="action.edit"/>
                            </a>
                            <a
                                    href="/<%= contextPath %>/pacote/remocao?id=<c:out value='${pacote.id}' />"
                                    onclick="return confirm('<fmt:message key="action.confirm"/>');">
                                <fmt:message key="action.delete"/>
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.usuarioLogado!= null && sessionScope.usuarioLogado.role=='cliente'}">
                            <a
                                    href="/<%= contextPath %>/compra/efetuar?pacote_id=<c:out value='${pacote.id}' />">
                                <fmt:message key="action.buy"/>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
</fmt:bundle>

</html>
