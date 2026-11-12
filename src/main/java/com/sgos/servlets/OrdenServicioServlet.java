
package com.sgos.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.sgos.entities.OrdenServicio;
import com.sgos.repositories.OrdenServicioJdbcRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ordenes")
public class OrdenServicioServlet extends HttpServlet {

    private OrdenServicioJdbcRepository repository;

    @Override
    public void init() throws ServletException {
        repository = new OrdenServicioJdbcRepository();
    }

    // Procesar solicitudes GET.
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {

            case "nuevo":
                request.getRequestDispatcher(
                    "/WEB-INF/views/formularioOrden.jsp"
                ).forward(request, response);
                break;

            case "editar":
                Long id = Long.parseLong(
                    request.getParameter("id"));

                OrdenServicio orden = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException(
                        "Orden no encontrada"));

                request.setAttribute("orden", orden);

                request.getRequestDispatcher(
                    "/WEB-INF/views/formularioOrden.jsp"
                ).forward(request, response);
                break;

            default:
                List<OrdenServicio> ordenes = repository.findAll();

                request.setAttribute("ordenes", ordenes);

                request.getRequestDispatcher(
                    "/WEB-INF/views/listarOrdenes.jsp"
                ).forward(request, response);
                break;
        }
    }

    // Procesar solicitudes POST.
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Acción no especificada");
            return;
        }

        switch (accion) {

            case "guardar":
                OrdenServicio nuevaOrden =
                    obtenerDatosFormulario(request);

                repository.save(nuevaOrden);
                break;

            case "actualizar":
                Long idActualizar = Long.parseLong(
                    request.getParameter("id"));

                OrdenServicio ordenActualizada =
                    obtenerDatosFormulario(request);

                repository.update(idActualizar, ordenActualizada);
                break;

            case "eliminar":
                Long idEliminar = Long.parseLong(
                    request.getParameter("id"));

                repository.deleteById(idEliminar);
                break;

            default:
                response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Acción no válida");
                return;
        }

        response.sendRedirect(
            request.getContextPath() + "/ordenes");
    }

    // Obtener los datos enviados desde el formulario HTML.
    private OrdenServicio obtenerDatosFormulario(
            HttpServletRequest request) {

        String descripcion = request.getParameter("descripcion");
        LocalDate fecha = LocalDate.parse(
            request.getParameter("fecha"));
        String estado = request.getParameter("estado");
        String cliente = request.getParameter("cliente");
        String responsable = request.getParameter("responsable");

        return new OrdenServicio(
            descripcion, fecha, estado, cliente, responsable);
    }
}
