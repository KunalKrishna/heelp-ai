package edu.unc.cs.heelp_ai.controller;

import edu.unc.cs.heelp_ai.model.HeelpData;
import edu.unc.cs.heelp_ai.service.HeelpDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class HeelpDataController {

    private final HeelpDataService service;

    @Autowired
    public HeelpDataController(HeelpDataService service) {
        this.service = service;
    }

    @PostMapping
    public HeelpData createData(@RequestBody HeelpData data) {
        return service.createData(data);
    }

    @GetMapping
    public List<HeelpData> getAllData() {
        return service.getAllData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeelpData> getDataById(@PathVariable Long id) {
        return service.getDataById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeelpData> updateData(@PathVariable Long id, @RequestBody HeelpData dataDetails) {
        HeelpData updatedData = service.updateData(id, dataDetails);
        if (updatedData != null) {
            return ResponseEntity.ok(updatedData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        service.deleteData(id);
        return ResponseEntity.noContent().build();
    }
}
