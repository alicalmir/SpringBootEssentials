package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private  final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();

    };

    public void addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if( studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    };

    public void deleteStudent(Long studentId){
        boolean exits = studentRepository.existsById(studentId);
        if(!exits) {
            throw new IllegalStateException(
                    "student with id " + studentId + "does not exits");



        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, Student updatedStudent) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isEmpty()) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }

        // Get the existing student
        Student student = studentOptional.get();

        // Update the fields as needed
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());

        // Save the updated student
        studentRepository.save(student);
    }

}
