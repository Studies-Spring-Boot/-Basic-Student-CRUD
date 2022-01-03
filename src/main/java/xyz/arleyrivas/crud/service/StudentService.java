package xyz.arleyrivas.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.arleyrivas.crud.model.Student;
import xyz.arleyrivas.crud.repository.IStudentRepository;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private IStudentRepository iStudentRepository;

    //luego definimos las operaciones CRUD

    //Guardar un estudiante que no  venga con id null, el id solo para editar uno existente.
    // hacer uso de las excepciones
    public Student saveStudent (Student student){
        if(student.getId()== null) {
            return iStudentRepository.save(student);
        }
        return null;
    }

    //listar estudiantes
    //Recibimos el numero de la pagina a hacer la petición, el tamaño (cuantos datos vamos a traer por cada pagina) y un boolean para si viene en true la paginacion hacerlo, sino no.
    public Page<Student> getAllStudent (Integer page, Integer size, Boolean enablePagination){
       //preguntamos si la paginacion viene activada, en caso que si hacemos el PageRequest (page, size),
        // si el boolean del parametros es false, hacemos el Pagleable.unpaged (le decimos que no esta paginado
        return iStudentRepository.findAll(enablePagination ? PageRequest.of(page, size): Pageable.unpaged());
    }

    public Optional<Student> findById (long id){
       // if (id != null){
            return iStudentRepository.findById(id);
       // }
       // return null;
    }
    //podemos recibir el Id o el objeto completo, en este caso por id de tipo Long segun su model
    public void deleteStudent(Long id){
        iStudentRepository.deleteById(id);
    }

    //El JPA no trae un editar sino un guardar (save) para ellos debemos hacer uso de los DTO
    // en el DTO recibimos el (Student stundent y el Long id) con esto tenemos más seguridad
    public Student editStudent (Student student) {
        //si vamos a editar el id no puede ser uno para consultarlo y luego editarlo
        if (student.getId() != null && iStudentRepository.existsById(student.getId())) {
            return iStudentRepository.save(student);
        }
        return null;

        //un ejemplo sencillo pero no aplicable, falta la logica para editar
    }

    public boolean existById(Long id) {
        return iStudentRepository.existsById(id);
    }
}


