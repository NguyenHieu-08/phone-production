package com.example.PhoneProduction.repository;

import com.example.PhoneProduction.modal.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
}
