package com.katalogus.project.service;

import com.katalogus.project.entity.Student;
import com.katalogus.project.repository.StudentRepository;
import com.katalogus.project.security.ApplicationUserRole;
import com.katalogus.project.security.JwtTokenServices;
import com.katalogus.project.utility.RegistrationData;
import com.katalogus.project.utility.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProvider {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenServices jwtTokenServices;


    public HashMap<Boolean, String> registration(RegistrationData user) {
        HashMap<Boolean, String> response = new HashMap<>();
        response.put(false, "Email already in use.");
        Optional<Student> optionalStudent = studentRepository.findByEmail(user.getEmail());
        if (optionalStudent.isEmpty()) {
            response.replace(false, "Can't register this user");
            Student newStudent = Student.builder()
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .name(user.getName())
                    .neptunCode(user.getNeptunCode())
                    .roles(List.of(ApplicationUserRole.STUDENT))
                    .turnusId(user.getTurnusId())
                    .build();
            Object saveResponse = studentRepository.save(newStudent);
            if (saveResponse.getClass().equals(Student.class)) {
                response.clear();
                response.put(true, "Registered " + newStudent.getName() + " successfully!");
            }
        }
        return response;
    }

    public Map<Object, Object> login(UserCredentials user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(user.getEmail(), roles);
            Student studentData = null;
            if (roles.contains("STUDENT")) {
               Optional<Student> student = studentRepository.findByEmail(user.getEmail());
               if (student.isPresent()) {
                   studentData = student.get();
               }
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("name", user.getName());
            model.put("email", user.getEmail());
            model.put("roles", roles);
            model.put("token", token);
            if (studentData != null) {
                model.put("id", studentData.getId());
                model.put("name", studentData.getName());
            }

            return model;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
