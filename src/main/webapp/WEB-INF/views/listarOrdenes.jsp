
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sgos.entities.OrdenServicio" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Órdenes de Servicio - SGOS</title>
</head>
<body>

    <h1>Sistema de Gestión de Órdenes de Servicio</h1>

    <h2>Órdenes registradas</h2>

    <a href="<%= request.getContextPath() %>/ordenes?accion=nuevo">
        Registrar nueva orden
    </a>

    <br><br>

    <table border="1" cellpadding="10">
        <thead>
            <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>Fecha</th>
                <th>Estado</th>
                <th>Cliente</th>
                <th>Responsable</th>
                <th>Acciones</th>
            </tr>
        </thead>

        <tbody>
        <%
            List<OrdenServicio> ordenes =
                (List<OrdenServicio>) request.getAttribute("ordenes");

            if (ordenes != null) {
                for (OrdenServicio orden : ordenes) {
        %>
            <tr>
                <td><%= orden.getId() %></td>
                <td><%= orden.getDescripcion() %></td>
                <td><%= orden.getFecha() %></td>
                <td><%= orden.getEstado() %></td>
                <td><%= orden.getCliente() %></td>
                <td><%= orden.getResponsable() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/ordenes?accion=editar&id=<%= orden.getId() %>">
                        Editar
                    </a>

                    <form action="<%= request.getContextPath() %>/ordenes"
                          method="post">

                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" name="id"
                               value="<%= orden.getId() %>">

                        <button type="submit">
                            Eliminar
                        </button>
                    </form>
                </td>
            </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

</body>
</html>
