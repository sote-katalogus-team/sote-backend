package com.katalogus.project.service;

import com.katalogus.project.entity.Teacher;
import com.katalogus.project.repository.TeacherRepository;
import com.katalogus.project.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public HashMap<Boolean, String> saveNewTeacher(Teacher teacher, String role) {
        HashMap<Boolean, String> response = new HashMap<>();
        response.put(false, "Email already in use.");
        Optional<Teacher> optionalTeacher = teacherRepository.findByEmail(teacher.getEmail());
        if (optionalTeacher.isEmpty()) {
            response.replace(false, "Can't register this teacher");
            Teacher newTeacher = Teacher.builder()
                    .email(teacher.getEmail())
                    .password(passwordEncoder.encode(teacher.getPassword()))
                    .name(teacher.getName())
                    .build();
            List<ApplicationUserRole> roles = new ArrayList<>(List.of(ApplicationUserRole.TEACHER));
            if (role.equals("admin")) {
                roles.add(ApplicationUserRole.ADMIN);
            }
            newTeacher.setRoles(roles);
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
        Teacher oldTeacher = optionalTeacher.get();
        if (teacher.getPassword().equals("")) {
            teacher.setPassword(optionalTeacher.get().getPassword());
        }
        if (!optionalTeacher.get().getPassword().equals(teacher.getPassword())) {
            String password = teacher.getPassword();
            oldTeacher.setPassword(passwordEncoder.encode(password));
        }
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setName(teacher.getName());
        Object response = teacherRepository.save(oldTeacher);
        return response.getClass().equals(Teacher.class);
    }

    public Boolean deleteTeacherById(Long teacherId) {
        long before = teacherRepository.count();
        teacherRepository.deleteById(teacherId);
        long after = teacherRepository.count();
        return before > after;
    }
}
