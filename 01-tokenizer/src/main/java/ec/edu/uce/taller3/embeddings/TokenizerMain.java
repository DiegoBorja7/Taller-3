package ec.edu.uce.taller3.embeddings;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.IntArrayList;
import com.knuddels.jtokkit.api.ModelType;

public class TokenizerMain {
    static void main(){
        IO.println("Tokenizador inicializado. Creado por: Diego Borja");

        // 1. Inicializar el registro de codificaciones por defecto
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();

        // 2. Seleccionar el esquema BPE
        Encoding tokenizer = registry.getEncodingForModel(ModelType.TEXT_DAVINCI_003);

        var text = "Hello, do you like tea someunknownPlace?";

        // 3. Codificar el texto a una lista de IDs numéricos de tokens
        //Encode
        IntArrayList tokenIds = tokenizer.encode(text);
        System.out.println("Total tokens: " + tokenIds.size());
        System.out.println(tokenIds);

        // 5. Imprimir cada token con su ID y texto correspondiente
        for (int i = 0; i < tokenIds.size(); i++) {
            int id = tokenIds.get(i);
            IntArrayList single = new IntArrayList(1);
            single.add(id);
            System.out.printf("Token %2d -> ID: %-6d | Texto: \"%s\"%n", (i + 1), id, tokenizer.decode(single));
        }

        // 6. Decodificar la lista de IDs nuevamente a texto
        //Decode
        String textDecoded = tokenizer.decode(tokenIds);
        System.out.println("Decodificado: " + textDecoded);

    }
}
