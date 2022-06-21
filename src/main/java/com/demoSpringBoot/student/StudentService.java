package com.demoSpringBoot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) { this.studentRepository = studentRepository; }


    public List<Student> getStudents(){

        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studenByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studenByEmail.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(long studentId){
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException(
                "Student not Exist!!!"
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(long studentId, String name, String email){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "Student is not exist"
        ));

        if(
                name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(
                name != null &&
                        name.length() > 0 &&
                        !Objects.equals(student.getEmail(), email)){
            Optional<Student> studenByEmail = studentRepository.findStudentByEmail(student.getEmail());
            if(studenByEmail.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            student.setName(email);
        }
    }
}
