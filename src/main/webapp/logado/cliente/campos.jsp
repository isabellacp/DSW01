<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="table">
    <caption>
        <c:choose>
            <c:when test="${cliente != null}">
                <fmt:message key="client.edit_title"/>
            </c:when>
            <c:otherwise>
                <fmt:message key="client.create_title"/>
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${cliente != null}">
        <input type="hidden" id="id" name="id"
               value="<c:out value='${cliente.id}' />"/>

    </c:if>

    <tr>
        <td><label for="cpf"> <fmt:message key="client.cpf"/>

        </label></td>
        <td><input type="text" id="cpf" name="cpf" size="18" required
                   value="<c:out value='${cliente.cpf}' />"/></td>
    </tr>
    <tr>
        <td><label for="email"> <fmt:message key="client.email"/>

        </label></td>
        <td><input type="text" id="email" name="email" size="18" required
                   value="<c:out value='${cliente.email}' />"/></td>
    </tr>
    <tr>
        <td><label for="senha"> <fmt:message key="client.password"/>

        </label></td>
        <td><input type="text" id="senha" name="senha" size="18" required
                   value="<c:out value='${cliente.senha}' />"/></td>
    </tr>
    <tr>
        <td><label for="nome"> <fmt:message key="client.name"/>

        </label></td>
        <td><input type="text" id="nome" name="nome" size="18" required
                   value="<c:out value='${cliente.nome}' />"/></td>
    </tr>
    <tr>
        <td><label for="telefone"> <fmt:message key="client.phone"/>

        </label></td>
        <td><input type="text" id="telefone" name="telefone" size="18" required
                   value="<c:out value='${cliente.telefone}' />"/></td>
    </tr>
    <tr>
        <td><label for="sexo"> <fmt:message key="client.gender"/>

        </label></td>
        <td><input type="text" id="sexo" name="sexo" size="18" required
                   value="<c:out value='${cliente.sexo}' />"/></td>
    </tr>
    <tr>
        <td><label for="date"> <fmt:message key="client.birth"/>

        </label></td>
        <td><input type="text" id="date" name="date" size="18" required
                   value="<c:out value='${cliente.date}' />"/></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit"
                                              value="        <fmt:message key='client.save_button'/>
"/></td>
    </tr>
</table>