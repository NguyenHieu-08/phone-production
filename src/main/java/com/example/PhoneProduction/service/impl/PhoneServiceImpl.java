package com.example.PhoneProduction.service.impl;

import com.example.PhoneProduction.modal.Manufacture;
import com.example.PhoneProduction.modal.Phone;
import com.example.PhoneProduction.repository.PhoneRepository;
import com.example.PhoneProduction.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    private final static int FIFTY_MILLION = 50000000;
    private final static int FIFTEEN_MILLION = 15000000;
    private final static String PINK_COLOR = "Pink";

    // Create
    public Phone createPhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    // Read
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public Optional<Phone> getPhoneById(Long id) {
        return phoneRepository.findById(id);
    }

    // Update
    public Phone updatePhone(Long id, Phone phoneDetails) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone not found with id " + id));

        phone.setName(phoneDetails.getName());
        phone.setPrice(phoneDetails.getPrice());
        phone.setColor(phoneDetails.getColor());
        phone.setQuatity(phoneDetails.getQuatity());
        phone.setManufacture(phoneDetails.getManufacture());

        return phoneRepository.save(phone);
    }

    // Delete
    public void deletePhone(Long id) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone not found with id " + id));
        phoneRepository.delete(phone);
    }

    // Method to return the phone with the highest selling price
    public Optional<Phone> getPhoneWithHighestPrice() {
        return phoneRepository.findAll().stream()
                .max(Comparator.comparing(Phone::getPrice));
    }

    // Method to get a list of phones sorted by country name, descending by price if same country
    public List<Phone> getPhonesSortedByCountryAndPrice() {
        return phoneRepository.findAll()
                .stream()
                .sorted(Comparator.comparing((Phone phone) -> phone.getManufacture().getLocation())
                        .thenComparing(Comparator.comparing(Phone::getPrice).reversed()))
                .toList();
    }

    // Method to check if there is a phone priced above 50 million VND
    public boolean isPhoneAboveFiftyMillion() {
        return phoneRepository.findAll().stream()
                .anyMatch(phone -> phone.getPrice() > FIFTY_MILLION);
    }

    // Method to get the first phone that is pink and costs over 15 million
    public Phone getFirstPinkPhoneOverFifteenMillion() {
        return phoneRepository.findAll().stream()
                .filter(phone -> PINK_COLOR.equalsIgnoreCase(phone.getColor()) && phone.getPrice() > FIFTEEN_MILLION)
                .findFirst()
                .orElse(null);
    }
}
