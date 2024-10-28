package com.example.PhoneProduction.controller;

import com.example.PhoneProduction.modal.Phone;
import com.example.PhoneProduction.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/phones")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;


    // Create a new phone
    @PostMapping
    public ResponseEntity<Phone> createPhone(@RequestBody Phone phone) {
        Phone createdPhone = phoneService.createPhone(phone);
        return new ResponseEntity<>(createdPhone, HttpStatus.CREATED);
    }

    // Get all phones
    @GetMapping
    public ResponseEntity<List<Phone>> getAllPhones() {
        List<Phone> phones = phoneService.getAllPhones();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    // Get a phone by ID
    @GetMapping("/{id}")
    public ResponseEntity<Phone> getPhoneById(@PathVariable Long id) {
        Optional<Phone> phone = phoneService.getPhoneById(id);
        return phone.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a phone
    @PutMapping("/{id}")
    public ResponseEntity<Phone> updatePhone(@PathVariable Long id, @RequestBody Phone phoneDetails) {
        Phone updatedPhone = phoneService.updatePhone(id, phoneDetails);
        return new ResponseEntity<>(updatedPhone, HttpStatus.OK);
    }

    // Delete a phone
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        phoneService.deletePhone(id);
        return ResponseEntity.noContent().build();
    }

    // Get the phone with the highest price
    @GetMapping("/highest-price")
    public ResponseEntity<Phone> getPhoneWithHighestPrice() {
        Optional<Phone> phone = phoneService.getPhoneWithHighestPrice();
        return phone.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get phones sorted by country name and price
    @GetMapping("/sorted")
    public ResponseEntity<List<Phone>> getPhonesSortedByCountryAndPrice() {
        List<Phone> sortedPhones = phoneService.getPhonesSortedByCountryAndPrice();
        return new ResponseEntity<>(sortedPhones, HttpStatus.OK);
    }

    // Check if there is a phone priced above 50 million
    @GetMapping("/above-fifty-million")
    public ResponseEntity<Boolean> isPhoneAboveFiftyMillion() {
        boolean exists = phoneService.isPhoneAboveFiftyMillion();
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Get the first pink phone over 15 million
    @GetMapping("/pink-above-fifteen-million")
    public ResponseEntity<Phone> getFirstPinkPhoneOverFifteenMillion() {
        Phone phone = phoneService.getFirstPinkPhoneOverFifteenMillion();
        return phone != null ? ResponseEntity.ok(phone) : ResponseEntity.notFound().build();
    }
}

