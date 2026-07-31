package ec.edu.espe.agrosmart.service;

// TODO: Descomentar cuando LangChain4j funcione
/*
import dev.langchain4j.service.AiService;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

@AiService
public interface AgroSmartAIService {
    @UserMessage(...)
    String generarPublicidad(...);
}
*/
public interface AgroSmartAIService {
    default String generarPublicidad(String producto, String audiencia) {
        return "Publicidad para " + producto + " (IA temporalmente desactivada)";
    }
}