package com.katalogus.project.service;

import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.entity.Student;
import com.katalogus.project.repository.GyakorlatRepository;
import com.katalogus.project.repository.StudentRepository;
import com.katalogus.project.utility.RandomCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GyakorlatProvider {

    @Autowired
    GyakorlatRepository gyakorlatRepository;

    @Autowired
    RandomCodeGenerator randomCodeGenerator;

    @Autowired
    StudentRepository studentRepository;


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
        Optional<Gyakorlat> optionalGyakorlat = gyakorlatRepository.findById(gyakorlatId);
        Gyakorlat oldGyakorlat = optionalGyakorlat.get();
        oldGyakorlat.setName(gyakorlat.getName());
        oldGyakorlat.setDate(gyakorlat.getDate());
        oldGyakorlat.setPotlas(gyakorlat.getPotlas());
        oldGyakorlat.setActive(gyakorlat.getActive());
        oldGyakorlat.setAttendanceType(gyakorlat.getAttendanceType());
        Object response = gyakorlatRepository.save(oldGyakorlat);
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

    public List<Student> getStudentsById(Long id) {
        List<Student> result = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        for (Student currentStudent : studentList) {
            for (Gyakorlat gyakorlat : currentStudent.getGyakorlatList()) {
                if (gyakorlat.getId().equals(id)) {
                    result.add(currentStudent);
                }
            }
        }
        return result;
    }
}
