package ec.edu.espe.agrosmart.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Producto {  // final = no se puede heredar

    private final Long id;
    private final String nombre;
    private final String categoria;
    private final BigDecimal precioUsd;
    private final List<String> correosNotificacion;  // Inmutable

    // Constructor con copia defensiva
    public Producto(Long id, String nombre, String categoria, BigDecimal precioUsd, List<String> correosNotificacion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioUsd = precioUsd;
        // Copia defensiva de ENTRADA: creamos una nueva lista
        this.correosNotificacion = new ArrayList<>(correosNotificacion);
    }

    // Getters (sin setters - es inmutable)
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getPrecioUsd() {
        return precioUsd;
    }

    // Copia defensiva de SALIDA: devolvemos una copia no modificable
    public List<String> getCorreosNotificacion() {
        return Collections.unmodifiableList(new ArrayList<>(correosNotificacion));
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioUsd=" + precioUsd +
                '}';
    }
}