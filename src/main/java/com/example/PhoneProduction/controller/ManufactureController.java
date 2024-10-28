package com.example.PhoneProduction.controller;

import com.example.PhoneProduction.modal.Manufacture;
import com.example.PhoneProduction.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manufactures")
public class ManufactureController {

    @Autowired
    private ManufactureService manufactureService;

    // Create a new manufacture
    @PostMapping
    public ResponseEntity<Manufacture> createManufacture(@RequestBody Manufacture manufacture) {
        Manufacture createdManufacture = manufactureService.createManufacture(manufacture);
        return new ResponseEntity<>(createdManufacture, HttpStatus.CREATED);
    }

    // Read all manufactures
    @GetMapping
    public ResponseEntity<List<Manufacture>> getAllManufactures() {
        List<Manufacture> manufactures = manufactureService.getAllManufactures();
        return new ResponseEntity<>(manufactures, HttpStatus.OK);
    }

    // Read a manufacture by ID
    @GetMapping("/{id}")
    public ResponseEntity<Manufacture> getManufactureById(@PathVariable Long id) {
        Optional<Manufacture> manufactureOpt = manufactureService.getManufactureById(id);
        return manufactureOpt.map(manufacture -> new ResponseEntity<>(manufacture, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a manufacture
    @PutMapping("/{id}")
    public ResponseEntity<Manufacture> updateManufacture(@PathVariable Long id, @RequestBody Manufacture manufactureDetails) {
        try {
            Manufacture updatedManufacture = manufactureService.updateManufacture(id, manufactureDetails);
            return new ResponseEntity<>(updatedManufacture, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a manufacture by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacture(@PathVariable Long id) {
        boolean deleted = manufactureService.deleteManufactureById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Check if all manufacturers have more than 100 employees
    @GetMapping("/check-employees")
    public ResponseEntity<Boolean> allManufacturersHaveMoreThan100Employees() {
        boolean result = manufactureService.allManufacturersHaveMoreThan100Employees();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Return the sum of all employees
    @GetMapping("/sum-employees")
    public ResponseEntity<Integer> sumOfAllEmployees() {
        int sum = manufactureService.sumOfAllEmployees();
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    // Return the last manufacturer based in the US
    @GetMapping("/last-us-manufacturer")
    public ResponseEntity<?> getLastUsManufacturer() {
        try {
            Manufacture lastUsManufacturer = manufactureService.getLastUsManufacturer();
            return new ResponseEntity<>(lastUsManufacturer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
