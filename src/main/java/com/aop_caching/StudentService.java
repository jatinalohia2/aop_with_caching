package com.aop_caching;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public StudentDTO save(Student student) {
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Cacheable(cacheNames = "student" , key = "#id")
    public StudentDTO findByStudentId(Integer id) {
        Student student = getStudentOrThrow(id);
        return modelMapper.map(student, StudentDTO.class);
    }

    @CacheEvict(cacheNames = "student" , key = "#studentId")
    public void deleteStudentById(Integer studentId) {
        Student student = getStudentOrThrow(studentId);
        studentRepository.delete(student);
    }

    @CachePut(cacheNames = "student" , key = "#studentId")
    public StudentDTO updateWholeStudent(Integer studentId, Student student) {
        getStudentOrThrow(studentId); // ensure it exists
        student.setId(studentId);
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentDTO.class);
    }

    private Student getStudentOrThrow(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
}
