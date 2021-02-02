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

    public void deleteGyakorlatById(Long gyakorlatId) {
        gyakorlatRepository.deleteById(gyakorlatId);
    }

    public Boolean updateGyakorlatById(Gyakorlat gyakorlat, Long gyakorlatId) {
        gyakorlat.setId(gyakorlatId);
        Object response = gyakorlatRepository.save(gyakorlat);
        return response.getClass().equals(Gyakorlat.class);
    }


    public void changeActiveStatusById(Long gyakorlatId) {
        Optional<Gyakorlat> optionalGyakorlat = gyakorlatRepository.findById(gyakorlatId);
        if (optionalGyakorlat.isPresent()) {
            Gyakorlat gyakorlat = optionalGyakorlat.get();
            gyakorlat.setActive(!gyakorlat.getActive());
            gyakorlatRepository.save(gyakorlat);
        }
    }
}
