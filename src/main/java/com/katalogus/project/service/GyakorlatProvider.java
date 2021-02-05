package com.katalogus.project.service;

import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.repository.GyakorlatRepository;
import com.katalogus.project.utility.RandomCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GyakorlatProvider {

    @Autowired
    GyakorlatRepository gyakorlatRepository;

    @Autowired
    RandomCodeGenerator randomCodeGenerator;


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

    public Gyakorlat getGyakorlatById(Long gyakorlatId) {
        return gyakorlatRepository.findById(gyakorlatId).get();
    }

    public String openClassForAttendace(Long gyakorlatId) {
        String code = "There is no gyakorlat with this Id";
        Optional<Gyakorlat> optionalGyakorlat = gyakorlatRepository.findById(gyakorlatId);
        if (optionalGyakorlat.isPresent()) {
            Gyakorlat gyakorlat = optionalGyakorlat.get();
            code = randomCodeGenerator.codeGenerator();
            gyakorlat.setCode(code);
            gyakorlat.setIsAttendanceOpen(true);
            gyakorlatRepository.save(gyakorlat);
        }
        return code;
    }

    public Boolean closeClassForAttendace(Long gyakorlatId) {
        boolean success = false;
        Optional<Gyakorlat> optionalGyakorlat = gyakorlatRepository.findById(gyakorlatId);
        if (optionalGyakorlat.isPresent()) {
            Gyakorlat gyakorlat = optionalGyakorlat.get();
            gyakorlat.setIsAttendanceOpen(false);
            gyakorlatRepository.save(gyakorlat);
            success = true;
        }
        return success;
    }
}
