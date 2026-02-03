package edu.unc.cs.heelp_ai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmbeddingIntegrationTest {

    @Autowired
    private VectorStore vectorStore;

    @Test
    void testEmbeddingAndSearch() {
        // 1. Create a document
        Document document = new Document("1", "Hello world, this is a test document for embedding.",
                Map.of("meta", "data"));

        // 2. Add to VectorStore (this triggers embedding via Ollama and saving to
        // PGVector)
        vectorStore.add(List.of(document));

        // 3. Search
        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query("Hello world")
                        .topK(1)
                        .build());

        // 4. Verify
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getText()).contains("Hello world");

        System.out.println("Integration test passed: Document embedded and retrieved successfully.");
    }
}
