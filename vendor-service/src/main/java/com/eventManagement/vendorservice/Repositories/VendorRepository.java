package com.eventManagement.vendorservice.Repositories;


import com.eventManagement.vendorservice.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Integer> {
    @Override
    List<Vendor> findAll();
}
