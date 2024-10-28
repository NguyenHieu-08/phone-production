package com.example.PhoneProduction.service;

import com.example.PhoneProduction.modal.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    Phone createPhone(Phone phone);
    List<Phone> getAllPhones();
    Optional<Phone> getPhoneById(Long id);
    Phone updatePhone(Long id, Phone phoneDetails);
    void deletePhone(Long id);
    Optional<Phone> getPhoneWithHighestPrice();
    List<Phone> getPhonesSortedByCountryAndPrice();
    boolean isPhoneAboveFiftyMillion();
    Phone getFirstPinkPhoneOverFifteenMillion();
}
