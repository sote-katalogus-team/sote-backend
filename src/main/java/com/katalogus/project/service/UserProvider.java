package com.katalogus.project.service;

import com.katalogus.project.entity.ApplicationUser;
import com.katalogus.project.entity.Student;
import com.katalogus.project.repository.StudentRepository;
import com.katalogus.project.security.ApplicationUserRole;
import com.katalogus.project.security.JwtTokenServices;
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


    public boolean registration(UserCredentials user) {
        Student newStudent = Student.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .name(user.getName())
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Object response = studentRepository.save(newStudent);
        return response.getClass().equals(Student.class);
    }

    public Map<Object, Object> login(UserCredentials user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(user.getEmail(), roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", user.getEmail());
            model.put("roles", roles);
            model.put("token", token);

            return model;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
