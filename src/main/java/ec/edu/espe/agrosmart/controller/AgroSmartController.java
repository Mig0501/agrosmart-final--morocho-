package ec.edu.espe.agrosmart.controller;

import ec.edu.espe.agrosmart.domain.Producto;
import ec.edu.espe.agrosmart.service.ProductoService;
// import ec.edu.espe.agrosmart.service.PublicidadService;  // COMENTADO TEMPORALMENTE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class AgroSmartController {

    @Autowired
    private ProductoService productoService;

    // @Autowired  // COMENTADO TEMPORALMENTE
    // private PublicidadService publicidadService;

    // GET /api/productos - Lista todos los productos comercializables
    @GetMapping("/productos")
    public Flux<Producto> obtenerProductos() {
        return productoService.obtenerProductosComercializables();
    }

    // GET /api/productos/{id} - Busca un producto por ID
    @GetMapping("/productos/{id}")
    public Mono<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
                .onErrorResume(error -> Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado: " + id)
                ));
    }

    // GET /api/agrosmart/publicidad - Genera publicidad con IA (TEMPORALMENTE DESACTIVADO)
    @GetMapping("/agrosmart/publicidad")
    public Mono<String> generarPublicidad(
            @RequestParam String producto,
            @RequestParam String audiencia) {
        // Versión temporal sin IA
        return Mono.just("Publicidad para " + producto + " dirigido a " + audiencia);

        /* Versión original con IA (COMENTADA)
        return publicidadService.generarPublicidad(producto, audiencia);
        */
    }
}