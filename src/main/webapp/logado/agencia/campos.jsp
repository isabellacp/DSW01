<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="table">
    <caption>
        <c:choose>
            <c:when test="${agencia != null}">
                <fmt:message key="agency.edit_title"/>
            </c:when>
            <c:otherwise>
                <fmt:message key="agency.create_title"/>
            </c:otherwise>
        </c:choose>
    </caption>

    <c:if test="${agencia != null}">
        <input type="hidden" id="id" name="id" size="18" required
               value="<c:out value='${agencia.id}' />"/>
    </c:if>


    <tr>
        <td><label for="cnpj"> <fmt:message key="agency.cnpj"/>

        </label></td>
        <td><input type="text" id="cnpj" name="cnpj" size="18" required
                   value="<c:out value='${agencia.cnpj}' />"/></td>
    </tr>
    <tr>
        <td><label for="nome"> <fmt:message key="agency.name"/>

        </label></td>
        <td><input type="text" id="nome" name="nome" size="45" required
                   value="<c:out value='${agencia.nome}' />"/></td>
    </tr>
    <tr>
        <td><label for="email"> <fmt:message key="agency.email"/>

        </label></td>
        <td><input type="text" id="email" name="email" size="45" required
                   value="<c:out value='${agencia.email}' />"/></td>
    </tr>
    <tr>
        <td><label for="senha"> <fmt:message key="agency.password"/>

        </label></td>
        <td><input type="text" id="senha" name="senha" size="45" required
                   value="<c:out value='${agencia.senha}' />"/></td>
    </tr>
    <tr>
        <td><label for="descricao"> <fmt:message key="agency.description"/>

        </label></td>
        <td><input type="text" id="descricao" name="descricao" size="45" required
                   value="<c:out value='${agencia.descricao}' />"/></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit"
                                              value="                <fmt:message key='agency.save'/>
"/></td>
    </tr>
</table>