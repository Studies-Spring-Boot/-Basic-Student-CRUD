package xyz.arleyrivas.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.arleyrivas.crud.model.Student;
import xyz.arleyrivas.crud.service.StudentService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/student") //path al que le haremos las peticiones HTTP
public class StudentController {

    //cuando necesitamos que se llene automaticamente,
    // el @Autowired Permite que Spring resuelva e inyecte beans colaborativos en nuestro bean.
    @Autowired
    private StudentService studentService;


    @PostMapping
    public ResponseEntity<Student> saveStrudent ( @Valid @RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.saveStudent(student));
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudent ( //hacemos opcionales los parametros
            @RequestParam(required = false, defaultValue= "0")Integer page,
            @RequestParam(required = false, defaultValue= "0")Integer size,
            @RequestParam(required = false, defaultValue= "false")Boolean enablePagination
    ){
        return ResponseEntity.ok(studentService.getAllStudent(page, size, enablePagination));
    }

    @DeleteMapping (value = "/{id}")
    public void deleteStudent( @PathVariable("id") Long id){
    //    ResponseEntity.status(HttpStatus.OK).body(studentService.deleteStudent(id));
            studentService.deleteStudent(id);
            ResponseEntity.ok(!studentService.existById(id));
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Optional<Student>> findStudentById(@PathVariable("id") Long id){
              return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id));
    }



    @PutMapping
    public ResponseEntity<Student> editStudent (@Valid @RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.editStudent(student));
    }

}