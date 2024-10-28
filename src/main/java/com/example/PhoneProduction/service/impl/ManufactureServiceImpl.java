package com.example.PhoneProduction.service.impl;

import com.example.PhoneProduction.modal.Manufacture;
import com.example.PhoneProduction.repository.ManufactureRepository;
import com.example.PhoneProduction.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufactureServiceImpl implements ManufactureService {

    @Autowired
    private ManufactureRepository manufactureRepository;

    private final static String US = "US";

    // Create a new manufacture
    public Manufacture createManufacture(Manufacture manufacture) {
        return manufactureRepository.save(manufacture);
    }

    // Read all manufactures
    public List<Manufacture> getAllManufactures() {
        return manufactureRepository.findAll();
    }

    // Read a manufacture by ID
    public Optional<Manufacture> getManufactureById(Long id) {
        return manufactureRepository.findById(id);
    }

    // Update a manufacture
    public Manufacture updateManufacture(Long id, Manufacture manufactureDetails) {
        Manufacture manufacture = manufactureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manufacture not found with id " + id));

        manufacture.setName(manufactureDetails.getName());
        manufacture.setEmployee(manufactureDetails.getEmployee());
        manufacture.setLocation(manufactureDetails.getLocation());
        return manufactureRepository.save(manufacture);
    }

    // Delete a manufacture
    public boolean deleteManufactureById(Long id) {
        Optional<Manufacture> manufactureOpt = manufactureRepository.findById(id);
        if (manufactureOpt.isPresent()) {
            manufactureRepository.delete(manufactureOpt.get());
            return true;
        }
        return false;
    }

    // Delete a manufacture by object
    public boolean deleteManufacture(Manufacture manu) {
        if (manufactureRepository.existsById(manu.getId())) {
            manufactureRepository.delete(manu);
            return true;
        }
        return false;
    }


    // Check if all manufacturers have more than 100 employees
    public boolean allManufacturersHaveMoreThan100Employees() {
        return manufactureRepository.findAll().stream()
                .allMatch(manufacture -> manufacture.getEmployee() > 100);
    }

    // Return the sum of all employees of the manufactures
    public int sumOfAllEmployees() {
        return manufactureRepository.findAll().stream()
                .mapToInt(Manufacture::getEmployee)
                .sum();
    }

    // Return the last manufacturer based in the US
    public Manufacture getLastUsManufacturer() throws Exception {
        List<Manufacture> usManufacturers = manufactureRepository.findAll().stream()
                .filter(manufacture -> US.equalsIgnoreCase(manufacture.getLocation()))
                .toList();

        if (usManufacturers.isEmpty()) {
            throw new Exception("No manufacturer based in the US found");
        }

        return usManufacturers.get(usManufacturers.size() - 1); // Return the last one
    }
}
