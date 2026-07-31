package ec.edu.espe.agrosmart.mapper;

import ec.edu.espe.agrosmart.domain.Producto;
import ec.edu.espe.agrosmart.entity.ProductoEntity;

import java.util.Arrays;
import java.util.List;

public class ProductoMapper {

    public static Producto toDominio(ProductoEntity entity) {
        if (entity == null) {
            return null;
        }

        List<String> correos;
        String correosStr = entity.getCorreosNotificacion();
        if (correosStr == null || correosStr.trim().isEmpty()) {
            correos = List.of();
        } else {
            correos = Arrays.stream(correosStr.split(","))
                    .map(String::trim)
                    .filter(c -> !c.isEmpty())
                    .toList();
        }

        return new Producto(
                entity.getIdProducto(),
                entity.getNombreProducto(),
                entity.getCategoria(),
                entity.getPrecioUsd(),
                correos
        );
    }
}