package com.aop_caching.students;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody  Student student) {
        StudentDTO studentDTO = studentService.save(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer studentId) {
        StudentDTO studentDTO = studentService.findByStudentId(studentId);
        return ResponseEntity.ok(studentDTO);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Integer studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok("Student successfully deleted");
    }


    @PutMapping("/updateStudent/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Integer studentId,
            @RequestBody Student student) {
        StudentDTO updatedStudent = studentService.updateWholeStudent(studentId, student);
        return ResponseEntity.ok(updatedStudent);
    }
}
