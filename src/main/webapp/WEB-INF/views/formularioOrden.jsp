
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.sgos.entities.OrdenServicio" %>

<%
    OrdenServicio orden =
        (OrdenServicio) request.getAttribute("orden");

    boolean editar = orden != null;
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario de Orden - SGOS</title>
</head>
<body>

    <h1><%= editar ? "Editar orden" : "Registrar orden" %></h1>

    <form action="<%= request.getContextPath() %>/ordenes"
          method="post">

        <input type="hidden" name="accion"
               value="<%= editar ? "actualizar" : "guardar" %>">

        <% if (editar) { %>
            <input type="hidden" name="id"
                   value="<%= orden.getId() %>">
        <% } %>

        <label>Descripción:</label>
        <input type="text" name="descripcion" required
               value="<%= editar ? orden.getDescripcion() : "" %>">
        <br><br>

        <label>Fecha:</label>
        <input type="date" name="fecha" required
               value="<%= editar ? orden.getFecha() : "" %>">
        <br><br>

        <label>Estado:</label>
        <select name="estado" required>
            <option value="Pendiente"
                <%= editar && "Pendiente".equals(orden.getEstado())
                    ? "selected" : "" %>>
                Pendiente
            </option>

            <option value="En proceso"
                <%= editar && "En proceso".equals(orden.getEstado())
                    ? "selected" : "" %>>
                En proceso
            </option>

            <option value="Finalizada"
                <%= editar && "Finalizada".equals(orden.getEstado())
                    ? "selected" : "" %>>
                Finalizada
            </option>
        </select>
        <br><br>

        <label>Cliente:</label>
        <input type="text" name="cliente" required
               value="<%= editar ? orden.getCliente() : "" %>">
        <br><br>

        <label>Responsable:</label>
        <input type="text" name="responsable" required
               value="<%= editar ? orden.getResponsable() : "" %>">
        <br><br>

        <button type="submit">
            <%= editar ? "Actualizar orden" : "Guardar orden" %>
        </button>

        <a href="<%= request.getContextPath() %>/ordenes">
            Cancelar
        </a>

    </form>

</body>
</html>
