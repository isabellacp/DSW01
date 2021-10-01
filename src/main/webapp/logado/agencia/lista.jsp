<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="message">

    <head>
        <title>Lista de Agencias</title>
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
                <th>CNPJ</th>
                <th>Nome</th>
                <th>Descricao</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="agencia" items="${requestScope.listaAgencias}">
                <tr>
                    <td>${agencia.cnpj}</td>
                    <td>${agencia.nome}</td>
                    <td>${agencia.descricao}</td>
                    <td><a
                            href="/<%= contextPath %>/agencia/edicao?id=<c:out value='${agencia.id}' />">
                        Editar
                    </a>
                        <a
                                href="/<%= contextPath %>/agencia/remocao?id=<c:out value='${agencia.id}' />"
                                onclick="return confirm('Confirmar?');">
                            Deletar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
</fmt:bundle>

</html>