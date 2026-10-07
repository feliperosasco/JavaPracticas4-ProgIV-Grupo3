<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Seguro" %>
<%@ page import="entidades.TipoSeguro" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="Head.jsp" />
    <title>Listar seguros</title>
</head>
<body>
    <jsp:include page="Menu.jsp" />

    <h2>Listar seguros</h2>

    <form action="ServletListarSeguro" method="post">
        <label for="idTipo">Filtrar por tipo de seguro:</label>
        <select name="idTipo" id="idTipo">
            <option value="">Todos</option>
            <%
                List<TipoSeguro> tipos = (List<TipoSeguro>) request.getAttribute("listaT");
                Integer idTipoSeleccionado = (Integer) request.getAttribute("idTipoSeleccionado");
                if (tipos != null) {
                    for (TipoSeguro tipo : tipos) {
                        boolean seleccionado = idTipoSeleccionado != null
                                && idTipoSeleccionado.intValue() == tipo.getIdTipo();
            %>
                <option value="<%= tipo.getIdTipo() %>" <%= seleccionado ? "selected" : "" %>>
                    <%= tipo.getDescripcion() %>
                </option>
            <%
                    }
                }
            %>
        </select>
        <button type="submit">Filtrar</button>
    </form>

    <table class="lista">
        <thead>
            <tr>
                <th>ID Seguro</th>
                <th>Descripción</th>
                <th>ID Tipo</th>
                <th>Costo de contratación</th>
                <th>Costo asegurado</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Seguro> seguros = (List<Seguro>) request.getAttribute("listaS");
                if (seguros != null && !seguros.isEmpty()) {
                    for (Seguro seguro : seguros) {
            %>
                <tr>
                    <td><%= seguro.getIdSeguro() %></td>
                    <td><%= seguro.getDescripcion() %></td>
                    <td><%= seguro.getIdTipo() %></td>
                    <td><%= seguro.getCostoContratacion() %></td>
                    <td><%= seguro.getCostoAsegurado() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="5">No hay seguros para mostrar.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
