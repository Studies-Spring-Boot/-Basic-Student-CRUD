package xyz.arleyrivas.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.arleyrivas.crud.model.Student;

@Repository //para que funcione como reposositorio
//recibe el objeto que queremos persitir y el tipo de dato del id que tiene
public interface IStudentRepository extends JpaRepository<Student, Long> {
}
