package ec.edu.uce.taller3.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;

public class EmbeddingsModelMain {
    static void main() {
        var pathToModel = "models/model.onnx";
        var pathToTokenizer = "models/tokenizer.json";

        EmbeddingModel embeddingsModel = new OnnxEmbeddingModel(pathToModel, pathToTokenizer, PoolingMode.MEAN);

        var text = "Hola, Como estas?";
        Response<Embedding> response = embeddingsModel.embed(text);
        Embedding embedding = response.content();
        System.out.println("Embedding size: " + embedding.dimension());
        System.out.println("Embedding vector: " + embedding.vector());

        System.out.println("Original text: " + text);

        System.out.println("Embedding: ");
        float[] vector = embedding.vector();
        for (float val : vector) {
            System.out.printf("%f ", val);
        }
        System.out.println();
    }
}

