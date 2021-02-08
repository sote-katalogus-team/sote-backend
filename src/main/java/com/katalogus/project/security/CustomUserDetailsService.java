package com.katalogus.project.security;

import com.katalogus.project.entity.ApplicationUser;
import com.katalogus.project.entity.Student;
import com.katalogus.project.entity.Teacher;
import com.katalogus.project.repository.StudentRepository;
import com.katalogus.project.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser user;
        Optional<Teacher> optionalTeacher = teacherRepository.findByEmail(email);
        Optional<Student> optionalStudent = studentRepository.findByEmail(email);
        if (optionalTeacher.isPresent() && optionalStudent.isEmpty()) {
            user = optionalTeacher.get();
        } else if (optionalStudent.isPresent() && optionalTeacher.isEmpty()) {
            user = optionalStudent.get();
        } else if (optionalTeacher.isPresent() && optionalStudent.isPresent()) {
            throw new UsernameNotFoundException("More then one user registered with " + email);
        } else {
            throw new UsernameNotFoundException("Username: " + email + " not found");
        }
        return new User(user.getEmail(), user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList()));
    }
}
