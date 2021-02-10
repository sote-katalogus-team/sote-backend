package com.katalogus.project.service;

import com.katalogus.project.entity.Teacher;
import com.katalogus.project.repository.TeacherRepository;
import com.katalogus.project.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherProvider {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public HashMap<Boolean, String> saveNewTeacher(Teacher teacher) {
        HashMap<Boolean, String> response = new HashMap<>();
        response.put(false, "Email already in use.");
        Optional<Teacher> optionalTeacher = teacherRepository.findByEmail(teacher.getEmail());
        if (optionalTeacher.isEmpty()) {
            response.replace(false, "Can't register this teacher");
            Teacher newTeacher = Teacher.builder()
                    .email(teacher.getEmail())
                    .password(passwordEncoder.encode(teacher.getPassword()))
                    .name(teacher.getName())
                    .roles(List.of(ApplicationUserRole.TEACHER))
                    .build();
            Object saveResponse = teacherRepository.save(newTeacher);
            if (saveResponse.getClass().equals(Teacher.class)) {
                response.clear();
                response.put(true, "Registered " + newTeacher.getName() + " successfully!");
            }
        }
        return response;
    }

    public Boolean updateTeacherById(Teacher teacher, Long teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        teacher.setId(teacherId);
        if (!optionalTeacher.get().getPassword().equals(teacher.getPassword())) {
            String password = teacher.getPassword();
            teacher.setPassword(passwordEncoder.encode(password));
        }
        Object response = teacherRepository.save(teacher);
        return response.getClass().equals(Teacher.class);
    }

    public Boolean deleteTeacherById(Long teacherId) {
        long before = teacherRepository.count();
        teacherRepository.deleteById(teacherId);
        long after = teacherRepository.count();
        return before > after;
    }
}
