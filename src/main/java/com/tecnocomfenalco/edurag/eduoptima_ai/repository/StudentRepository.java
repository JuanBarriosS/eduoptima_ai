package com.tecnocomfenalco.edurag.eduoptima_ai.repository;

import com.tecnocomfenalco.edurag.eduoptima_ai.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Métodos CRUD automáticos: findAll(), findById(), save(), deleteById()
    // Se pueden agregar métodos derivados según necesidad:
    // List<Student> findByTopic(String topic);
    // List<Student> findByAcademicRiskGreaterThanEqual(int risk);

    // SELECT * FROM tabla - Retorna todos los registros
    List<Student> findAll();

    // SELECT * FROM tabla WHERE id = ? - Busca por clave primaria
    Optional<Student> findById(Long id);

    // INSERT / UPDATE - Guarda o actualiza según si el ID es nulo o no
    <S extends Student> S save(S entity);

    // DELETE FROM tabla WHERE id = ? - Elimina por clave primaria
    void deleteById(Long id);

    // SELECT COUNT(*) WHERE id = ? - Verifica existencia sin cargar el objeto
    boolean existsById(Long id);
}