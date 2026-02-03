package edu.unc.cs.heelp_ai.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class OllamaEmbeddingCheck implements CommandLineRunner {

    private final EmbeddingModel embeddingModel;

    @Autowired
    public OllamaEmbeddingCheck(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------------------------------");
        System.out.println("🦙 TESTING OLLAMA EMBEDDING...");
        System.out.println("----------------------------------------");

        try {
            String testSentence = "Hello, Ollama! This is a test sentence for embedding.";
            System.out.println("📝 Text: \"" + testSentence + "\"");

            float[] embedding = embeddingModel.embed(testSentence);

            if (embedding.length > 0) {
                System.out.println("✅ SUCCESS! Embedding generated.");
                System.out.println("This is the dimension of the embedding: " + embedding.length);
                System.out.println("This is the embedding: " + Arrays.toString(embedding));
                // System.out.println("Sample (first 5 values): " + embedding.subList(0,
                // Math.min(5, embedding.size())));
            } else {
                System.err.println("❌ FAILED: Embedding check returned empty or null.");
            }

        } catch (Exception e) {
            System.err.println("❌ FAILED: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("----------------------------------------");
    }
}
