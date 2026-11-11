package ec.edu.uce.taller3.embeddings;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.CosineSimilarity;

public class SimilitudMain {
    static void main() {
        var pathToModel = "models/model.onnx";
        var pathToTokenizer = "models/tokenizer.json";

        EmbeddingModel embeddingsModel = new OnnxEmbeddingModel(pathToModel, pathToTokenizer, PoolingMode.MEAN);

        var embedding1 = embeddingsModel.embed("Hola, Como estas?").content();
        var embedding2 = embeddingsModel.embed("Cual es el area de un circulo?").content();
        var embedding3 = embeddingsModel.embed("Que hora es?").content();

        //calcular la similitud entre los embeddings
        double similarity1 = CosineSimilarity.between(embedding1, embedding2);
        double similarity2 = CosineSimilarity.between(embedding1, embedding3);
        double similarity3 = CosineSimilarity.between(embedding2, embedding3);

        System.out.println("Similitud entre embedding1 y embedding2: " + similarity1);
        System.out.println("Similitud entre embedding1 y embedding3: " + similarity2);
        System.out.println("Similitud entre embedding2 y embedding3: " + similarity3);
    }
}