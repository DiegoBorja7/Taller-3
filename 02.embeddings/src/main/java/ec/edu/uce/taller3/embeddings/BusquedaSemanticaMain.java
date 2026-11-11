package ec.edu.uce.taller3.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.util.List;

public class BusquedaSemanticaMain {
    static void main() {
        var pathToModel = "models/model.onnx";
        var pathToTokenizer = "models/tokenizer.json";

        EmbeddingModel model = new OnnxEmbeddingModel(pathToModel, pathToTokenizer, PoolingMode.MEAN);

        var documentos = List.of(
                "El sol es una estrella que se encuentra en el centro del sistema solar.",
                "La luna es el satélite natural de la Tierra y refleja la luz del sol.",
                "El agua es un recurso vital para la vida en la Tierra y cubre gran parte de su superficie.",
                "Los árboles son organismos vegetales que producen oxígeno y proporcionan sombra.",
                "La gravedad es una fuerza que atrae los objetos hacia el centro de la Tierra."
        );

        List<Embedding> embeddings = documentos.stream()
                .map(doc -> model.embed(doc))
                .map(response -> response.content())
                .toList();

        var consulta = "¿Qué es la gravedad?";

        var embeddingConsulta = model.embed(consulta).content();

        int indexDocumentoMasSimilar = 0;
        int mejorIndice = 0;
        double mejorSimilitud = -1.0;

        for(var it: embeddings){
            double score = CosineSimilarity.between(embeddingConsulta, it);
            System.out.println("Similitud con el documento " + indexDocumentoMasSimilar + ": " + score);

            if(score > mejorSimilitud){
                mejorSimilitud = score;
                mejorIndice = indexDocumentoMasSimilar;
            }
            indexDocumentoMasSimilar++;
        }
        System.out.println("El documento más similar es el: " + mejorIndice + " con el contenido: " + documentos.get(mejorIndice));
        System.out.println("La similitud es: " + mejorSimilitud);
    }
}
