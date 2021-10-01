<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="message">

    <head>
        <title><fmt:message key="agency.create_or_edit"/>
        </title>
        <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>

    <body>
    <%
        String contextPath = request.getContextPath().replace("/", "");
    %>
    <%@include file="../menu.jsp" %>

    <div align="center">
        <c:choose>
            <c:when test="${agencia != null}">
                <form action="atualizacao" method="post">
                    <%@include file="campos.jsp" %>
                </form>
            </c:when>
            <c:otherwise>
                <form action="insercao" method="post">
                    <%@include file="campos.jsp" %>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    <c:if test="${!empty requestScope.mensagens}">
        <ul class="erro">
            <c:forEach items="${requestScope.mensagens}" var="mensagem">
                <li>${mensagem}</li>
            </c:forEach>
        </ul>
    </c:if>
    </body>
</fmt:bundle>

</html>