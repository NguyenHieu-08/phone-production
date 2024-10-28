package com.example.PhoneProduction.service;

import com.example.PhoneProduction.modal.Manufacture;

import java.util.List;
import java.util.Optional;

public interface ManufactureService {
    Manufacture createManufacture(Manufacture manufacture);
    List<Manufacture> getAllManufactures();
    Optional<Manufacture> getManufactureById(Long id);
    Manufacture updateManufacture(Long id, Manufacture manufactureDetails);
    boolean deleteManufactureById(Long id);
    boolean deleteManufacture(Manufacture manu);
    boolean allManufacturersHaveMoreThan100Employees();
    int sumOfAllEmployees();
    Manufacture getLastUsManufacturer() throws Exception;

}
