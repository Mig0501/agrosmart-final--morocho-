package ec.edu.espe.agrosmart.service;

import ec.edu.espe.agrosmart.domain.Producto;
import ec.edu.espe.agrosmart.domain.ProductoFilters;
import ec.edu.espe.agrosmart.mapper.ProductoMapper;
import ec.edu.espe.agrosmart.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    private static final Producto PRODUCTO_GENERICO = new Producto(
            0L,
            "Producto genérico",
            "Sin categoría",
            BigDecimal.ZERO,
            Arrays.asList()
    );

    public Flux<Producto> obtenerProductosComercializables() {
        return Mono.fromCallable(repository::findAll)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable)
                .map(ProductoMapper::toDominio)
                .map(ProductoFilters.A_MAYUSCULAS)
                .filter(ProductoFilters.IS_VALID)
                .doOnNext(ProductoFilters.LOG_PRODUCTO)
                .defaultIfEmpty(PRODUCTO_GENERICO);
    }

    public Mono<Producto> buscarPorId(Long id) {
        return Mono.fromCallable(() -> repository.findById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(Mono::justOrEmpty)
                .map(ProductoMapper::toDominio)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con ID: " + id)));
    }
}