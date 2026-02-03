package edu.unc.cs.heelp_ai.service;

import edu.unc.cs.heelp_ai.model.HeelpData;
import edu.unc.cs.heelp_ai.repository.HeelpDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HeelpDataService {

    private final HeelpDataRepository repository;
    private final VectorStore vectorStore;

    @Autowired
    public HeelpDataService(HeelpDataRepository repository, VectorStore vectorStore) {
        this.repository = repository;
        this.vectorStore = vectorStore;
    }

    public HeelpData createData(HeelpData data) {
        HeelpData savedData = repository.save(data);
        syncToVectorStore(savedData);
        return savedData;
    }

    public List<HeelpData> getAllData() {
        return repository.findAll();
    }

    public Optional<HeelpData> getDataById(Long id) {
        return repository.findById(id);
    }

    public HeelpData updateData(Long id, HeelpData dataDetails) {
        return repository.findById(id).map(existingData -> {
            existingData.setUrl(dataDetails.getUrl());
            existingData.setText_content(dataDetails.getText_content());
            HeelpData savedData = repository.save(existingData);

            // Sync to vector store: simpler approach is to delete old and add new
            // Assuming source_id is unique enough for us to use for deletion
            deleteFromVectorStore(id);
            syncToVectorStore(savedData);

            return savedData;
        }).orElse(null);
    }

    public void deleteData(Long id) {
        repository.deleteById(id);
        deleteFromVectorStore(id);
    }

    private void syncToVectorStore(HeelpData data) {
        if (data.getText_content() != null && !data.getText_content().isEmpty()) {
            // Use the HeelpData ID as the Document ID for easy retrieval/deletion
            Document document = new Document(data.getId().toString(),
                    data.getText_content(),
                    Map.of("source_id", data.getId(), "url", data.getUrl()));
            System.out.println("Document: " + document);
            vectorStore.add(List.of(document));
        }
    }

    private void deleteFromVectorStore(Long id) {
        // Delete using the ID we assigned (HeelpData ID -> String)
        vectorStore.delete(List.of(id.toString()));
    }
}
