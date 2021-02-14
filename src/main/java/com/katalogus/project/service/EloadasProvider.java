package com.katalogus.project.service;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.entity.Konzultacio;
import com.katalogus.project.entity.Student;
import com.katalogus.project.repository.EloadasRepository;
import com.katalogus.project.repository.StudentRepository;
import com.katalogus.project.utility.RandomCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EloadasProvider {

    @Autowired
    EloadasRepository eloadasRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RandomCodeGenerator randomCodeGenerator;

    public Boolean saveNewEloadas(Eloadas eloadas) {
        Object response = eloadasRepository.save(eloadas);
        return response.getClass().equals(Eloadas.class);
    }

    public Boolean updateEloadasById(Eloadas eloadas, Long eloadasId) {
        eloadas.setId(eloadasId);
        Object response = eloadasRepository.save(eloadas);
        return response.getClass().equals(Eloadas.class);
    }

    public Boolean deleteEloadasById(Long eloadasId) {
        long before = eloadasRepository.count();
        eloadasRepository.deleteById(eloadasId);
        long after = eloadasRepository.count();
        return before > after;
    }

    public Boolean changeActiveStatusById(Long eloadasId) {
        boolean success = false;
        Optional<Eloadas> optionalEloadas = eloadasRepository.findById(eloadasId);
        if (optionalEloadas.isPresent()) {
            Eloadas eloadas = optionalEloadas.get();
            eloadas.setActive(!eloadas.getActive());
            eloadasRepository.save(eloadas);
            success = true;
        }
        return success;
    }

    public Eloadas getEloadasById(Long eloadasId) {
        return eloadasRepository.findById(eloadasId).get();

    }

    public String openClassForAttendace(Long eloadasId) {
        String code = "There is no eloadas with this Id";
        Optional<Eloadas> optionalEloadas = eloadasRepository.findById(eloadasId);
        if (optionalEloadas.isPresent()) {
            Eloadas eloadas = optionalEloadas.get();
            code = randomCodeGenerator.codeGenerator();
            eloadas.setCode(code);
            eloadas.setIsAttendanceOpen(true);
            eloadasRepository.save(eloadas);
        }
        return code;
    }

    public Boolean closeClassForAttendace(Long eloadasId) {
        boolean success = false;
        Optional<Eloadas> optionalEloadas = eloadasRepository.findById(eloadasId);
        if (optionalEloadas.isPresent()) {
            Eloadas eloadas = optionalEloadas.get();
            eloadas.setIsAttendanceOpen(false);
            eloadasRepository.save(eloadas);
            success = true;
        }
        return success;
    }

    public List<Student> getStudentsById(Long id) {
        List<Student> result = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        for (Student currentStudent : studentList) {
            for (Eloadas eloadas : currentStudent.getEloadasList()) {
                if (eloadas.getId().equals(id)) {
                    result.add(currentStudent);
                }
            }
        }
        return result;
    }
}
