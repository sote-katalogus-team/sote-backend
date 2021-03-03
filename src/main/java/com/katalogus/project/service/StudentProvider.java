package com.katalogus.project.service;

import com.katalogus.project.entity.*;
import com.katalogus.project.model.*;
import com.katalogus.project.repository.StudentRepository;
import com.katalogus.project.utility.AttendancePercentage;
import com.katalogus.project.utility.RandomCodeGenerator;
import org.hibernate.query.criteria.internal.predicate.BooleanExpressionPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StudentProvider {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendancePercentage attendancePercentage;

    @Autowired
    private ClassesProvider classesProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RandomCodeGenerator randomCodeGenerator;

    @Autowired
    private EmailService emailService;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public HashMap<Boolean, String> saveNewStudent(Student student) {
        HashMap<Boolean, String> response = new HashMap<>();
        response.put(false, "Email already in use.");
        Optional<Student> optionalStudent = studentRepository.findByEmail(student.getEmail());
        if (optionalStudent.isEmpty()) {
            response.replace(false, "Can't register this student");
            Student newStudent = Student.builder()
                    .email(student.getEmail())
                    .password(passwordEncoder.encode(student.getPassword()))
                    .name(student.getName())
                    .neptunCode(student.getNeptunCode())
                    .validationCode(randomCodeGenerator.codeGenerator())
                    .turnusId(student.getTurnusId())
                    .build();
            Object saveResponse = studentRepository.save(newStudent);
            if (saveResponse.getClass().equals(Student.class)) {
                response.clear();
                response.put(true, "Registered " + newStudent.getName() + " successfully!");
                emailService.sendNewValidationCode(student.getEmail(), student.getValidationCode());
            }
        }
        return response;
    }

    public Boolean updateStudentById(Student student, Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student oldStudent = optionalStudent.get();
        if (!oldStudent.getPassword().equals(student.getPassword())) {
            String password = student.getPassword();
            oldStudent.setPassword(passwordEncoder.encode(password));
        }
        if (student.getPassword().equals("")) {
            student.setPassword(optionalStudent.get().getPassword());
        }
        oldStudent.setName(student.getName());
        oldStudent.setEmail(student.getEmail());
        oldStudent.setNeptunCode(student.getNeptunCode());
        oldStudent.setTurnusId(student.getTurnusId());
        Object response = studentRepository.save(oldStudent);
        return response.getClass().equals(Student.class);
    }
    public Boolean updateStudentsNameById(String name, Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student oldStudent = optionalStudent.get();
            oldStudent.setName(name);
            Object response = studentRepository.save(oldStudent);
            return response.getClass().equals(Student.class);
        }
        else {
            return false;
        }
    }
    public Boolean updateStudentsEmailById(String email, Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            if (isEmailUnique(email)) {
                Student oldStudent = optionalStudent.get();
                oldStudent.setEmail(email);
                Object response = studentRepository.save(oldStudent);
                return response.getClass().equals(Student.class);
            } else{
                return false;
            }
        }
        else {
            return false;
        }
    }
    public Boolean updateStudentsPasswordById(String password, Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student oldStudent = optionalStudent.get();
            oldStudent.setPassword(passwordEncoder.encode(password));
            Object response = studentRepository.save(oldStudent);
            return response.getClass().equals(Student.class);
        }
        else {
            return false;
        }
    }
    public Boolean updateStudentsNeptunCodeById(String neptunCode, Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            if (isNeptunCodelUnique(neptunCode)) {
                Student oldStudent = optionalStudent.get();
                oldStudent.setNeptunCode(neptunCode);
                Object response = studentRepository.save(oldStudent);
                return response.getClass().equals(Student.class);
            }
            else {
                return false;
            }

        }
        else {
            return false;
        }
    }
    public Boolean updateStudentsTurnusById(Long turnusId, Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student oldStudent = optionalStudent.get();
            oldStudent.setTurnusId(turnusId);
            Object response = studentRepository.save(oldStudent);
            return response.getClass().equals(Student.class);
        }
        else {
            return false;
        }
    }

    public Boolean deleteStudentById(Long studentId) {
        long before = studentRepository.count();
        studentRepository.deleteById(studentId);
        long after = studentRepository.count();
        return before > after;
    }

    public StudentStatistic getStudentStatisticByStudentId(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            Classes classes = classesProvider.getAllByTurnusId(student.getTurnusId());
            return StudentStatistic.builder()
                    .percentages(attendancePercentage.calculateAttendancePercentages(student, classes))
                    .neptunCode(student.getNeptunCode())
                    .studentName(student.getName())
                    .build();
        }
        return null;
    }

    public HashMap<Boolean, String> sendInCode(Long studentId, HashMap<String, String> code) {
        HashMap<Boolean, String> success = new HashMap<>();
        success.put(false, "Student not found");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            Classes classes = classesProvider.getAllByTurnusId(student.getTurnusId());
            List<BasicClass> activeClasses = classes.getActiveClasses();
            BasicClass currentClass = null;
            for (BasicClass classToCheck : activeClasses) {
                if (classToCheck.getCode().equalsIgnoreCase(code.get("code"))) {
                    currentClass = classToCheck;
                }
            }
            success.put(false, "Not valid code");
            if (currentClass != null) {
                success.remove(false);
                success.put(true, "Student already attended in this class");
                if (!student.wasInClass(currentClass)) {
                    success.put(true, "Successfully attend at class");
                    student.addClass(currentClass);
                    studentRepository.save(student);
                }
            }
        }
        return success;
    }

    public Integer countStudentAtClass(ClassInfo classInfo) {
        Integer count = 0;
        List<Student> studentList = studentRepository.findAll();
        for (Student currentStudent : studentList) {
            if (classInfo.getType() == ClassType.ELOADAS) {
                for (Eloadas eloadas : currentStudent.getEloadasList()) {
                    if (eloadas.getId().equals(classInfo.getId())) {
                        count++;
                    }
                }
            } else if (classInfo.getType() == ClassType.GYAKORLAT) {
                for (Gyakorlat gyakorlat : currentStudent.getGyakorlatList()) {
                    if (gyakorlat.getId().equals(classInfo.getId())) {
                        count++;
                    }
                }
            } else {
                for (Konzultacio konzultacio : currentStudent.getKonzultacioList()) {
                    if (konzultacio.getId().equals(classInfo.getId())) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public HashMap<Boolean, String> addByNeptunCode(ManualAttendance manualAttendance) {
        HashMap<Boolean, String> success = new HashMap<>();
        ClassInfo classInfo = manualAttendance.getClassInfo();
        String neptunString = manualAttendance.getNeptunCode();
        success.put(false, "No registered student with " + neptunString);
        Optional<Student> optionalStudent = studentRepository.findByNeptunCodeIgnoreCase(neptunString);
        if (optionalStudent.isPresent()) {
            success.put(false, "No class found");
            Classes classes = classesProvider.getAllClasses();
            Student student = optionalStudent.get();
            BasicClass currentClass = null;
            if (classInfo.getType() == ClassType.ELOADAS) {
                for (Eloadas eloadas : classes.getEloadasList()) {
                    if (eloadas.getId().equals(classInfo.getId())) {
                        currentClass = eloadas;
                    }
                }
            } else if (classInfo.getType() == ClassType.GYAKORLAT) {
                for (Gyakorlat gyakorlat : classes.getGyakorlatList()) {
                    if (gyakorlat.getId().equals(classInfo.getId())) {
                        currentClass = gyakorlat;
                    }
                }
            } else {
                for (Konzultacio konzultacio : classes.getKonzultacioList()) {
                    if (konzultacio.getId().equals(classInfo.getId())) {
                        currentClass = konzultacio;
                    }
                }
            }
            if (currentClass != null) {
                success.put(false, "Student was already on the class");
                if (!student.wasInClass(currentClass)){
                    student.addClass(currentClass);
                    studentRepository.save(student);
                    success.remove(false);
                    success.put(true, student.getName() + " was added to class!");

                }
            }
        }
        return success;
    }

    public Integer getHeadCount(Long turnusId) {
        Integer headCount = studentRepository.countByTurnusId(turnusId);
        if (headCount < 1) {
            return -1;
        } else {
            return headCount;
        }

    }

    public List<Student> getAllStudentByTurnusId(Long turnusId) {
        return studentRepository.findAllByTurnusId(turnusId);
    }

    private Boolean isEmailUnique (String email) {
        List<Student> allStudent = studentRepository.findAll();
        for (Student student: allStudent)  {
            if (student.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
    private Boolean isNeptunCodelUnique (String neptunCode) {
        System.out.println(neptunCode + "  NPETUNCODE");
        List<Student> allStudent = studentRepository.findAll();
        for (Student student: allStudent)  {
            if (student.getNeptunCode().equalsIgnoreCase(neptunCode)) {
                return false;
            }
        }
        return true;
    }
}
