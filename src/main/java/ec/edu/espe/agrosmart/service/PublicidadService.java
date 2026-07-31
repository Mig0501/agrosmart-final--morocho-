package ec.edu.espe.agrosmart.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PublicidadService {

    // @Autowired  // COMENTADO TEMPORALMENTE
    // private AgroSmartAIService aiService;

    public Mono<String> generarPublicidad(String producto, String audiencia) {
        // Versión temporal sin IA (siempre devuelve un mensaje genérico)
        return Mono.just("Publicidad para " + producto + " dirigido a " + audiencia);
        
        /* ========== VERSIÓN ORIGINAL CON IA (COMENTADA) ==========
        return Mono.fromCallable(() -> aiService.generarPublicidad(producto, audiencia))
                .subscribeOn(Schedulers.boundedElastic())
                .timeout(Duration.ofSeconds(30))
                .onErrorResume(e -> Mono.just(
                        "Publicidad no disponible en este momento (" + e.getClass().getSimpleName() + ")"
                ));
        =========================================================== */
    }
}