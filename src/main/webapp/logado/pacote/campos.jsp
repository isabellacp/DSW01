<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="table">
    <caption>
        <c:choose>
            <c:when test="${pacote != null}">
                <fmt:message key="package.edit_title"/>
            </c:when>
            <c:otherwise>
                <fmt:message key="package.create_title"/>
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${pacote != null}">
        <input type="hidden" name="id" value="${pacote.id}"/>
    </c:if>
    <tr>
        <td><label for="agencia"> <fmt:message key="agency.name"/>
        </label></td>
        <td><select name="agencia" id="agencia">
            <c:forEach items="${agencias}" var="agencia">
                <option value="${agencia.key}"
                    ${pacote.agencia.id==agencia.key ? 'selected' : '' }>
                        ${agencia.value}</option>
            </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td><label for="destino"> <fmt:message key="package.destination"/>
        </label></td>
        <td><input type="text" id="destino" name="destino" size="18" required
                   value="<c:out value='${pacote.destino}' />"/></td>
    </tr>
    <tr>
        <td><label for="partida"> <fmt:message key="package.departure"/>
        </label></td>
        <td><input type="date" id="partida" name="partida" required
                   value="<c:out value='${pacote.partida}' />"/></td>
    </tr>
    <tr>
        <td><label for="duracao"> <fmt:message key="package.duration"/>
        </label></td>
        <td><input type="number" id="duracao" name="duracao" required
                   value="<c:out value='${pacote.duracao}' />"/></td>
    </tr>
    <tr>
        <td><label for="valor"> <fmt:message key="package.price"/>
        </label></td>
        <td><input type="number" id="valor" name="valor" required
                   value="<c:out value='${pacote.valor}' />"/></td>
    </tr>
    <tr>
        <td><label for="roteiro"><fmt:message key="agency.select_itinerary"/></label></td>
        <td><input type="file" id="roteiro" name="roteiro" accept="application/pdf"><br><br>
        </td>
    </tr>
    <tr>
        <td><label for="imagens"><fmt:message key="agency.select_images"/></label></td>
        <td><input type="file" id="imagens" name="imagens" accept="image/*" multiple><br><br>
        </td>
    </tr>
    <td colspan="2" align="center"><input type="submit"
                                          value="Salvar"/></td>
    </tr>
</table>