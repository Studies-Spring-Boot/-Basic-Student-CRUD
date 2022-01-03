package xyz.arleyrivas.crud.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//el @DATA me crea los getter y Setter gracias a Lombok
@Data
@Entity
@Table(name= "Student") //el nombre de la tabla para postgre
public class Student {
    //le decimos cual es el Id y como estrategia le decimos que se genere de forma automatica
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    private Integer age;
}
