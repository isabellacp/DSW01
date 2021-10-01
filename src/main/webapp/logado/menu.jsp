<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:bundle basename="message">

<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item">
            <fmt:message key="menu.title"/>
        </a>

        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link">
                    <fmt:message key="menu.list_packages"/>
                </a>

                <div class="navbar-dropdown">
                    <a class="navbar-item" href="/<%=contextPath%>/pacote/listar">
                        <fmt:message key="menu.list_all_packages"/>
                    </a>
                    <a class="navbar-item" href="/<%=contextPath%>/pacote/lista-vigente">
                        <fmt:message key="menu.list_only_active_packages"/>

                    </a>
                </div>
            </div>

            <c:if test="${sessionScope.usuarioLogado != null && sessionScope.usuarioLogado.role=='agencia'}">
                <a class="navbar-item" href="/<%=contextPath%>/pacote/cadastro">
                    <fmt:message key="menu.create_package"/>
                </a>
            </c:if>
            <c:if test="${sessionScope.usuarioLogado != null && sessionScope.usuarioLogado.role=='admin'}">
                <a class="navbar-item" href="/<%=contextPath%>/agencia/listar">
                    <fmt:message key="menu.list_agencies"/>
                </a>
                <a class="navbar-item" href="/<%=contextPath%>/agencia/cadastro">
                    <fmt:message key="menu.create_agency"/>

                </a>
                <a class="navbar-item" href="/<%=contextPath%>/cliente/listar">
                    <fmt:message key="menu.list_clients"/>

                </a>
                <a class="navbar-item" href="/<%=contextPath%>/cliente/cadastro">
                    <fmt:message key="menu.create_client"/>

                </a>
            </c:if>
            <c:if test="${sessionScope.usuarioLogado != null && sessionScope.usuarioLogado.role=='cliente'}">
                <a class="navbar-item" href="/<%=contextPath%>/compra/listar">
                    <fmt:message key="menu.list_bought"/>
                </a>
            </c:if>
        </div>

        <div class="navbar-end">
            <div class="navbar-item">
                <div class="buttons">
                    <c:if test="${sessionScope.usuarioLogado!=null}">
                        <a class="button is-danger" href="${pageContext.request.contextPath}/logout.jsp">
                            <fmt:message key="menu.logout"/>

                        </a>
                    </c:if>
                    <c:if test="${sessionScope.usuarioLogado==null}">
                        <a class="button is-primary" href="${pageContext.request.contextPath}">
                            <fmt:message key="menu.login"/>

                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</nav></fmt:bundle>
