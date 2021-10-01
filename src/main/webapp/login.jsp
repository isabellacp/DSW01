<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<%
    String contextPath = request.getContextPath().replace("/", "");
%>
<html>
<fmt:bundle basename="message">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="login.title"/></title>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>
<%@include file="logado/menu.jsp" %>
<c:if test="${mensagens.existeErros}">
    <div id="erro">
        <ul>
            <c:forEach var="erro" items="${mensagens.erros}">
                <li> ${erro} </li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<form method="post" action="index.jsp">


    <section class="hero is-fullheight-with-navbar">
        <div class="hero-body">
            <div class="container">
                <div class="columns is-centered">
                    <div class="column is-5-tablet is-4-desktop is-3-widescreen">
                        <form action="" class="box">
                            <div class="field">
                                <label for="email" class="label">Email</label>
                                <div class="control has-icons-left">
                                    <input name="email" type="email" id="email" value="${param.email}"
                                           placeholder="email@gmail.com" class="input" required>
                                    <span class="icon is-small is-left">
                  <i class="fa fa-envelope"></i>
                </span>
                                </div>
                            </div>
                            <div class="field">
                                <label for="senha" class="label">Password</label>
                                <div class="control has-icons-left">
                                    <input name="senha" type="password" id="senha" placeholder="*******" class="input"
                                           required>
                                    <span class="icon is-small is-left">
                  <i class="fa fa-lock"></i>
                </span>
                                </div>
                            </div>
                            <div class="field">
                                <button type="submit" name="bOK" class="button is-success"><fmt:message
                                        key="login.login_label"/></button>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </section>
</form>
</body>
</fmt:bundle>

</html>