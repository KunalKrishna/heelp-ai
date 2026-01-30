package edu.unc.cs.heelp_ai.service;

import edu.unc.cs.heelp_ai.model.HeelpData;
import edu.unc.cs.heelp_ai.repository.HeelpDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeelpDataService {

    private final HeelpDataRepository repository;

    @Autowired
    public HeelpDataService(HeelpDataRepository repository) {
        this.repository = repository;
    }

    public HeelpData createData(HeelpData data) {
        return repository.save(data);
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
            return repository.save(existingData);
        }).orElse(null);
    }

    public void deleteData(Long id) {
        repository.deleteById(id);
    }
}
