package ec.edu.espe.agrosmart.domain;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ProductoFilters {

    // Predicate: verifica si un producto es válido
    public static final Predicate<Producto> IS_VALID = producto ->
            producto.getPrecioUsd() != null &&
                    producto.getPrecioUsd().compareTo(BigDecimal.ZERO) > 0 &&
                    !producto.getCorreosNotificacion().isEmpty();

    // Consumer: imprime información del producto (efecto lateral)
    public static final Consumer<Producto> LOG_PRODUCTO = producto ->
            System.out.println("✅ Producto procesado: " + producto.getId() + " - " + producto.getNombre());

    // Function: convierte el nombre a mayúsculas (crea una nueva instancia)
    public static final Function<Producto, Producto> A_MAYUSCULAS = producto ->
            new Producto(
                    producto.getId(),
                    producto.getNombre().toUpperCase(),
                    producto.getCategoria(),
                    producto.getPrecioUsd(),
                    producto.getCorreosNotificacion()
            );
}
