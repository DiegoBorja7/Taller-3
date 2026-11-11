package ec.edu.uce.taller3.embeddings;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.util.List;

public class InMemoryEmbeddingStoreMain {
    static void main() {
        System.out.println("InMemoryEmbeddingStoreMain");

        var pathToModel = "models/model.onnx";
        var pathToTokenizer = "models/tokenizer.json";

        EmbeddingModel model = new OnnxEmbeddingModel(pathToModel, pathToTokenizer, PoolingMode.MEAN);

        var documents = List.of(
                "El sol es una estrella que se encuentra en el centro del sistema solar.",
                "La luna es el satélite natural de la Tierra y refleja la luz del sol.",
                "El agua es un recurso vital para la vida en la Tierra y cubre gran parte de su superficie.",
                "Los árboles son organismos vegetales que producen oxígeno y proporcionan sombra.",
                "La gravedad es una fuerza que atrae los objetos hacia el centro de la Tierra."
        );

        InMemoryEmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();

        for (int i = 0; i < documents.size(); i++) {
            String it = documents.get(i);
            String key = "documentos - " + (i + 1);
            var embedding = model.embed(it);
            store.add(key, embedding.content(), TextSegment.from(it));
        }

        //consultar
        var consulta = "¿Qué es la gravedad?";
        var searchQuery = EmbeddingSearchRequest.builder()
                .queryEmbedding(model.embed(consulta).content())
                .maxResults(2)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = store.search(searchQuery);
        System.out.println("Resultados de la busqueda: ");
        for (var it : searchResult.matches()) {
            System.out.println("Key: " + it.embeddingId());
            System.out.println("Score: " + it.score());
            System.out.println("Content: " + it.embedded().text());
        }


    }
}
