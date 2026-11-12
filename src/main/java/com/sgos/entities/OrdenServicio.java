
package com.sgos.entities;

import java.time.LocalDate;

// Representa una orden de servicio del sistema SGOS.
public class OrdenServicio {

    private Long id;
    private String descripcion;
    private LocalDate fecha;
    private String estado;
    private String cliente;
    private String responsable;

    // Constructor vacío.
    public OrdenServicio() {
    }

    // Constructor con parámetros.
    public OrdenServicio(
            String descripcion,
            LocalDate fecha,
            String estado,
            String cliente,
            String responsable) {

        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.responsable = responsable;
    }

    // Métodos de acceso a los atributos.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
