package com.Event.Services;

import com.Event.Repositories.VendorRepository;
import com.Event.Vendor.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> getAllvendors(){
        return vendorRepository.findAll();
    }
    public Optional<Vendor> getvendor(Integer id){
        return vendorRepository.findById(id);
    }
}
