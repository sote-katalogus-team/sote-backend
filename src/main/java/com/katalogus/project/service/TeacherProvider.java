package com.katalogus.project.service;

import com.katalogus.project.entity.Teacher;
import com.katalogus.project.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherProvider {

    @Autowired
    TeacherRepository teacherRepository;

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public Boolean saveNewTeacher(Teacher teacher) {
        Object response = teacherRepository.save(teacher);
        return response.getClass().equals(Teacher.class);
    }

    public Boolean updateTeacherById(Teacher teacher, Long teacherId) {
        teacher.setId(teacherId);
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
