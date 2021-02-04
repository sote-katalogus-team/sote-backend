package com.katalogus.project.service;

import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.repository.GyakorlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GyakorlatProvider {

    @Autowired
    GyakorlatRepository gyakorlatRepository;


    public Boolean saveNewGyakorlat(Gyakorlat gyakorlat) {
        Object response = gyakorlatRepository.save(gyakorlat);
        return response.getClass().equals(Gyakorlat.class);
    }

    public Boolean deleteGyakorlatById(Long gyakorlatId) {
        long before = gyakorlatRepository.count();
        gyakorlatRepository.deleteById(gyakorlatId);
        long after = gyakorlatRepository.count();
        return before > after;
    }

    public Boolean updateGyakorlatById(Gyakorlat gyakorlat, Long gyakorlatId) {
        gyakorlat.setId(gyakorlatId);
        Object response = gyakorlatRepository.save(gyakorlat);
        return response.getClass().equals(Gyakorlat.class);
    }


    public Boolean changeActiveStatusById(Long gyakorlatId) {
        boolean success = false;
        Optional<Gyakorlat> optionalGyakorlat = gyakorlatRepository.findById(gyakorlatId);
        if (optionalGyakorlat.isPresent()) {
            Gyakorlat gyakorlat = optionalGyakorlat.get();
            gyakorlat.setActive(!gyakorlat.getActive());
            gyakorlatRepository.save(gyakorlat);
            success = true;
        }
        return success;
    }
}
