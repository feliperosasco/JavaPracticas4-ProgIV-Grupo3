<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
	<%@ page import="entidades.TipoSeguro" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="Stylesheet.css">
</head>
<body>
<!-- Menú de Navegación -->
    <jsp:include page="Menu.jsp" />

<div class="formulario">
    <h2>Agregar Seguros</h2>

    <form action="ServletAgregarSeguro" method="post">

        <p>
            <label>Id Seguro:</label>
            <strong><%= request.getAttribute("proximoId") %></strong>
        </p>

        <p>
            <label for="descripcion">Descripción:</label>
            <input type="text" name="descripcion" id="descripcion" required>
        </p>

        <p>
            <label for="idTipo">Tipo de Seguro:</label>
            <select name="idTipo" id="idTipo" required>
                <option value="">-- Seleccione --</option>
                <%
                    List<TipoSeguro> tipos = (List<TipoSeguro>) request.getAttribute("listaTipos");
                    if (tipos != null) {
                        for (TipoSeguro t : tipos) {
                %>
                            <option value="<%= t.getIdTipo() %>"><%= t.getDescripcion() %></option>
                <%
                        }
                    }
                %>
            </select>
        </p>

        <p>
            <label for="costoContratacion">Costo contratación:</label>
            <input type="number" step="0.01" name="costoContratacion" id="costoContratacion" required>
        </p>

        <p>
            <label for="costoMaximo">Costo Máximo Asegurado:</label>
            <input type="number" step="0.01" name="costoMaximo" id="costoMaximo" required>
        </p>

        <p>
            <input type="submit" value="Aceptar">
        </p>

    </form>
    </div>
</body>
</html>